package com.ds.springSecurity.handler;

import com.alibaba.fastjson.JSON;
import com.ds.domain.JsonData;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author: dongsheng
 * @CreateTime: 2020-11-09
 * @Description:设置完成登录成功和失败处理后，还是不够满足需求，
 * 当用户未通过登录页进入网站，我们需要在用户直接访问资源时，
 * 告诉前端此用户未认证。（默认是302跳转到登录页）。我们可以改成返回403状态码
 */
@Component
public class OnAuthenticationHandler implements AuthenticationEntryPoint {

    /*
     * 认证入口（未登录状态）登录超时或者未登录的异常处理器
     */
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        JsonData response = new JsonData();
        response.setCode(HttpServletResponse.SC_UNAUTHORIZED);
        System.out.println("authenticate fail,认证抛出异常，认证入口（未登录状态）");
        response.setMsg("authenticate fail,认证抛出异常，认证入口（未登录状态）");
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        httpServletResponse.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        httpServletResponse.getWriter().write(JSON.toJSONString(response));
    }
}
