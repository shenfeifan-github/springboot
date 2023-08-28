package com.springboot.mapper;
import com.springboot.pojo.Dto.UserDto;
import com.springboot.pojo.User;
import com.springboot.pojo.VO.UserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    List<UserDto> getUser(@Param("vo") UserVo vo);
    User getUserNmber(@Param("number") Integer number);
    void saveUser(@Param("vo") User vo);
    void removeUser(@Param("id") Integer id);
}
