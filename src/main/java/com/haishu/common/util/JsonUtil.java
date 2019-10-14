package com.haishu.common.util;

import com.google.gson.*;
import com.haishu.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @description json工具类
 * @author zhb
 * @createTime 2017年12月4日
 */
@Slf4j
public class JsonUtil {
    private static final Gson GSON = new GsonBuilder().disableHtmlEscaping().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    private static final JsonParser JSON_PARSER = new JsonParser();

    /**
     * @description json字符串转换成对象
     * @param json
     * @param clazz
     * @return
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        T dto = null;
        try {
            dto = GSON.fromJson(json, clazz);
        } catch (JsonSyntaxException e) {
            throw new BusinessException("数据异常");
        }
        return dto;
    }

    /**
     * @description json array字符串转换成List对象
     * @param jsonArray
     * @param clazz
     * @return
     */
    public static <T> List<T> fromJsonArray(String jsonArray, Class<T> clazz) throws BusinessException {
        if (StringUtils.isEmpty(jsonArray) || StringUtils.isEmpty(jsonArray.trim())) {
            return null;
        }
        try {
            JsonArray array = strToJsonArray(jsonArray);
            if (array == null || array.size() == 0) {
                return null;
            }
            List<T> list = new ArrayList<>();
            for (int i = 0; i < array.size(); i++) {
                T dto = GSON.fromJson(array.get(i), clazz);
                list.add(dto);
            }
            return list;
        } catch (JsonSyntaxException e) {
            //log.error("", e);
            throw new BusinessException("数据异常");
        }
    }

    /**
     * @description json array字符串转换成List对象
     * @param jsonArray
     * @param clazz
     * @return
     */
    public static <T> List<T> fromJsonArray(JsonArray jsonArray, Class<T> clazz) throws BusinessException {
        try {
            if (jsonArray == null || jsonArray.size() == 0) {
                return null;
            }
            List<T> list = new ArrayList<>();
            for (int i = 0; i < jsonArray.size(); i++) {
                T dto = GSON.fromJson(jsonArray.get(i), clazz);
                list.add(dto);
            }
            return list;
        } catch (JsonSyntaxException e) {
            throw new BusinessException("数据异常");
        }
    }

    /**
     * @description json字符串转换成对象
     * @param json
     * @param clazz
     * @return
     */
    public static <T> T fromJson(JsonElement json, Class<T> clazz) throws BusinessException {
        T dto = null;
        try {
            dto = GSON.fromJson(json, clazz);
        } catch (JsonSyntaxException e) {
            throw new BusinessException("数据异常");
        }
        return dto;
    }

    /**
     * @description json字符串转换成对象
     * @param json
     * @param clazz
     * @return
     */
    public static <T> T fromJson(JsonElement json, Class<T> clazz, String format) throws BusinessException {
        T dto = null;
        try {
            Gson gson = new GsonBuilder().disableHtmlEscaping().setDateFormat(format).create();
            dto = gson.fromJson(json, clazz);
        } catch (JsonSyntaxException e) {
            throw new BusinessException("数据异常");
        }
        return dto;
    }

    /**
     * @description json字符串转换成对象
     * @param json
     * @param clazz
     * @return
     */
    public static <T> T fromJson(String json, Class<T> clazz, String dateFormat) throws BusinessException {
        T dto = null;
        try {
            Gson gson = new GsonBuilder().disableHtmlEscaping().setDateFormat(dateFormat).create();
            dto = gson.fromJson(json, clazz);
        } catch (JsonSyntaxException e) {
            throw new BusinessException("数据异常");
        }
        return dto;
    }

    public static JsonObject strToJson(String str) {
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        try {
            JsonObject json = JSON_PARSER.parse(str).getAsJsonObject();
            return json;
        } catch (Exception e) {
            return null;
        }
    }

    public static JsonArray strToJsonArray(String str) {
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        try {
            JsonArray json = JSON_PARSER.parse(str).getAsJsonArray();
            return json;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * @description getStringFromJsonByKey
     * @param json
     * @param key
     * @return
     */
    public static String getString(JsonObject json, String key) {
        if (json == null) {
            return null;
        }
        if (key == null) {
            return null;
        }
        if (json.has(key) && !json.get(key).isJsonNull()) {
            return json.get(key).getAsString();
        }
        return null;
    }

    /**
     * @description getBooleanFromJsonByKey
     * @param json
     * @param key
     * @return
     */
    public static Boolean getBoolean(JsonObject json, String key) {
        if (json == null) {
            return null;
        }
        if (key == null) {
            return null;
        }
        if (json.has(key)) {
            return json.get(key).getAsBoolean();
        }
        return null;
    }

    /**
     * @description getDouble
     * @param json
     * @param key
     * @return
     */
    public static Double getDouble(JsonObject json, String key) {
        if (json == null || StringUtils.isEmpty(key)) {
            return null;
        }
        if (json.has(key)) {
            return json.get(key).getAsDouble();
        }
        return null;
    }

    /**
     * @description getInteger
     * @param json
     * @param key
     * @return
     */
    public static Integer getInteger(JsonObject json, String key) {
        if (json == null || StringUtils.isEmpty(key)) {
            return null;
        }
        if (json.has(key)) {
            return json.get(key).getAsInt();
        }
        return null;
    }

    /**
     * @description getInteger
     * @param json
     * @param key
     * @return
     */
    public static Long getLong(JsonObject json, String key) {
        if (json == null || StringUtils.isEmpty(key)) {
            return null;
        }
        if (json.has(key)) {
            return json.get(key).getAsLong();
        }
        return null;
    }

    /**
     * @description getJsonArrayFronJsonByKey
     * @param json
     * @param key
     * @return
     */
    public static JsonObject getJsonObject(JsonObject json, String key) {
        if (json == null) {
            return null;
        }
        if (json.has(key)) {
            try {
                return json.get(key).getAsJsonObject();
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    /**
     * @description getJsonArrayFronJsonByKey
     * @param json
     * @param key
     * @return
     */
    public static JsonArray getJsonArray(JsonObject json, String key) {
        if (json == null) {
            return null;
        }
        if (json.has(key)) {
            try {
                return json.get(key).getAsJsonArray();
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    /**
     * @description 对象转换成json字符串
     * @param obj
     * @return
     */
    public static String toJson(Object obj){
        if (obj == null) {
            return null;
        }
        String result = null;
        try {
            result = GSON.toJson(obj);
        } catch (Exception e) {
            //log.debug("", e);
            return null;
        }
        return result;
    }
    /**
     * @description 对象转换成json字符串
     * @param obj
     * @return
     */
    public static String toJson(Object obj, String dateFormat){
        Gson gson = new GsonBuilder().disableHtmlEscaping().setDateFormat(dateFormat).create();
        if (obj == null) {
            return null;
        }
        String result = null;
        try {
            result = gson.toJson(obj);
        } catch (Exception e) {
            return null;
        }
        return result;
    }

    /**
     * @description 对象转换成json字符串
     * @param json
     * @return
     */
    public static JsonArray toJsonArray(String json) {
        JsonArray ja = null;
        try {
            ja = JSON_PARSER.parse(json).getAsJsonArray();
        } catch (Exception e) {
            throw new BusinessException("json格式不对");
        }
        return ja;
    }

    /**
     * 对象转json对象
     * @param obj
     * @return
     */
    public static JsonObject objToJson(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            String json = toJson(obj);
            return JSON_PARSER.parse(json).getAsJsonObject();
        } catch (Exception e) {
            return null;
        }
    }
}