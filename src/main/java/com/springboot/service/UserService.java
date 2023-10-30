package com.springboot.service;

import com.springboot.pojo.User;
import com.springboot.pojo.VO.LoginVo;
import com.springboot.pojo.VO.UserVo;
import com.springboot.pojo.domain.JsonData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {
    List<User> getUser(UserVo vo);
    User userLogin(LoginVo vo);
    JsonData saveUser(User vo);
    JsonData updateUser(User vo);
    void removeUser(Integer[] ids);
}
