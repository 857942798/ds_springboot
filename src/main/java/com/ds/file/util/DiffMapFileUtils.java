package com.ds.file.util;

import com.alibaba.fastjson.JSON;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * <p>
 *
 * </p>
 *
 * @author dongsheng
 * @date 2022/8/17
 */
public class DiffMapFileUtils {

    public static void main(String[] args){
        System.out.println(JSON.toJSONString(getMovedMap()));
    }

    public static Map<String, String> getMovedMap() {
        File sourceFile = new File("/Users/ds/projects/CSMF-6.3");
        Map<String, String> sourceMap = new HashMap<>();
        sourceMap = getFile(sourceFile, sourceMap);

        File targetFile = new File("/Users/ds/projects/csmf-6.1.x");
        Map<String, String> targetMap = new HashMap<>();
        targetMap = getFile(targetFile, targetMap);

//        // 新增的类
//        Map<String, String> addMap = new HashMap<>();
//        for(Map.Entry<String, String> entry :sourceMap.entrySet()){
//            if(!targetMap.containsKey(entry.getKey())){
//                addMap.put(entry.getKey(),entry.getValue());
//            }
//        }
//        // 删除的类
//        Map<String, String> delMap = new HashMap<>();
//        for(Map.Entry<String, String> entry :targetMap.entrySet()){
//            if(!sourceMap.containsKey(entry.getKey())){
//                delMap.put(entry.getKey(),entry.getValue());
//            }
//        }
        // 移动的类
        Map<String, String> movedMap = new HashMap<>();
        for(Map.Entry<String, String> entry :targetMap.entrySet()){
            if(sourceMap.containsKey(entry.getKey())&&!sourceMap.get(entry.getKey()).equals(entry.getValue())){
                if(!sourceMap.get(entry.getKey()).contains(entry.getValue())){
                    movedMap.put(entry.getKey(),entry.getValue()+"变更为->"+sourceMap.get(entry.getKey()));
                }
            }
        }
        Map<String, String> moveMap = new TreeMap<String, String>();
        moveMap.putAll(movedMap);
        return moveMap;
    }

    public static Map<String, String> getFile(File dir, Map<String, String> map) {
        //将file封装的路径下对象转换为数组
        File[] files = dir.listFiles();
        //判断这个数组为不为空,如果不为空,就执行内部代码
        if (files != null) {
            for (File file : files) {
                // 只遍历java目录下的文件
                if(file.getPath().contains("test")
                        || file.getPath().contains("webapp")
                        || file.getPath().contains("resources")
                        || file.getPath().contains("resources")
                        || file.getPath().contains("target")
                        || file.getPath().contains("demo")
                        || file.getPath().contains(".svn")
                        || file.getPath().contains(".idea")
                        || file.getPath().contains("org")
                        || file.getPath().contains("DS_Store")
                        || file.getPath().contains(".xml")){
                    continue;
                }
                //判断是否为文件
                if (file.isFile()) {
                    //如果为文件, 获取文件名及文件路径
                    if(file.getPath().contains("/java")){
                        String path =file.getPath().split("/java/")[1].replace("/",".");
                        if(map.containsKey(file.getName())){
                            map.put(file.getName(),map.get(file.getName())+";"+path);
                        }else{
                            map.put(file.getName(),path);
                        }
                    }

                } else {
                    //如果不为文件,就(递归)进入这个文件夹,删除文件
                    getFile(file,map);
                }
            }
        }
        return map;
    }

}
