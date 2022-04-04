package com.ds.designPattern.pattern1;

import org.springframework.ui.Model;
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
public class Import {

    @RequestMapping(value = "/import", method = {RequestMethod.GET, RequestMethod.POST})
    public void main(HttpServletRequest request){
        checkData(request);
        transferData(request);
    }

    public void checkData(HttpServletRequest request){
        System.out.println("---------parent checkData");
    }

    public void transferData(HttpServletRequest request){
        System.out.println("---------parent transferData");
    }
}
