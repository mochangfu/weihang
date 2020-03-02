package com.hg.gama.gamautil.numasutil.util;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

public class JsonUtil {
    private static ObjectMapper mapper = new ObjectMapper(); // can reuse, share
    private static ObjectMapper genericMapper = new ObjectMapper(); // can reuse, share
    private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);

    // private static final String component = Character.toString((char) 31);
    // private static final String data = Character.toString((char) 29);
    // private static final String segment = Character.toString((char) 28);
    // 设置或略不存在的字段
    static {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        mapper.setSerializationInclusion(Include.NON_NULL);// 忽略null
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        mapper.setVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.ANY);
        mapper.setVisibility(PropertyAccessor.IS_GETTER, JsonAutoDetect.Visibility.ANY);

        genericMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        genericMapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        genericMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        genericMapper.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);
        genericMapper.setSerializationInclusion(Include.NON_NULL);// 忽略null
        genericMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        genericMapper.setVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.ANY);
        genericMapper.setVisibility(PropertyAccessor.IS_GETTER, JsonAutoDetect.Visibility.ANY);
        genericMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);

    }

    public static ObjectNode createObjectNode() {
        return mapper.createObjectNode();
    }

    public static ArrayNode createArrayNode() {
        return mapper.createArrayNode();
    }

    /**
     * 将对象转成json.
     *
     * @param obj 对象
     * @return
     */
    public static String toJson(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            String str = mapper.writeValueAsString(obj);
            return str;
        } catch (IOException e) {
            logger.warn(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * 将对象转成json.
     *
     * @param obj 对象
     * @return
     */
    public static String toGenericJson(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            String str = genericMapper.writeValueAsString(obj);
            return str;
        } catch (IOException e) {
            logger.warn(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * json转List.
     *
     * @param <T>
     * @param content   json数据
     * @param valueType 泛型数据类型
     * @return
     */
    public static <T> List<T> toListObject(String content, Class<T> valueType) {
        if (content == null || content.isEmpty()) {
            return null;
        }
        try {
            final CollectionType javaType = mapper.getTypeFactory().constructCollectionType(List.class, valueType);
            return mapper.readValue(content, javaType);
        } catch (IOException e) {
            logger.warn("message:" + e.getMessage() + " content:" + content);
            throw new RuntimeException(e);
        }
    }

    public static <T> List<T> toListObject(String content) {
        if (content == null || content.isEmpty()) {
            return null;
        }
        try {
            return mapper.readValue(content, List.class);
        } catch (IOException e) {
            logger.warn("message:" + e.getMessage() + " content:" + content);
            throw new RuntimeException(e);
        }
    }

    public static <T> List<T> toObject(List<String> jsonList, Class<T> valueType) {
        if (jsonList == null || jsonList.isEmpty()) {
            return null;
        }
        List<T> list = new ArrayList<>();
        for (String json : jsonList) {
            list.add(JsonUtil.toObject(json, valueType));
        }
        return list;
    }

    public static JsonNode toObject(String json) {
        if (json == null || json.isEmpty()) {
            return null;
        }
        try {
            return mapper.readTree(json);
        } catch (IOException e) {
            logger.warn("message:" + e.getMessage() + " json:" + json);
            throw new RuntimeException(e);
        }
    }

    public static Object toGenericObject(String json) {
        if (json == null || json.isEmpty()) {
            return null;
        }
        try {
            return genericMapper.readTree(json);
        } catch (IOException e) {
            logger.warn("message:" + e.getMessage() + " json:" + json);
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> toMap(String content) {
        return JsonUtil.toObject(content, Map.class);
    }

    @SuppressWarnings("unchecked")
    public static Set<Object> toSet(String content) {
        return JsonUtil.toObject(content, Set.class);
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> toNotNullMap(String json) {
        Map<String, Object> map = JsonUtil.toObject(json, Map.class);
        if (map == null) {
            map = new LinkedHashMap<>();
        }
        return map;
    }

    @SuppressWarnings("unchecked")
    public static <T> Map<String, T> toNotNullMap(String json, Class<T> clazz) {
        Map<String, T> map = JsonUtil.toObject(json, Map.class);
        if (map == null) {
            map = new LinkedHashMap<>();
        }
        return map;
    }

    @SuppressWarnings("unchecked")
    public static <T> Set<T> toNotNullSet(String json, Class<T> clazz) {
        Set<T> set = JsonUtil.toObject(json, Set.class);
        if (set == null) {
            set = new LinkedHashSet<>();
        }
        return set;
    }

    /**
     * 类型转换.
     *
     * @param obj
     * @param clazz
     * @return
     */
    public static <T> T convert(Object obj, Class<T> clazz) {
        String json = JsonUtil.toJson(obj);
        return toObject(json, clazz);
    }

    /**
     * 将Json转换成对象.
     *
     * @param json
     * @return
     */
    public static <T> T toObject(String json, Class<T> clazz) {
        if (json == null || json.isEmpty()) {
            return null;
        }
        try {
            return mapper.readValue(json, clazz);
        } catch (IOException e) {
            logger.warn("message:" + e.getMessage() + " json:" + json);
            throw new RuntimeException(e);
        }
    }

    public static String getObjectJson(Object value) {
        if (value == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            logger.error("", e);
        }
        return json;
    }

    public static ObjectMapper getMapper() {
        return mapper;
    }

    public static void print(Object obj) {
        String json = JsonUtil.toJson(obj);
        System.out.println(json);
    }

    public static void print(Object obj, String name) {
        String json = JsonUtil.toJson(obj);
        System.out.println("json info " + name + "::" + json);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static void printMap(Map map, String name) {
        if (map == null) {
            System.out.println("json info " + name + "::null");
            return;
        }
        if (map.isEmpty()) {
            System.out.println("json info " + name + "::");
            return;
        }
        for (Entry entry : (Iterable<Entry>) map.entrySet()) {
            Object key = entry.getKey();
            Object value = entry.getValue();
            System.out.println("key:" + key + " json:" + JsonUtil.toJson(value));
        }

    }

    @SuppressWarnings("rawtypes")
    public static void printList(List list, String name) {
        if (list == null) {
            System.out.println("json info " + name + "::null");
            return;
        }
        if (list.isEmpty()) {
            System.out.println("json info " + name + "::");
            return;
        }
        for (Object element : list) {
            System.out.println("json info " + name + "::" + JsonUtil.toJson(element));
        }

    }

    public static boolean mayBeJSONMap(String string) {
        return string != null &&
                ("null".equals(string) || string.startsWith("{") && string.endsWith("}"));
    }

    public static boolean mayBeJSONArray(String string) {
        return string != null &&
                string.startsWith("[") && string.endsWith("]");
    }
}
