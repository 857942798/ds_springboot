package com.ds.i18n.util;

import com.ds.i18n.common.I18nConst;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.ZoneId;

/**
 * <p>
 *
 * </p>
 *
 * @author dongsheng
 * @date 2022/8/15
 */
public class I18nUtils {
    /**
     * timeZone字段可来自于三个地方
     * 1、 session的attribute
     * 2、request的attribute
     * 3、url请求参数中
     */
    public static String getTimeZone(){
        String timeZone = (String) RequestContextHolder.getRequestAttributes().getAttribute(I18nConst.TIMEZONE, RequestAttributes.SCOPE_SESSION);
        if(StringUtils.isEmpty(timeZone)){
           timeZone = (String) RequestContextHolder.getRequestAttributes().getAttribute(I18nConst.TIMEZONE, RequestAttributes.SCOPE_REQUEST);
        }
        if(StringUtils.isEmpty(timeZone)){
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            timeZone= servletRequestAttributes.getRequest().getHeader(I18nConst.TIMEZONE);
        }
        if(StringUtils.isEmpty(timeZone)){
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            timeZone=servletRequestAttributes.getRequest().getParameter(I18nConst.TIMEZONE);
        }
        // 没有自定义的timezone则使用系统默认的
        if(StringUtils.isEmpty(timeZone)){
            timeZone= ZoneId.systemDefault().toString();
        }
        return timeZone;
    }


}
