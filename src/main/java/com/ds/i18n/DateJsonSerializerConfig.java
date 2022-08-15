package com.ds.i18n;

import com.ds.i18n.util.I18nUtils;
import com.ds.util.DateUtils;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * <p>
 *  支持场景：
 *   <br/> 1. POST-application/json请求(RequestBody参数)在使用javabean作为入参时,javabean对象中的Date、LocalDateTime类型可以根据请求头中的时区字段自动转为应用服务当前时区的时间
 *   <br/> 2. 接口返回对象时,对象中的Date、LocalDateTime类型的日期值,可以根据请求头中的时区字段,自动转为该时区的时间 * </p>
 * </p>
 * @author dongsheng
 * @date 2022/8/9
 */
@JsonComponent
public class DateJsonSerializerConfig {

    /**
     * 序列化，Date -> String
     * 返回格式： yyyy-MM-dd HH:mm:ss
     */
    public static class DateJsonSerializer extends JsonSerializer<Date> {
        @Override
        public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            String timeZone = I18nUtils.getTimeZone();
            LocalDateTime time = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
            LocalDateTime localDateTime = time.atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of(timeZone)).toLocalDateTime();
            jsonGenerator.writeString(DateUtils.formatDateTime(localDateTime));
        }
    }

    /**
     * 反序列化，String -> Date <br/>
     * <p>
     *      支持入参格式：<br/>
     *                yyyy-MM-dd HH:mm:ss <br/>
     *                yyyy-MM-dd HH:mm
     * </p>
     *
     */
    public static class DateJsonDeserializer extends JsonDeserializer<Date> {
        @Override
        public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            LocalDateTime localDateTime = DateUtils.parseToLocalDateTime(jsonParser.getText());
            String timeZone = I18nUtils.getTimeZone();
            return Date.from(localDateTime.atZone(ZoneId.of(timeZone)).toInstant());
        }
    }

    /**
     * 反序列化，String —> LocalDateTime
     */
    public static class LocalDateTimeJsonDeserializer extends JsonDeserializer<LocalDateTime> {
        @Override
        public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            LocalDateTime time = DateUtils.parseToLocalDateTime(jsonParser.getText());
            String timeZone = I18nUtils.getTimeZone();
            LocalDateTime localDateTime = time.atZone(ZoneId.of(timeZone)).withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();
            return localDateTime;
        }
    }



    /**
     * 序列化，本地时间转为其它时区的时间
     */
    public static class LocalDateTimeJsonSerializer extends JsonSerializer<LocalDateTime> {
        @Override
        public void serialize(LocalDateTime localDateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            // 本地时间转对应时区的时间
            String timeZone = I18nUtils.getTimeZone();
            LocalDateTime time = localDateTime.atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of(timeZone)).toLocalDateTime();
            jsonGenerator.writeString(DateUtils.formatDateTime(time));
        }
    }

}

