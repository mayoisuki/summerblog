package com.panxudong.summerblog.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 文件上传
 * @author kaza
 * @create 2022-11-01 16:51
 **/
public class FileUtil {
    public static final String UPLOADS_PATH="D:\\repository5\\summer_blog\\uploads\\";

    /**
     * 上传文件.返回url
     * @param file
     * @return
     * @throws IOException
     */
    public static String uploads(MultipartFile file) throws IOException {


//        文件后缀名,如jpeg,png,jpg
        final String fileSuffix=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.')+1);

//        文件名
        String fileName = UUID.randomUUID().toString().replace("-", "") + "." + fileSuffix;
//        文件写入
        File descFile=new File(UPLOADS_PATH,fileName);
        file.transferTo(descFile);
//        文件url
        String url="/uploads/"+fileName;
        return url;
    }

}
