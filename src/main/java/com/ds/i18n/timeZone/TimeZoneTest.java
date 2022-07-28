package com.ds.i18n.timeZone;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <p>
 *
 * </p>
 *
 * @author dongsheng
 * @date 2022/7/28
 */
public class TimeZoneTest {
    public static void main(String[] args){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String format = formatter.format(ZonedDateTime.now(ZoneId.of("Asia/Shanghai")));
        System.out.println(format);
        String format2 = formatter.format(ZonedDateTime.now(ZoneId.of("America/New_York")));
        System.out.println(format2);
        System.out.println(ZoneId.of("America/New_York"));
    }

}
