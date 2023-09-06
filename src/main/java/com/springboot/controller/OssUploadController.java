package com.springboot.controller;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.common.utils.DateUtil;
import com.aliyun.oss.model.Callback;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.springboot.pojo.domain.JsonData;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@RestController
@CrossOrigin
@Api(value = "OSS上传")
public class OssUploadController {
    @Value(value = "${aliyun.AccessKeyId}")
    private String accessKeyId;
    @Value(value = "${aliyun.AccessKeySecret}")
    private String accessKeySecret;
    @Value(value = "${aliyun.oss.endpoint}")
    private String endpoint;
    @Value(value = "${aliyun.oss.bucketName}")
    private String bucketName;


    @PostMapping("/ossUpload")
    public JsonData ossUpload(@RequestBody MultipartFile file) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format1 = new SimpleDateFormat("HHmmss");
        //获取原文件名称
        String originalFileName = file.getOriginalFilename();
        String suffix = originalFileName.substring(originalFileName.lastIndexOf("."));
        String prefix = format.format(new Date());
        String fileName = format1.format(new Date());
        String objectName = prefix + "/" + fileName + suffix;
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        String result = null;
        try {
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, file.getInputStream());
            ossClient.putObject(putObjectRequest);
            //获取上传文件的路径

            URL url = ossClient.generatePresignedUrl(bucketName, objectName, new Date());

            int index = url.toString().indexOf("?");
            result = url.toString().substring(0, index);
        } catch (OSSException oe) {
            log.info("上传异常:" + oe);
        } catch (Throwable ce) {
            log.info("上传异常:" + ce);
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return JsonData.success(result, "上传成功");
    }
}
