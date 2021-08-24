package space.lakers.common.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author mamba
 */
public class JacksonUtils {
    private static final Logger logger = LoggerFactory.getLogger(JacksonUtils.class);

    private static ObjectMapper objectMapper = new ObjectMapper();
    private static ObjectMapper mapper = new ObjectMapper();

    static {
        // 设置输出时包含属性的风格
        objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"));

    }

    static {
        // 设置输出时包含属性的风格
        mapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static String toJSONString(Object object) {
        if (object == null) {
            return StringUtils.EMPTY;
        }
        try {
            return object instanceof String ? (String) object : mapper.writeValueAsString(object);
        } catch (Exception e) {
            logger.error("Object to jsonString error;object={}", object.getClass(), e);
            return StringUtils.EMPTY;
        }
    }

    public static String toJSONString(Object object, ObjectMapper objectMapper) {
        if (ObjectUtils.isEmpty(objectMapper)) {
            objectMapper = new ObjectMapper();
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        }
        if (object == null) {
            return StringUtils.EMPTY;
        }
        try {
            return object instanceof String ? (String) object : objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            logger.error("Object to jsonString error;object={}", object.getClass(), e);
            return StringUtils.EMPTY;
        }
    }

    public static <T> T toBean(String json, Class<T> clazz) {
        if (StringUtils.isBlank(json) || clazz == null) {
            return null;
        }
        try {
            return clazz.equals(String.class) ? (T) json : mapper.readValue(json, clazz);
        } catch (Exception e) {
            logger.error("jsonString to Object error; jsonString={}", json, e);
            return null;
        }

    }

    public static <T> T map2Object(Map map, Class<T> clazz) {
        return mapper.convertValue(map, clazz);
    }

    /**
     * 将json通过类型转换成对象
     *
     * @param json          json字符串
     * @param typeReference 引用类型
     *
     * @return 返回对象
     *
     * @throws IOException
     */
    public static <T> T readJson(String json, TypeReference<T> typeReference) {

        if (StringUtils.isBlank(json) || typeReference == null) {
            return null;
        }
        try {
            return (T) (typeReference.getType().equals(String.class) ? json : mapper.readValue(json, typeReference));
        } catch (Exception e) {
            logger.error("jsonString to Object error;jsonString" + json, e);
            return null;
        }
    }

    public static <T> T readJson(String json, TypeReference<T> typeReference, ObjectMapper objectMapper) {
        if (StringUtils.isBlank(json) || typeReference == null) {
            return null;
        }
        try {
            return (T) (typeReference.getType().equals(String.class) ? json
                                : objectMapper == null ? mapper.readValue(json, typeReference)
                                        : objectMapper.readValue(json, typeReference));
        } catch (Exception e) {
            logger.error("jsonString to Object error;jsonString=" + json, e);
            return null;
        }
    }

    public static JsonNode parseObject(String jsonStr) {
        try {
            return mapper.readTree(jsonStr);
        } catch (Exception e) {
            logger.error("jsonString to JsonNode error;jsonString={}" + jsonStr, e);
            return null;
        }
    }

    private static Map<String, Object> parseJsonToMap(JsonNode rootNode, String keyPre) {
        Map<String, Object> map = Maps.newHashMap();

        if (StringUtils.isNoneBlank(keyPre)) {
            keyPre += ".";
        } else {
            keyPre = "";
        }

        if (rootNode == null) {
            return map;
        }
        Iterator<String> fieldNames = rootNode.fieldNames();

        while (fieldNames.hasNext()) {
            String fieldName = fieldNames.next();
            String key = keyPre + fieldName;
            JsonNode childNode = rootNode.path(fieldName);
            put(childNode, key, map);

        }
        return map;
    }

    /**
     * json node 转换为 map
     *
     * @param json
     *
     * @return
     */
    public static Map<String, Object> asMap(String json) {
        if (StringUtils.isBlank(json)) {
            return Collections.emptyMap();
        }

        try {
            return mapper.readValue(json,
                    mapper.getTypeFactory().constructMapType(HashMap.class, String.class, Object.class));
        } catch (IOException e) {
            String message = String.format("jsonString to Object error;jsonString=%s", json);
            logger.error(message, e);
            return Collections.emptyMap();
        }
    }

    private static Map<String, Object> parseJsonArrayToMap(JsonNode rootNode, String keyPre) {
        Map<String, Object> map = Maps.newHashMap();
        if (keyPre == null) {
            keyPre = "";
        }
        if (!rootNode.isArray()) {
            return map;
        }
        Iterator<JsonNode> elements = rootNode.elements();
        Integer index = 0;
        while (elements.hasNext()) {

            JsonNode element = elements.next();
            String key = keyPre + "[" + index + "]";
            put(element, key, map);
            index++;

        }
        return map;
    }

    private static void put(JsonNode jsonNode, String key, Map<String, Object> map) {
        if (jsonNode.isArray()) {
            map.putAll(parseJsonArrayToMap(jsonNode, key));
        } else if (jsonNode.isTextual()) {
            map.put(key, jsonNode.asText());
        } else if (jsonNode.isNumber()) {
            map.put(key, jsonNode.numberValue());
        } else if (jsonNode.isBoolean()) {
            map.put(key, jsonNode.asBoolean());
        } else if (jsonNode.isObject()) {
            map.putAll(parseJsonToMap(jsonNode, key));
        }
    }

    /**
     * 反序列化为对象
     *
     * @param json
     * @param targetClass
     * @param <T>
     *
     * @return
     *
     * @throws IOException
     */

    public static <T> T unmarshalFromString(String json, Class<T> targetClass) {
        if (StringUtils.isBlank(json) || targetClass == null) {
            return null;
        }
        try {
            return mapper.readValue(json, targetClass);
        } catch (Exception e) {
            logger.error("unmarshalFromString error json:" + json + ", class:" + targetClass.toString(), e);

        }
        return null;
    }

    /**
     * 反序列化为List
     *
     * @param json
     * @param targetClass
     * @param <T>
     *
     * @return
     *
     * @throws IOException
     */
    public static <T> List<T> unmarshalFromString2List(String json, Class<T> targetClass) {

        if (StringUtils.isBlank(json) || targetClass == null) {
            return null;
        }
        try {
            return mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, targetClass));
        } catch (Exception e) {
            logger.error("unmarshalFromString2List error,json:" + json + ", class=" + targetClass.toString(), e);
        }
        return null;
    }

    /**
     * 反序列化为List<Map></>
     *
     * @param json
     *
     * @return
     *
     * @throws IOException
     */
    public static List<Map<String, Object>> unmarshalFromString2List(String json) {
        if (StringUtils.isBlank(json)) {
            return null;
        }
        try {
            return mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, Map.class));
        } catch (Exception e) {
            logger.error("unmarshalFromString2List error,json:" + json, e);
        }
        return null;
    }
}
