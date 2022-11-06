package com.panxudong.summerblog.controller;

import com.panxudong.summerblog.util.FileUtil;
import com.panxudong.summerblog.vo.JsonResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author kaza
 * @create 2022-11-01 16:59
 **/
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/file")
public class FileController {

    @PostMapping("/uploads")
    public JsonResult uploads(MultipartFile file) throws IOException {
        String url = FileUtil.uploads(file);
        return JsonResult.data(url);
    }

    /**
     * tinymce编辑器上传
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/tinymce/uploads")
    public Map<String,String> tinymceUploads(MultipartFile file) throws IOException {
        Map<String,String> result=new HashMap<>();
        String url = FileUtil.uploads(file);
        result.put("location",url);
        return result;
    }


}
