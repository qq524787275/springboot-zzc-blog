package com.zhuzichu.blog.controller;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.zhuzichu.blog.core.result.Result;
import com.zhuzichu.blog.core.result.ResultGenerator;
import com.zhuzichu.blog.utils.ConstantQiniu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
@RequestMapping("/api/file")
public class FileController {
    private Logger logger = LoggerFactory.getILoggerFactory().getLogger(this.getClass().getName());

    @Autowired
    private ConstantQiniu constantQiniu;

    @PostMapping("/upload")
    @ResponseBody
    public Result upload(@RequestParam("file") MultipartFile multipartFile) {
        try {
            ByteArrayInputStream inputStream = (ByteArrayInputStream) multipartFile.getInputStream();
            String fileName=multipartFile.getOriginalFilename();
            String fileType=fileName.substring(fileName.lastIndexOf("."),fileName.length());
            String path = uploadQNImg(inputStream,System.currentTimeMillis()+fileType);
            if (path != null) {
                logger.info(path);
                return ResultGenerator.genSuccessResult(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResultGenerator.genFailResult("上传失败");
    }

    @PostMapping("/uptoken")
    public Result uptoken() {
        Auth auth = Auth.create(constantQiniu.getAccessKey(), constantQiniu.getSecretKey());
        String uptoken = auth.uploadToken(constantQiniu.getBucket());
        return ResultGenerator.genSuccessResult(uptoken);
    }

    private String uploadQNImg(ByteArrayInputStream file, String key) {
        // 构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone0());
        // 其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        // 生成上传凭证，然后准备上传
        try {
            Auth auth = Auth.create(constantQiniu.getAccessKey(), constantQiniu.getSecretKey());
            String upToken = auth.uploadToken(constantQiniu.getBucket());
            try {
                Response response = uploadManager.put(file, key, upToken, null, null);
                // 解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);

                String returnPath = constantQiniu.getPath() + "/" + putRet.key;
                return returnPath;
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
