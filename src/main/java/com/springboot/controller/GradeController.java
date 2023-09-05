package com.springboot.controller;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.pojo.Grade;
import com.springboot.pojo.VO.GradeVo;
import com.springboot.pojo.domain.JsonData;
import com.springboot.service.GradeService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/grade")
public class GradeController {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private GradeService gradeService;

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

    @PostMapping("/getGrade")
    public JsonData getGrade(@RequestBody GradeVo vo){
        try {
            PageHelper.startPage(vo.getPageNum(),vo.getPageSize());
          List<Grade> result=  gradeService.getGrade(vo);

            PageInfo<Grade> pageInfo = new PageInfo<>(result);
            return JsonData.success(pageInfo,"查询成功!");

        }catch (Exception e){
            return JsonData.success("查询失败!"+e);
        }

    }
    @GetMapping ("/getGradeName")
    public JsonData getGradeName(){
        try {

            List<Grade> result=  gradeService.getGradeName();
            return JsonData.success(result,"查询成功!");

        }catch (Exception e){
            return JsonData.success("发送失败!"+e);
        }

    }

}
