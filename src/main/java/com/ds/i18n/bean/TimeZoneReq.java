package com.ds.i18n.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author dongsheng
 * @date 2022/8/9
 */
@Data
public class TimeZoneReq {
    private LocalDateTime datetime;

    private Date date;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+12")
    private Date date2;

}
