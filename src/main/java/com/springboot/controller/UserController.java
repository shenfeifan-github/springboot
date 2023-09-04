package com.springboot.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.pojo.User;
import com.springboot.pojo.VO.LoginVo;
import com.springboot.pojo.VO.UserVo;
import com.springboot.pojo.domain.JsonData;
import com.springboot.service.UserService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin
@Api(value = "用户管理")
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/getUser")
    public JsonData getUser(UserVo vo){
        try {
          List<User> result  =userService.getUser(vo);
            PageHelper.startPage(vo.getPageNum(),vo.getPageSize());
            PageInfo<User> pageInfo = new PageInfo<>(result);
            return JsonData.success(pageInfo,"查询成功!");
        }catch (Exception e){
            return JsonData.fail("查询失败"+e);
        }
    }
    @PostMapping("/getLogin")
    public JsonData userLogin(@RequestBody LoginVo vo){
        try {
            User user =  userService.userLogin(vo);
            if (!ObjectUtils.isEmpty(user)){
                return JsonData.success(user,"登录成功");
            }else {
                return JsonData.fail("账号密码错误!");
            }
        }catch (Exception e){
            return JsonData.fail("登录失败!"+e);
        }
    }
}
