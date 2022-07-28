package com.ds.file;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONObject;
import com.ds.utils.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.*;

/**
 * @author: dongsheng
 * @CreateTime: 2022/5/9
 * @Description: 生成文件测试
 */
@RestController
public class CreateFileTest {
    LinkedBlockingQueue<Runnable> queues = new LinkedBlockingQueue<>(1000000);
    ThreadPoolExecutor excutor = new ThreadPoolExecutor(
            20,
            40,
            3L,
            TimeUnit.SECONDS,
            queues
    );
    @Value("${create.file.path}")
    String path;

    @GetMapping("createfile/{num}")
    public void createfile(@PathVariable Integer num){
        for (int i = 0; i < num; i++) {
            excutor.submit(()->{
                create();
            });
        }
    }

    @GetMapping("getfile")
    public String getfile(){
        File dir = new File(path);
        DataFileFilter tmp = new DataFileFilter("task");
        String[] list = dir.list();
        int length=  list.length;
        System.out.println(length);
        return "success";
    }

    private void create(){
        File dir = new File(path);
        if (!dir.exists()){
            dir.mkdirs();
        }
        Snowflake snowflake = IdUtil.getSnowflake(1, 1);
        long id=snowflake.nextId();
        String fileName=id+".txt";
        JSONObject json = new JSONObject();
        json.put("id",id);
        json.put("fileName",fileName);
        FileUtils.save(fileName,json.toJSONString());
    }
}
