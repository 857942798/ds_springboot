package com.ds.designPattern.pattern1;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author: dongsheng
 * @CreateTime: 2021/11/11
 * @Description:
 */
@RestController
public class CusImport extends Import{

    @RequestMapping(value = "/cusimport", method = {RequestMethod.GET, RequestMethod.POST})
    @Override
    public void main(HttpServletRequest request) {
        super.main(request);
    }


    @Override
    public void checkData(HttpServletRequest request){
        System.out.println("------cus checkData");
    }

    @Override
    public void transferData(HttpServletRequest request){
        System.out.println("------cus transferData");
    }
}
