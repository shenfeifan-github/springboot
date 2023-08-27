package com.springboot.controller;

import com.springboot.pojo.User;
import com.springboot.pojo.domain.JsonData;
import com.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/getUser")
    public JsonData getUser(){
       User user= userService.getUser();
       return JsonData.success(user);

    }
}
