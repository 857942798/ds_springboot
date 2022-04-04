package com.ds.springSecurity.service;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: dongsheng
 * @CreateTime: 2020-11-09
 * @Description: 自定义的service对权限进行控制
 */
public interface RbacService {
    boolean hasPermission(HttpServletRequest request, Authentication authentication);
}
