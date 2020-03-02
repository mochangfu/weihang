package com.hg.gama.gamautil.numasutil.messagebus;

import com.hg.gama.gamautil.numasutil.SpringContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class MessageBusAwareProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageBusAwareProvider.class);

    private static MessageBusAware messageBusAware;
    private static boolean hasGet = false;

    public static MessageBusAware get() {
        if (messageBusAware == null && !hasGet) {
            try {
                Map<String, MessageBusAware> beans = SpringContextHolder.getBeans(MessageBusAware.class);
                if (beans.size() > 0) {
                    messageBusAware = beans.values().iterator().next();
                }
            } catch (Exception ex) {
                LOGGER.error("", ex);
            }
            hasGet = true;
        }
        return messageBusAware;
    }

}
