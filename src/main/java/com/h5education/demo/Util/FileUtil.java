package com.h5education.demo.Util;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;

@Component
public class FileUtil {
    //文件上传工具类服务方法
    public static boolean uploadFile(byte[] file,String filePath,String fileName)  {
        File targetFile = new File(filePath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        try {
            FileOutputStream out = new FileOutputStream(filePath + fileName);
            out.write(file);
            out.flush();
            out.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }


    public static boolean deleteFile(String filePath){
        File deletefile = new File(filePath);
        if (!deletefile.exists()){
            return false;
        }
        try {
            deletefile.delete();
        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }
}
