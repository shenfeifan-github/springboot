package com.springboot.service;

import com.github.pagehelper.PageInfo;
import com.springboot.pojo.User;

import java.util.List;

public interface UserService {
   List<User> getUser();
}
