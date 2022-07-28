package com.ds.file;

import java.io.File;
import java.io.FilenameFilter;

/**
 * @author: dongsheng
 * @CreateTime: 2022/5/17
 * @Description:
 */
public class DataFileFilter implements FilenameFilter {

    String extension;
    DataFileFilter(String extension){
        this.extension = extension;
    }

    public boolean accept(File directory, String filname) {
        // TODO Auto-generated method stub
        System.out.println(filname);
        return filname.endsWith(extension);
    }

}