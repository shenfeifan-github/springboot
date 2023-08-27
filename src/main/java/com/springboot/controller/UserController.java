package com.springboot.controller;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.springboot.pojo.Dto.GradeDto;
import com.springboot.pojo.Dto.UserDto;
import com.springboot.pojo.Grade;
import com.springboot.pojo.User;
import com.springboot.pojo.VO.UserVo;
import com.springboot.pojo.domain.JsonData;
import com.springboot.service.GradeService;
import com.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private GradeService gradeService;
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
    @PostMapping("/saveUser")
    public JsonData saveUser(@RequestBody UserVo vo){
        try {
            userService.saveUser(vo);
            return JsonData.success("新增成功!");
        }catch (Exception e){
            return JsonData.success("新增失败!"+e);
        }

    }
}
