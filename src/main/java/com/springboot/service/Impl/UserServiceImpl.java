package com.springboot.service.Impl;

import com.springboot.mapper.UserMapper;
import com.springboot.pojo.User;
import com.springboot.pojo.VO.LoginVo;
import com.springboot.pojo.VO.UserVo;
import com.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl  implements UserService {
    @Autowired
   private UserMapper userMapper;
    @Override
    public List<User> getUser(UserVo vo) {
        return userMapper.getUser(vo);
    }

    @Override
    public User userLogin(LoginVo vo) {
       return userMapper.userLogin(vo);
    }
}
