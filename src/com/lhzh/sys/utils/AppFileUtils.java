package com.lhzh.sys.utils;

import cn.hutool.http.HttpResponse;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Properties;

/**
 * 文件上传工具类
 */
public class AppFileUtils {
    /**
     * 获取文件上传路径
     */
    public static String PATH="G:/upload/";
    static{
        InputStream stream = AppFileUtils.class.getClassLoader().getResourceAsStream("file.properties");
        Properties properties = new Properties();
        try {
            properties.load(stream);
            PATH=properties.getProperty("path");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static ResponseEntity<Object> downloadFile(HttpServletResponse response ,String path ,String oldName){
        //使用绝对路径+相对路径找到文件对象
        File file = new File(AppFileUtils.PATH, path);
        //判断文件是否存在
        if(file.exists()){
            try {
                try {
                    //对中文文件名进行编码
                    URLEncoder.encode(oldName,"UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                //把file转成byte数组
                byte[] bytes = FileUtils.readFileToByteArray(file);
                HttpHeaders header = new HttpHeaders();
                //封装响应内容类型(APPLICATION_OCTET_STREAM 响应的内容不限定)
                header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
                //设置下载的文件的名称
                header.setContentDispositionFormData("attachment",oldName);
                //创建ResponseEntity对象
                ResponseEntity<Object> entity = new ResponseEntity<Object>(bytes,header,HttpStatus.CREATED);
                return entity;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }else {
            PrintWriter out;
            try {
                out=response.getWriter();
                out.write("文件不存在");
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    /**
     * 根据相对路径删除硬盘上文件
     * @param path
     */
    public static void deleteFileUsePath(String path) {
        String realPath =PATH+path;
        File file = new File(realPath);
        if (file.exists()){
            file.delete();
        }
    }
    /**
     * 更改文件名
     * @param carimg
     * @param suffix
     */
    public static String updateFileName(String carimg,String suffix) {
        File file = new File(PATH, carimg);
        if (file.exists()){
            file.renameTo(new File(PATH,carimg.replace(suffix,"")));
            return carimg.replace(suffix,"");
        }
        return null;
    }
}
