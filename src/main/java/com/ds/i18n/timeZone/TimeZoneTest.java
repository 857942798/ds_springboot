package com.ds.i18n.timeZone;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

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
        String sh = formatter.format(ZonedDateTime.now(ZoneId.of("Asia/Shanghai")));
        System.out.println(sh);
        String format2 = formatter.format(ZonedDateTime.now(ZoneId.of("America/New_York")));
        System.out.println(format2);
        System.out.println(ZoneId.of("America/New_York"));

        SimpleDateFormat fdt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            java.sql.Date date = new java.sql.Date(fdt.parse(sh).getTime() + 8 * 3600 * 1000l);
            String result = fdt.format(date);
            System.out.println(result);
            fdt.setTimeZone(TimeZone.getTimeZone(ZoneId.of("America/New_York").getId()));
            String result2 = fdt.format(date);
            System.out.println(result2);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


        long l = System.currentTimeMillis();

    }

}
