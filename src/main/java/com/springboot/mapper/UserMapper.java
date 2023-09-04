package com.springboot.mapper;

import com.springboot.pojo.User;
import com.springboot.pojo.VO.LoginVo;
import com.springboot.pojo.VO.UserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
   List<User> getUser(@Param("vo")UserVo vo);
    User userLogin(@Param("vo")LoginVo vo);
}
