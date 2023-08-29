package com.springboot.controller;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.pojo.Grade;
import com.springboot.pojo.domain.JsonData;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/grade")
public class GradeController {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @ApiOperation(value = "添加班级信息")
    @PostMapping("/saveGrade")
    public JsonData saveGrade(@RequestBody Grade grade){
        try {
            String message = JSON.toJSONString(grade);
            rabbitTemplate.convertAndSend("SFF_EXCHANGE","product.add",message);
            log.info("发送添加班级信息:{}", message);
            return JsonData.success("发送成功!");
        }catch (Exception e){
            return JsonData.success("发送失败!"+e);
        }

    }
}
