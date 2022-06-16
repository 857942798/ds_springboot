package com.ds.controller;

import com.ds.codeOptimize.switchcase.method2.CaseEnum;
import com.ds.codeOptimize.switchcase.method2.CaseInterface;
import com.ds.codeOptimize.switchcase.method2.InitCaseBeanMapComponent;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author dongsheng
 */

@RestController
public class HelloController {

    @SneakyThrows
    @RequestMapping("/")
    public String index() {
        Map<String,Object> data = new HashMap<>();
        CaseInterface caseImpl=InitCaseBeanMapComponent.processMap.get(CaseEnum.ACCEPT);
        String res = caseImpl.execute(data);
        return "Greetings from Spring Boot!";
    }
}
