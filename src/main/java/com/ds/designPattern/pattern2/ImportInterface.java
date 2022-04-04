package com.ds.designPattern.pattern2;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: dongsheng
 * @CreateTime: 2021/11/11
 * @Description:
 */
public interface ImportInterface {

    default void excute(HttpServletRequest request){
        checkData(request);
        transferData(request);
    }

    default void checkData(HttpServletRequest request){
        System.out.println("---------parent checkData");
    }

    default void transferData(HttpServletRequest request){
        System.out.println("---------parent transferData");
    }
}
