package com.ds.springSecurity.handler;

import com.alibaba.fastjson.JSON;
import com.ds.domain.JsonData;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author: dongsheng
 * @CreateTime: 2020-11-09
 * @Description:登录失败处理器
 */
@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        if (e instanceof AccountExpiredException) {
            //账号过期
        } else if (e instanceof BadCredentialsException) {
            //密码错误
        } else if (e instanceof CredentialsExpiredException) {
            //密码过期
        } else if (e instanceof DisabledException) {
            //账号不可用
        } else if (e instanceof LockedException) {
            //账号锁定
        } else if (e instanceof InternalAuthenticationServiceException) {
            //用户不存在
        }else{
            //其他错误
        }
        //登录失败
        JsonData response = new JsonData();
        response.setCode(-1);
        response.setMsg(e.getMessage()+"登录认证失败");
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        httpServletResponse.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        httpServletResponse.getWriter().write(JSON.toJSONString(response));
    }
}