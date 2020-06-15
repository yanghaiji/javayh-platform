package com.javayh.common.util.json;

import cn.hutool.json.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javayh.common.config.JacksonConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.lang.Nullable;
import org.springframework.util.ObjectUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.URL;
import java.nio.charset.StandardCharsets;


/**
 * <p>
 *      json util
 * </p>
 * @author Dylan-haiji
 * @version 1.0.0
 * @since 2020-06-11
 */
public class JsonUtils<T>{

    private static Logger logger = LoggerFactory.getLogger(JsonUtils.class);

    /**
     * <p>
     *       Json to Bean
     * </p>
     * @version 1.0.0
     * @author Dylan-haiji
     * @since 2020/6/11
     * @param string
     * @param bean
     * @return T
     */
    public static <T> T jsonToBean(@Nullable String string, Class<T> bean){
        assertNotNull("json to bean ", string);
        ObjectMapper objectMapper = JacksonConfig.jacksonObjectMapper();
        T t = null;
        try {
            t = objectMapper.readValue(string, bean);
        } catch (JsonProcessingException e) {
            logger.info("json to object is error -> JsonProcessingException ",e);
        }
        return t;
    }

    /**
     * <p>
     *       bean to json
     * </p>
     * @version 1.0.0
     * @author Dylan-haiji
     * @since 2020/6/11
     * @param bean
     * @return java.lang.String
     */
    public static <T> String beanToJson(@Nullable T bean){
        assertNotNull("bean to json ",bean);
        ObjectMapper objectMapper = JacksonConfig.jacksonObjectMapper();
        String t = null;
        try {
            t =  objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(bean);
        } catch (JsonProcessingException e) {
            logger.info("json to object is error -> JsonProcessingException ",e);
        }
        return t;
    }

    /**
     * <p>
     *       JSON with generics
     * </p>
     * @version 1.0.0
     * @author Dylan-haiji
     * @since 2020/6/11
     * @param json          "[{\"name\":\"haiji\", \"age\":21},{\"name\":\"haiji\", \"age\":21}]"
     * @param typeReference  new TypeReference<List<User>>() {})
     * @return void
     */
    public static <T> T jsonToBean(@Nullable String json, TypeReference<T> typeReference){
        assertNotNull("to json generics",json);
        ObjectMapper objectMapper = JacksonConfig.jacksonObjectMapper();
        T t = null;
        try {
            t = objectMapper.readValue(json, typeReference);
        } catch (IOException e) {
            logger.error("json to object is exception -> ",e);
        }
        return t;
    }

    /**
     * <p>
     *       bean to file json
     * </p>
     * @version 1.0.0
     * @author Dylan-haiji
     * @since 2020/6/11
     * @param file
     * @param bean
     */
    public static <T> void beanToJson(@Nullable File file, T bean){
        assertNotNull("to file",file);
        ObjectMapper objectMapper = JacksonConfig.jacksonObjectMapper();
        try {
            objectMapper.writeValue(file,bean);
        } catch (IOException e) {
            logger.info("json to object is error -> IOException ",e);
        }
    }

    /**
     * <p>
     *       file json to bean
     * </p>
     * @version 1.0.0
     * @author Dylan-haiji
     * @since 2020/6/11
     * @param file new File("test.json");
     * @return T
     */
    public static <T> T jsonToBean(@Nullable File file, TypeReference<T> typeReference){
        assertNotNull("to file",file);
        ObjectMapper objectMapper = JacksonConfig.jacksonObjectMapper();
        T t = null;
        try {
            t =  objectMapper.readValue(file,typeReference);
        } catch (IOException e) {
            logger.error("json to object exception -> ",e);
        }
        return t;
    }

    /**
     * <p>
     *       json to tree
     * </p>
     * @version 1.0.0
     * @author Dylan-haiji
     * @since 2020/6/11
     * @param json
     * @return com.fasterxml.jackson.databind.JsonNode
     */
    public static <T> JsonNode jsonToTree(@Nullable String json){
        assertNotNull("to tree",json);
        ObjectMapper objectMapper = JacksonConfig.jacksonObjectMapper();
        JsonNode jsonNode =null;
        try {
            jsonNode = objectMapper.readTree(json);
        } catch (JsonProcessingException e) {
            logger.error("json to tree exception ->",e);
        }
        return jsonNode;
    }


    /**
     * <p>
     *       url to json
     * </p>
     * @version 1.0.0
     * @author Dylan-haiji
     * @since 2020/6/11
     * @param url
     * @param bean
     * @return T
     */
    public static <T> T jsonToBean(@Nullable URL url, Class<T> bean){
        assertNotNull("url",url);
        ObjectMapper objectMapper = JacksonConfig.jacksonObjectMapper();
        T t = null;
        try {
            t = objectMapper.readValue(url, bean);
        } catch (IOException e) {
            logger.info("url to object is error -> IOException ",e);
        }
        return t;
    }

    /**
     * <p>
     *
     * </p>
     * @version 1.0.0
     * @author Dylan-haiji
     * @since 2020/6/11
     * @param url
     * @param bean
     * @return T
     */
    public static <T> T jsonToBean(@Nullable URL url, TypeReference<T> bean){
        assertNotNull("url generic paradigm",url);
        ObjectMapper objectMapper = JacksonConfig.jacksonObjectMapper();
        T t = null;
        try {
            t = objectMapper.readValue(url, bean);
        } catch (IOException e) {
            logger.info("url to object is error -> IOException ",e);
        }
        return t;
    }


    /**
     * <p>
     *       inputStream to bean
     * </p>
     * @version 1.0.0
     * @author Dylan-haiji
     * @since 2020/6/11
     * @param inputStream
     * @param bean
     * @return T
     */
    public static <T> T inputStreamToBean(@Nullable InputStream inputStream, Class<T> bean){
        assertNotNull("inputStream class",inputStream);
        ObjectMapper objectMapper = JacksonConfig.jacksonObjectMapper();
        T t = null;
        try {
            t = objectMapper.readValue(inputStream, bean);
        } catch (IOException e) {
            logger.info("url to object is error -> IOException ",e);
        }
        return t;
    }

    /**
     * <p>
     *       inputStream to bean
     * </p>
     * @version 1.0.0
     * @author Dylan-haiji
     * @since 2020/6/11
     * @param inputStream
     * @param bean
     * @return T
     */
    public static <T> T inputStreamToBean(@Nullable InputStream inputStream, TypeReference<T> bean){
        assertNotNull("inputStream",inputStream);
        ObjectMapper objectMapper = JacksonConfig.jacksonObjectMapper();
        T t = null;
        try {
            t = objectMapper.readValue(inputStream, bean);
        } catch (IOException e) {
            logger.info("url to object is error -> IOException ",e);
        }
        return t;
    }

    public static <T> T byteToBean(@Nullable byte[] src, Class<T> bean){
        assertNotNull("byte class",src);
        ObjectMapper objectMapper = JacksonConfig.jacksonObjectMapper();
        T t = null;
        try {
            t = objectMapper.readValue(src, bean);
        } catch (IOException e) {
            logger.info("url to object is error -> IOException ",e);
        }
        return t;
    }

    public static <T> T byteToBean(@Nullable byte[] src, TypeReference<T> bean){
        assertNotNull("byte TypeReference",src);
        ObjectMapper objectMapper = JacksonConfig.jacksonObjectMapper();
        T t = null;
        try {
            t = objectMapper.readValue(src, bean);
        } catch (IOException e) {
            logger.info("url to object is error -> IOException ",e);
        }
        return t;
    }

    /**
     * <p>
     *
     * </p>
     * @version 1.0.0
     * @author Dylan-haiji
     * @since 2020/6/15
     * @param path
     * @param value
     * @return T
     */
    public static <T> void readJsonFromClassPath(String path, T value)  {
        ClassPathResource resource = new ClassPathResource(path);
        if (resource.exists()) {
            try {
                beanToJson(new File(path),value);
            } catch (Exception e) {
                logger.error("read json file ",e);
            }
        }
    }

    /**
     * <p>
     *       not null
     * </p>
     * @version 1.0.0
     * @author Dylan-haiji
     * @since 2020/6/11
     * @param paramName
     * @param src
     * @return void
     */
    protected static void assertNotNull(String paramName, Object src) {
        if (ObjectUtils.isEmpty(src)) {
            throw new IllegalArgumentException(String.format("argument \"%s\" is null", paramName));
        }
    }
}
