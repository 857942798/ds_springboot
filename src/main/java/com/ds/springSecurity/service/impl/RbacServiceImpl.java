package com.ds.springSecurity.service.impl;

import com.ds.springSecurity.service.RbacService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: dongsheng
 * @CreateTime: 2020-11-09
 * @Description: 自定义用户访问权限
 */
@Service
public class RbacServiceImpl implements RbacService {
    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Object principal = authentication.getPrincipal();
        boolean hasPermission = false;
        if (principal instanceof UserDetails) { //首先判断先当前用户是否是我们UserDetails对象。
            String userName = ((UserDetails) principal).getUsername();
            //从缓存中获取用户有权限访问的url
            hasPermission = true;
            List<String> urls = new ArrayList<>();
            // 注意这里不能用equal来判断，因为有些URL是有参数的，所以要用AntPathMatcher来比较
            for (String url : urls) {
                if (antPathMatcher.match(url, request.getRequestURI())) {
                    hasPermission = true;
                    break;
                }
            }
        }
        return true;
    }
}
