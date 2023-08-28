package com.springboot.controller;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.pojo.Dto.UserDto;
import com.springboot.pojo.Grade;
import com.springboot.pojo.User;
import com.springboot.pojo.VO.UserVo;
import com.springboot.pojo.domain.JsonData;
import com.springboot.service.GradeService;
import com.springboot.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private GradeService gradeService;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @ApiOperation(value = "获取用户")
    @PostMapping("/getUser")
    public JsonData getUser(@RequestBody UserVo vo){
        PageHelper.startPage(vo.getPageNum(),vo.getPageSize());
        List<UserDto> result = userService.getUser(vo);
        for (UserDto userDto:result){
             if (userDto.getClassNumber() !=null){
             Grade grade= gradeService.getGrade(userDto.getClassNumber());
                 userDto.setGrade(grade);
             }
        }
        PageInfo<UserDto> pageInfo = new PageInfo<>(result);
       return JsonData.success(pageInfo,"查询成功!");
    }


    @ApiOperation(value = "添加用户")
    @PostMapping("/saveUser")
    public JsonData saveUser(@RequestBody User vo){
        if (vo.getNumber().toString().length()!=6){
            return JsonData.fail("学号必须为六位数!");
        }
        try {
          return  userService.saveUser(vo);
        }catch (Exception e){
            return JsonData.success("新增失败!"+e);
        }
    }

    @ApiOperation(value = "批量删除用户")
    @PostMapping("/removeUser")
    public JsonData removeUser(Integer[] ids){
        try {
            userService.removeUser(ids);
            return JsonData.success("删除成功!");
        }catch (Exception e){
            return JsonData.success("删除失败!"+e);
        }
    }


    @ApiOperation(value = "mq发消息")
    @PostMapping("/mqUser")
    public JsonData mqUser(){
        String message="hello shenfeifan";
         rabbitTemplate.convertAndSend("SFF_EXCHANGE","product.add",message);
        return JsonData.success("发送成功！");
    }
}
