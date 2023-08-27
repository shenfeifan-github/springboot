package com.springboot.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.pojo.User;
import com.springboot.pojo.domain.JsonData;
import com.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/getUser")
    public JsonData getUser(){

        PageHelper.startPage(1,10);
        List<User> newsByPage = userService.getUser();
        PageInfo<User> pageInfo = new PageInfo<>(newsByPage);

       return JsonData.success(pageInfo);

    }
}
