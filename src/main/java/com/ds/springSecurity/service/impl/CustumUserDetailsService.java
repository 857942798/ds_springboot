package com.ds.springSecurity.service.impl;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author: dongsheng
 * @CreateTime: 2020-11-09
 * @Description:
 */
@Service
public class CustumUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //根据用户名查询用户信息从缓存中取
        //权限设置
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        String role = "user,admin";
        //分割权限名称，如 user,admin
        String[] roles = role.split(",");
        System.out.println("=============================");
        System.out.println("注册该账户权限");
        for (String r :roles){
            System.out.println(r);
            //添加权限
            simpleGrantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+r));
//            simpleGrantedAuthorities.add(new SimpleGrantedAuthority(r));
        }
        System.out.println("=============================");

        /**
         * 创建一个用于认证的用户对象并返回，包括：用户名，密码，角色
         */
        //输入参数
        return new org.springframework.security.core.userdetails.User("user", "123", simpleGrantedAuthorities);
    }
}
