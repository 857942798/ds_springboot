package com.ds.file.util;

import com.alibaba.fastjson.JSON;

import java.io.File;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * <p>
 *
 * </p>
 *
 * @author dongsheng
 * @date 2022/8/17
 */
public class DiffTreeFileUtils {

    public static void main(String[] args){
        File sourceFile = new File("/Users/ds/projects/CSMF-6.3");
        Set<String> sourceTree = new TreeSet<>();
        sourceTree = getFile(sourceFile, sourceTree);

        File targetFile = new File("/Users/ds/projects/csmf-6.1.x");
        Set<String> targetTree = new TreeSet<>();
        targetTree = getFile(targetFile, targetTree);

        // 新增的类sourceTree-targetTree
        Set<String> addTree=new TreeSet<>();
        addTree.addAll(sourceTree);
        addTree.removeAll(targetTree);
        System.out.println("新增的类："+JSON.toJSONString(addTree));
        // 删除的类targetTree-sourceTree
        Set<String> delTree=new TreeSet<>();
        delTree.addAll(targetTree);
        delTree.removeAll(sourceTree);
        // 移动的类
        System.out.println("删除的类："+JSON.toJSONString(delTree));
        Map<String, String> movedMap = DiffMapFileUtils.getMovedMap();
        Set<String> delAns=new TreeSet<>();
        delTree.forEach(path->{
            String filePath = path.split(".java")[0];
            int last = filePath.lastIndexOf(".")+1;
            String fileName=filePath.substring(last);
            if(!movedMap.containsKey(fileName+".java")){
                delAns.add("删除`"+fileName+"`类。类路径：`"+filePath+"`");
            }
        });
        System.out.println("删除类："+JSON.toJSONString(delAns));
    }



    public static Set<String> getFile(File dir, Set<String> treeSet) {
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
                        || file.getPath().contains(".xml")
                        || file.getPath().contains(".ssh")
                        || file.getPath().contains("META-INF")){
                    continue;
                }
                //判断是否为文件
                if (file.isFile()) {
                    //如果为文件, 获取文件名及文件路径
                    if(file.getPath().contains("/java")){
                        String path =file.getPath().split("/java/")[1].replace("/",".");
                        treeSet.add(path);
                    }
                } else {
                    //如果不为文件,就(递归)进入这个文件夹,删除文件
                    getFile(file,treeSet);
                }
            }
        }
        return treeSet;
    }

}
