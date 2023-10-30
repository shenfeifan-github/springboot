package com.springboot.service.Impl;

import com.github.pagehelper.util.StringUtil;
import com.springboot.mapper.UserMapper;
import com.springboot.pojo.User;
import com.springboot.pojo.VO.LoginVo;
import com.springboot.pojo.VO.UserVo;
import com.springboot.pojo.domain.JsonData;
import com.springboot.service.UserService;
import org.apache.commons.codec.binary.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

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

    @Override
    public JsonData saveUser(User vo) {
        List<String> phoneList =userMapper.getPhone();
        if (!ObjectUtils.isEmpty(phoneList)){
            for (String phone:phoneList){
                if (StringUtils.equals(phone,vo.getPhone())){
                    return JsonData.fail("该号码已被使用!");
                }
            }
        }
        try{
            userMapper.saveUser(vo);
            return JsonData.success(null,"新增成功!");
        }catch (Exception e){
            return JsonData.fail("新增失败!");
        }
    }

    @Override
    public JsonData updateUser(User vo) {
       try {
           userMapper.updateUser(vo);
           return JsonData.success(null,"修改成功!");
       }catch (Exception e){
           return JsonData.fail("修改失败");
       }
    }

    @Override
    public void removeUser(Integer[] ids) {
        for (Integer id:ids){
            userMapper.removeUser(id);
        }
    }
}
