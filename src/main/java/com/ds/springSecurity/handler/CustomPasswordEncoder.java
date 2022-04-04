package com.ds.springSecurity.handler;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

/**
 * @author: dongsheng
 * @CreateTime: 2020-11-09
 * @Description: 自定义密码加密方式
 */
@Component
public class CustomPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        //加密方法可以根据自己的需要修改
//        String s = DigestUtils.md5DigestAsHex((charSequence.toString() + salt).getBytes());
//        return s;
        //加密方法可以根据自己的需要修改
        String s = charSequence.toString();
        return s;
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return encode(charSequence).equals(s);
    }
}
