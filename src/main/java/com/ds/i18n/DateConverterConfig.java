package com.ds.i18n;

import cn.hutool.core.date.DateException;
import com.ds.i18n.util.I18nUtils;
import com.ds.util.DateUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * <p>
 *  支持场景：
 *      GET请求及POST表单请求(RequestParam和PathVariable参数)中日期字符串在转为Date、LocalDateTime类型时可以自动转为应用服务当前时区的时间
 * </p>
 *
 * @author dongsheng
 * @date 2022/8/9
 */
@Configuration
public class DateConverterConfig {

    /**
     * 自定义类型转换,HTTP请求日期字符串转换日期类型,
     */
    @Bean
    public Converter<String, LocalDateTime> localDateTimeConverter() {
        return new Converter<String, LocalDateTime>() {
            @Override
            public LocalDateTime convert(String source) {
                try {
                    LocalDateTime localDateTime = DateUtils.parseToLocalDateTime(source);
                    return localDateTime.atZone(ZoneId.of(I18nUtils.getTimeZone())).withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();
                } catch (DateException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
    }

    @Bean
    public Converter<String, Date> dateConverter() {
        return new Converter<String, Date>() {
            @Override
            public Date convert(String source) {
                try {
                    return DateUtils.parseToDate(source,I18nUtils.getTimeZone());
                } catch (DateException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
    }
}
