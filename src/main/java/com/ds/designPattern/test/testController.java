package com.ds.designPattern.test;

import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: dongsheng
 * @CreateTime: 2022/2/21
 * @Description:
 */
@RestController
public class testController {
    @RequestMapping("/test/getauth")
    public Object getAuth(){
        AuthReq req = new AuthReq();
        IncrReq incrReq = new IncrReq();
        String res= JSON.toJSONString(req)+">>>>"+JSON.toJSONString(incrReq);
        return res;
    }
}
