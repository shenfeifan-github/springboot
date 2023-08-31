package com.springboot.controller;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.teaopenapi.models.Config;
import com.github.pagehelper.util.StringUtil;
import com.google.gson.Gson;
import com.springboot.pojo.domain.JsonData;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sms")
public class SmsController {
    @Value(value = "${aliyun.AccessKeyId}")
    private String accessKeyId;
    @Value(value = "${aliyun.AccessKeySecret}")
    private String accessKeySecret;
    @Value(value = "${aliyun.SignName}")
    private String signName;

    @ApiOperation(value = "阿里云发送短信")
    @PostMapping ("/send")
    public JsonData send(String phone,String value,String code){
        Config config = new Config()
                // 您的AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 您的AccessKey Secret
                .setAccessKeySecret(accessKeySecret);
                    // 访问的域名
              config.endpoint = "dysmsapi.aliyuncs.com";
              try {
                  Client client = new Client(config);
                  SendSmsRequest request = new SendSmsRequest();
                  request.phoneNumbers= phone;
                  request.signName =signName;
                  request.templateCode=value;
                  JSONObject jsonObject =new JSONObject();
                  if (StringUtil.isNotEmpty(code)) {
                      jsonObject.put("code",code);
                      String json=JSONObject.toJSONString(jsonObject);
                      request.setTemplateParam(json);
                  }
                  SendSmsResponse response = client.sendSms(request);
                  System.out.println(new Gson().toJson(response.body));
              }catch (Exception e) {
                  e.printStackTrace();
                  return JsonData.fail("发送失败"+e);
              }
         return JsonData.success("发送成功!");
    }
}
