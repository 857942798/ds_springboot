package com.ds.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

import java.io.*;

/**
 * @author: dongsheng
 * @CreateTime: 2022/5/20
 * @Description:
 */
@Component
public class FileUtils {

    private static String path="/Users/ds/Downloads/dcp/file";
    private static String TMP=path+"/tmp";
    private static String TASK=path+"/task";
    private static String SUCCESS=path+"/success";
    private static String ERROR=path+"/error";

    static  {
        File dir = new File(path);
        if(!dir.exists()){
            dir.mkdirs();
            new File(TMP).mkdirs();
            new File(TASK).mkdirs();
            new File(SUCCESS).mkdirs();
            new File(ERROR).mkdirs();
        }
    }

    /**
     * @param fileName:文件名
     * @param data:Json字符串
     * @throws IOException:
     * @description
     * tmp临时目录
     * 1. 在tmp临时目录下新建一个文件
     * 2. 向文件中写入指令json信息
     * 3. 将该文件从tmp目录移动到task目录中
     */
    public static Boolean save(String fileName,String data){
        BufferedWriter bufferedWriter=null;
        FileWriter fileWriter=null;
        try {
            File file = new File(TMP,fileName);
            Boolean saveFlag=true;
            if(!file.exists()){
                saveFlag=file.createNewFile();
                file.setReadable(true);
                file.setWritable(true);
            }
            if(saveFlag){
                // false: 覆盖模式，true：追加模式
                fileWriter=new FileWriter(file, false);
                bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(data);
                bufferedWriter.newLine();
                bufferedWriter.flush();
                bufferedWriter.close();
                file.renameTo(new File(TASK,fileName));
            }
            return saveFlag;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }finally {
            try {
                if (bufferedWriter !=null){
                    bufferedWriter.close();
                }
                if (fileWriter !=null){
                    fileWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
    }

    /**
     * @param fileName
     * task目录下
     * 1. 读取文件内容
     * 2. 构造放入队列的请求体
     * 3. 更改文件后缀为.task
     */
    public static JSONObject get(String fileName){
        File taskFile = new File(TASK,fileName);
        if (taskFile.isFile() && taskFile.exists()) {
            try {
                InputStreamReader read = null;
                read = new InputStreamReader(new FileInputStream(taskFile), "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                lineTxt = bufferedReader.readLine();
                while (lineTxt != null) {
                    return JSON.parseObject(lineTxt);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * @param fileName
     * task目录下，根据文件名删除文件
     */
    public static void delete(String fileName){
    }

    /**
     * @param fileName
     * 将文件从task移动到error目录
     */
    public static void moveToError(String fileName){

    }

    /**
     * @param fileName
     * 将文件从task移动到success目录
     */
    public static void moveToSuccess(String fileName){

    }
}
