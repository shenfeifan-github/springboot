package com.springboot.service.Impl;
import com.springboot.mapper.GradeMapper;
import com.springboot.mapper.UserMapper;
import com.springboot.pojo.Dto.UserDto;
import com.springboot.pojo.EasyExcel.UserExecl;
import com.springboot.pojo.User;
import com.springboot.pojo.VO.UserVo;
import com.springboot.pojo.domain.JsonData;
import com.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private GradeMapper gradeMapper;

    @Override
    public List<UserDto> getUser(UserVo vo) {
        return userMapper.getUser(vo);
    }

    @Override
    public JsonData saveUser(User vo) {
       User user= userMapper.getUserNmber(vo.getNumber());
       if (!ObjectUtils.isEmpty(user)){
           return JsonData.fail("学号和"+user.getName()+"同学重复!");
       }
       List<Integer> list=gradeMapper.getCodeShare();
       if (list.contains(vo.getClassNumber())){
           userMapper.saveUser(vo);
       }else {
         return JsonData.fail("班级号必须为:"+list.toString());
       }
       return JsonData.success("新增成功!");
    }

    @Override
    public void removeUser(Integer[] ids) {
        for (Integer id:ids){
            userMapper.removeUser(id);
        }
    }

    @Override
    public List<UserExecl> getUserExcel() {
        return userMapper.getUserExcel();
    }

    @Override
    public void updateUserStatus(Integer id) {
        userMapper.updateUserStatus(id);
    }

    @Override
    public List<Integer> getUserId() {
        return userMapper.getUserId();
    }

    @Override
    public JsonData insertUserList(List<UserExecl> list) {
        try {
            userMapper.insertUserList(list);
            return JsonData.success("导入成功!");
        }catch (Exception e){
            return JsonData.fail("导入失败!"+e);
        }

    }
}
