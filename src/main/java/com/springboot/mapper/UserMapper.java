package com.springboot.mapper;
import com.springboot.pojo.Dto.UserDto;
import com.springboot.pojo.VO.UserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    List<UserDto> getUser(@Param("vo") UserVo vo);
    void saveUser(UserVo vo);
}
