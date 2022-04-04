package com.ds.springSecurity.handler;

import com.alibaba.fastjson.JSON;
import com.ds.domain.JsonData;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author: dongsheng
 * @CreateTime: 2020-11-09
 * @Description: 拒绝访问处理器（登录状态下，无权限会触发）
 */
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        System.out.println("拒绝访问处理器（登录状态下，无权限会触发）");
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        httpServletResponse.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        httpServletResponse.getWriter().write(JSON.toJSONString(JsonData.buildError("拒绝访问处理器（登录状态下，无权限会触发",HttpStatus.FORBIDDEN.value())));
    }
}
