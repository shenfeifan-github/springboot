package com.springboot.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.mapper.UserMapper;
import com.springboot.pojo.Dto.UserDto;
import com.springboot.pojo.User;
import com.springboot.pojo.VO.UserVo;
import com.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public List<UserDto> getUser(UserVo vo) {
        return userMapper.getUser(vo);
    }

    @Override
    public void saveUser(UserVo vo) {
        userMapper.saveUser(vo);
    }
}
