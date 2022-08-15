package com.ds.i18n.timeZone;

import com.alibaba.fastjson.JSONObject;
import com.ds.i18n.bean.TimeZoneReq;
import com.ds.i18n.bean.TimeZoneResp;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author dongsheng
 * @date 2022/7/28
 */
@RestController
@Slf4j
public class TimeTestController {
    @Autowired
    ObjectMapper objectMapper;

    @GetMapping("gettest")
    public String dateTest(HttpServletRequest request, @RequestParam LocalDateTime date){
        return date.toString();
    }

    @PostMapping("posttest")
    public TimeZoneResp dateTest2(HttpServletRequest request, @RequestBody TimeZoneReq timeZoneBean){
        System.out.println(timeZoneBean.toString());
        LocalDateTime localDateTime = timeZoneBean.getDatetime();
        Date date = timeZoneBean.getDate();
        TimeZoneResp tImeZoneResp=new TimeZoneResp();
        tImeZoneResp.setDate(date);
        tImeZoneResp.setDatetime(localDateTime);
        tImeZoneResp.setDate2(timeZoneBean.getDate2());
        return tImeZoneResp;
    }

    @GetMapping("fastjson_test")
    public JSONObject jsonTest(){
        JSONObject ob=new JSONObject();
        ob.put("date",new Date());
        return ob;
    }

    @GetMapping("fastjsonStr_test")
    public String fastjsonStrTest(){
        JSONObject ob=new JSONObject();
        ob.put("date",new Date());
        return ob.toJSONString();
    }

    @SneakyThrows
    @GetMapping("jackson_test")
    public Map jacksonTest(){
        Map<String,Object> ob=new HashMap<>();
        ob.put("date",new Date());
        return ob;
    }

    @SneakyThrows
    @GetMapping("jacksonStr_test")
    public String jacksonStrTest(){
        Map<String,Object> ob=new HashMap<>();
        ob.put("date",new Date());
        return objectMapper.writeValueAsString(ob);
    }

    @PostMapping("map_test")
    public Map mapTest(@RequestBody Map<String,Date> map){
        return map;
    }


}
