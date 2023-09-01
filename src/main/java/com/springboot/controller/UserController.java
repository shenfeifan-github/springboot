package com.springboot.controller;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.pojo.Dto.UserDto;
import com.springboot.pojo.EasyExcel.UserExecl;
import com.springboot.pojo.Grade;
import com.springboot.pojo.User;
import com.springboot.pojo.VO.UserVo;
import com.springboot.pojo.domain.JsonData;
import com.springboot.service.GradeService;
import com.springboot.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private GradeService gradeService;
    @ApiOperation(value = "获取用户")
    @PostMapping("/getUser")
    public JsonData getUser(@RequestBody UserVo vo){
        PageHelper.startPage(vo.getPageNum(),vo.getPageSize());
        List<UserDto> result = userService.getUser(vo);
        for (UserDto userDto:result){
             if (userDto.getClassNumber() !=null){
             Grade grade= gradeService.getGradeByNumber(userDto.getClassNumber());
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


    @ApiOperation(value = "导出学生列表")
    @PostMapping("/export")
    public JsonData export() throws IOException{
        try {
            List<UserExecl> list =userService.getUserExcel();
            SimpleDateFormat dateFormat=new SimpleDateFormat("yyyyMMddHHmmss");
            String fileName="学生列表"+dateFormat.format(new Date())+".xlsx";
            EasyExcel.write(fileName, UserExecl.class)
                    .sheet("学生表")
                    .doWrite(() -> {
                        // 分页查询数据
                        return list;
                    });
            return JsonData.success(fileName,"导出成功");
        }catch (Exception e){
            return JsonData.fail("导出失败"+e);
        }

    }

    @ApiOperation(value = "导入学生列表")
    @PostMapping("/write")
    public JsonData write() throws IOException{
        try {
            String fileName = "学生列表20230830011257.xlsx";
            // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
            // 这里默认每次会读取100条数据 然后返回过来 直接调用使用数据就行
            // 具体需要返回多少行可以在`PageReadListener`的构造函数设置
            List<UserExecl> list =new ArrayList<>();
            EasyExcel.read(fileName, UserExecl.class, new PageReadListener<UserExecl>(dataList -> {
                for (UserExecl demoData : dataList) {
                    log.info("读取到一条数据{}", JSON.toJSONString(demoData));
                    list.add(demoData);
                }
            })).sheet().doRead();
            log.info("写入的数据{}",list);
            return userService.insertUserList(list);
        }catch (Exception e){
            return JsonData.fail("导入失败"+e);
        }

    }
}
