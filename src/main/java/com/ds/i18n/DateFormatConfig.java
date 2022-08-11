package com.ds.i18n;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

@JsonComponent
public class DateFormatConfig {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 序列化，日期格式化
     */
    public static class DateJsonSerializer extends JsonSerializer<Date> {
        @Override
        public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            String timeZone = (String) RequestContextHolder.getRequestAttributes().getAttribute("timeZone", RequestAttributes.SCOPE_SESSION);
            dateFormat.setTimeZone(TimeZone.getTimeZone(ZoneId.of(timeZone)));
            jsonGenerator.writeString(dateFormat.format(date));
        }
    }

    /**
     * 反序列化，解析日期字符串
     */
    public static class DateJsonDeserializer extends JsonDeserializer<Date> {
        @Override
        public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            try {
                String timeZone = (String) RequestContextHolder.getRequestAttributes().getAttribute("timeZone", RequestAttributes.SCOPE_SESSION);
                dateFormat.setTimeZone(TimeZone.getTimeZone(ZoneId.of(timeZone)));
                Date parse = dateFormat.parse(jsonParser.getText());
                return parse;
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

        }
    }

    /**
     * 反序列化，其它时区的时间转为本地时间
     */
    public static class LocalDateTimeJsonDeserializer extends JsonDeserializer<LocalDateTime> {
        @Override
        public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            String date = jsonParser.getText();
            String timeZone = (String) RequestContextHolder.getRequestAttributes().getAttribute("timeZone", RequestAttributes.SCOPE_SESSION);
            DateTimeFormatter dateTimeFormatter= DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime time = LocalDateTime.parse(date, dateTimeFormatter);
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
            String timeZone = (String) RequestContextHolder.getRequestAttributes().getAttribute("timeZone", RequestAttributes.SCOPE_SESSION);
            LocalDateTime time = localDateTime.atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of(timeZone)).toLocalDateTime();
            DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            jsonGenerator.writeString(time.format(dateTimeFormatter));
        }
    }

}

