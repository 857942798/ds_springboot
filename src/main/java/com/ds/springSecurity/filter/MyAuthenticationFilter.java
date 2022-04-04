package com.ds.springSecurity.filter;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.core.Authentication;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: dongsheng
 * @CreateTime: 2020-11-27
 * @Description:
 */
public class MyAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    public static final String POST = "POST";

    public MyAuthenticationFilter() {
        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login", "POST"));
        this.setAuthenticationManager(getAuthenticationManager());
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!request.getMethod().equals(POST)) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }

        String username = obtainUsername(request);
        String password = obtainPassword(request);

        if (username == null) {
            username = "";
        }

        if (password == null) {
            password = "";
        }

        username = username.trim();
        //主要通过这个token来决定使用哪个userDetailService.
        //UserDetailsAuthenticationProvider里面有个supports方法,主要用来验证指定的token是否符合.
        //可以通过指定不同类型的token来决定使用哪个userDetailService.
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
                username, password);
        // Allow subclasses to set the "details" property
        setDetails(request, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);

        //判断是否是JSON登录
//        String contentType = request.getContentType();
//        if (MediaType.APPLICATION_JSON_UTF8_VALUE.equalsIgnoreCase(contentType)
//                || "application/json; charset=UTF-8".equalsIgnoreCase(contentType)
//                || MediaType.APPLICATION_JSON_VALUE.equals(contentType)) {
//
//            try {
//                //获取提交的JSON数据
//                ServletInputStream ris = request.getInputStream();
//                StringBuilder content = new StringBuilder();
//                byte[] b = new byte[1024];
//                int lens;
//                while ((lens = ris.read(b)) > 0) {
//                    content.append(new String(b, 0, lens));
//                }
//                //从JSON数据中获取用户名和密码
//                JSONObject dataObject = JSONObject.parseObject(content.toString());
//                // 这里的username和password也可以自己指定名称，那么登录的时候就用指定的就行了
//                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
//                        dataObject.getString("username"), dataObject.getString("password"));
//                setDetails(request, token);
//                return this.getAuthenticationManager().authenticate(token);
//            } catch ( IOException e) {
//                return super.attemptAuthentication(request, response);
//            }
//        } else {
//            return super.attemptAuthentication(request, response);
//        }
    }
}
