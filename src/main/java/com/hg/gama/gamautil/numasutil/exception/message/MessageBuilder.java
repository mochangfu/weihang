package com.hg.gama.gamautil.numasutil.exception.message;

import com.google.common.collect.Maps;
import com.hg.gama.gamautil.numasutil.SpringContextHolder;
import com.hg.gama.gamautil.numasutil.constants.BaseCode;
import com.hg.gama.gamautil.numasutil.util.StringUtil2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.Properties;

public class MessageBuilder {

    private static final Logger logger = LoggerFactory.getLogger(MessageBuilder.class);

    public static ErrorMessage build(String id, Object... params) {
        return build(MessageLanguage.CN, id, params);
    }

    public static ErrorMessage build(MessageLanguage language, String id, Object... params) {
        try {
            String msg = MessageDef.getMessage(id);
            ErrorMessage message = new ErrorMessage();
            if (msg != null) {
                message.setId(id);
                message.setLanguage(language.toString());
                message.setContent(StringUtil2.formatSlf4jMsg(
                        msg, params));
            } else {
                message = ErrorMessage.SYSTEM_ERROR_TEMPLATE.copy();
                message.setContent(StringUtil2.formatSlf4jMsg(
                        message.getContent(), id));
            }
            return message;
        } catch (RuntimeException e) {
            logger.error("Create error message failed!", e);
            throw e;
        }
    }

    public static ErrorMessage buildDefaultError(String param) {
        ErrorMessage massage = ErrorMessage.SYSTEM_ERROR_TEMPLATE.copy();
        massage.setContent(StringUtil2.formatSlf4jMsg(massage.getContent(), param));
        return massage;
    }

    private static class MessageDef {
        private static volatile Map<String, String> messageMap;
        private static volatile Map<String, String> messageCodeMap;
        private static final String RESOURCE_PATH = "classpath*:/messages/*.properties";

        public static String getMessage(String msgId) {
            if (messageMap == null) {
                synchronized (MessageDef.class) {
                    if (messageMap == null) {
                        messageMap = Maps.newConcurrentMap();
                        messageCodeMap = Maps.newConcurrentMap();

                        try {
                            ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(MessageBuilder.class.getClassLoader());
                            Resource[] resources = resolver.getResources(RESOURCE_PATH);
                            Properties p = new Properties();
                            for (Resource resource : resources) {
                                p.load(resource.getInputStream());
                            }

                            Map<String, BaseCode> beans = SpringContextHolder.getBeans(BaseCode.class);
                            for (Map.Entry<String, BaseCode> entry : beans.entrySet()) {
                                Object constant = entry.getValue();
                                Field[] declaredFields = constant.getClass().getFields();
                                for (Field field : declaredFields) {
                                    if (Modifier.isStatic(field.getModifiers())
                                            && Modifier.isPublic(field.getModifiers())
                                            && field.getDeclaringClass().equals(ClassUtils.getUserClass(constant))
                                            && !field.getName().contains("$")) {
                                        String messageName = field.getName();
                                        Integer code;
                                        try {
                                            code = ((Integer) field.get(null));
                                        } catch (IllegalAccessException e) {
                                            throw new RuntimeException(e);
                                        }
                                        String key = messageName.toLowerCase().replace('_', '.');
                                        String message = p.getProperty(key);
                                        if (messageMap.containsKey(String.valueOf(code)) &&
                                                !messageCodeMap.get(String.valueOf(code)).equals(key)) {
                                            logger.warn("Message code {}:{} {}:{} duplicated.", code, key, code,
                                                    messageCodeMap.get(String.valueOf(code)));
                                        } else if (message == null) {
                                            logger.warn("Message key {} NOT defined.", key);
                                        } else {
                                            messageMap.put(String.valueOf(code), message);
                                            messageCodeMap.put(String.valueOf(code), key);
                                        }
                                    }
                                }
                            }
                        } catch (Exception ex) {
                            logger.error("Load message resource failed", ex);
                        }
                    }
                }
            }
            return messageMap.get(msgId);
        }
    }

}
