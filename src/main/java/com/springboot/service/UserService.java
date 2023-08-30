package com.springboot.service;
import com.springboot.pojo.Dto.UserDto;
import com.springboot.pojo.EasyExcel.UserExecl;
import com.springboot.pojo.User;
import com.springboot.pojo.VO.UserVo;
import com.springboot.pojo.domain.JsonData;

import java.util.List;

public interface UserService {
   List<UserDto> getUser(UserVo vo);
   List<UserExecl> getUserExcel();
   JsonData saveUser(User vo);
   void removeUser(Integer[] ids);
   void updateUserStatus(Integer id);
   List<Integer> getUserId();
   JsonData insertUserList(List<UserExecl> list);
}
