package com.javayh.common.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.util.ObjectUtils;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>
 *
 * </p>
 *
 * @author Dylan-haiji
 * @version 1.0.0
 * @since 2020-06-11
 */
@Configuration
public class JacksonConfig {

    private final static Logger logger = LoggerFactory.getLogger(JacksonConfig.class);
    private static ObjectMapper objectMapper;
    private static Lock LOCK = new ReentrantLock(true);
    private static AtomicInteger TIME= new AtomicInteger(60);
    private final static Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();

    @Bean
    @Primary
    @ConditionalOnMissingBean(ObjectMapper.class)
    public static ObjectMapper jacksonObjectMapper() {
        if(ObjectUtils.isEmpty(objectMapper)){
            try {
                if(LOCK.tryLock(TIME.get(), TimeUnit.SECONDS) && ObjectUtils.isEmpty(objectMapper)){
                    objectMapper= builder.createXmlMapper(false).build();
                    // 通过该方法对mapper对象进行设置，所有序列化的对象都将按改规则进行系列化
                    // Include.Include.ALWAYS 默认
                    // Include.NON_DEFAULT 属性为默认值不序列化
                    // Include.NON_EMPTY 属性为 空（""） 或者为 NULL 都不序列化，则返回的json是没有这个字段的。这样对移动端会更省流量
                    // Include.NON_NULL 属性为NULL 不序列化
                    objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
                    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                    // 允许出现特殊字符和转义符
                    objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
                    // 允许出现单引号
                    objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
                    // 字段保留，将null值转为""
                    objectMapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>()
                    {
                        @Override
                        public void serialize(Object o, JsonGenerator jsonGenerator,
                                              SerializerProvider serializerProvider)
                                throws IOException
                        {
                            jsonGenerator.writeString("");
                        }
                    });
                }
            } catch (InterruptedException e) {
                logger.error("object mapper try lock ",e);
            }finally {
                LOCK.unlock();
            }
        }
        return objectMapper;
    }
}
