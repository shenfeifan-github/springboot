package com.springboot.controller;

import com.obs.services.ObsClient;
import com.obs.services.exception.ObsException;
import com.springboot.pojo.domain.JsonData;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
@Controller
public class ObsController {
    @Value(value = "${obs.AccessKeyId}")
    private String hwAccessKeyId;
    @Value(value = "${obs.AccessKeySecret}")
    private String hwAccessKeySecret;
    @Value(value = "${obs.endpoint}")
    private String hwEndpoint;
    @Value(value = "${obs.bucketName}")
    private String hwBucketName;
    @ApiOperation(value = "obs上传")
    @PostMapping("obs/upload")
    public JsonData obsUpload(@RequestBody MultipartFile file){
        String objectname = file.getOriginalFilename();
        ObsClient obsClient = new ObsClient(hwAccessKeyId,hwAccessKeySecret,hwEndpoint);
        try {
            obsClient.putObject(hwBucketName, objectname,file.getInputStream());
        } catch (ObsException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
