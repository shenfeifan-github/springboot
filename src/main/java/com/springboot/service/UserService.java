package com.springboot.service;

import com.springboot.pojo.User;
import com.springboot.pojo.VO.LoginVo;
import com.springboot.pojo.VO.UserVo;

import java.util.List;

public interface UserService {
    List<User> getUser(UserVo vo);
    User userLogin(LoginVo vo);
}
