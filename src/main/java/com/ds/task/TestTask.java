package com.ds.task;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author dongsheng
 * 定时任务任务类
 */
@Component
public class TestTask {

//    @Scheduled(fixedRate = 2000)//两秒执行一次
//    @Scheduled(cron = "*/4 * * * * *")
    private void sum(){
        System.out.println("当前时间："+new Date());
    }

}
