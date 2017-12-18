package com.algorithm.$5_json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

/**
 * Title:jackson json utils
 *
 * @author:v_fanhaibo on 2017/12/13.
 * @version:v1.0
 */

public class JsonMapper {


    /** @return json result */
    public static String toJson(Object objToJson) {
        if (!(objToJson instanceof Serializable)) {
            String className = objToJson.getClass().getName();
            throw new RuntimeException("no serialization: " + className);
        }
        try {
            String json = getSingleJsonMapper().writeValueAsString(objToJson);
            return json;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param json eg:[{},{}]
     * @param fromType eg: getObjFromJson("[{},{}]",new TypeReference<List<FindUserDTO>>() {});
     */
    public static <T> T getObjFromJson(String json, TypeReference<T> fromType) {
        try {
            return getSingleJsonMapper().readValue(json, fromType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ObjectMapper getSingleJsonMapper() {
        return SingleTon.getSingleInstance();
    }

    /** static inner class singleton 静态内部类单例模式 */
    private static class SingleTon {
        private final static ObjectMapper mapper = new ObjectMapper();

        public static ObjectMapper getSingleInstance() {
            //convert (non null or empty) getter to json  //非空属性才转json
            mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            return mapper;
        }
    }

    /** single Object to Map  */
    public static Map<String, Object> convertObj2Map(Object obj) {

        BeanInfo beanInfo;
        try {
            beanInfo = Introspector.getBeanInfo(obj.getClass(), Object.class);
        } catch (IntrospectionException e) {
            throw new RuntimeException("JsonMapper: convert object to json IntrospectionException.");
        }
        PropertyDescriptor[] properties = beanInfo.getPropertyDescriptors();
        final Function<PropertyDescriptor, Method> getReadMethod = PropertyDescriptor::getReadMethod;
        List<PropertyDescriptor> list = Arrays.asList(properties);
        Map<String, Object> map = list
                .stream()
                .parallel()
                .filter(propertyDescriptor -> {
                    try {
                        return getReadMethod.apply(propertyDescriptor).invoke(obj) != null;
                    } catch (Exception e) {
                        throw new RuntimeException("JsonMapper: convert object to json error.",e);
                    }

                })
                .collect(toMap(PropertyDescriptor::getName, propertyDescriptor -> {
                    try {
                        return getReadMethod.apply(propertyDescriptor).invoke(obj);
                    } catch (Exception e) {
                        throw new RuntimeException("JsonMapper: convert object to json error.",e);
                    }

                }));

        return map;
    }

    /** List<Object> to List<Map> */
    public static List<Map<String, Object>> listConvertObj2Map(List<?> obj) {
        List<Map<String, Object>> collect = obj.stream()
                .parallel()
                .map(JsonMapper::convertObj2Map)
                .collect(toList());
        return collect;

    }


}
