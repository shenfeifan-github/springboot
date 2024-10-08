package com.springboot.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.pojo.User;
import com.springboot.pojo.VO.LoginVo;
import com.springboot.pojo.VO.UserVo;
import com.springboot.pojo.domain.JsonData;
import com.springboot.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
    @ApiOperation("获取用户列表")
    @PostMapping("/getUser")
    public JsonData getUser(@RequestBody UserVo vo){
        try {
            PageHelper.startPage(vo.getPageNum(),vo.getPageSize());
          List<User> result  =userService.getUser(vo);

            PageInfo<User> pageInfo = new PageInfo<>(result);
            return JsonData.success(pageInfo,"查询成功!");
        }catch (Exception e){
            return JsonData.fail("查询失败"+e);
        }
    }
    @ApiOperation("用户登录")
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
    @ApiOperation("新增用户")
    @PostMapping("/saveUser")
    public JsonData saveUser(@RequestBody User vo){
        return  userService.saveUser(vo);
    }
    @ApiOperation("修改用户")
    @PostMapping("/updateUser")
    public JsonData updateUser(@RequestBody User vo){
        return  userService.updateUser(vo);
    }
    @ApiOperation(value = "批量删除用户")
    @PostMapping("/removeUser")
    public JsonData removeUser(Integer[] ids){
        try {
           userService.removeUser(ids);
            return JsonData.success(null,"删除成功!");
        }catch (Exception e){
            return JsonData.fail("删除失败!"+e);
        }
    }
}
