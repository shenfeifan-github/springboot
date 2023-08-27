package com.springboot.mapper;

import com.github.pagehelper.PageInfo;
import com.springboot.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> getUser();
}
