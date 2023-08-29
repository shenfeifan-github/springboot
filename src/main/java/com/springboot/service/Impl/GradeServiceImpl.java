package com.springboot.service.Impl;
import com.springboot.mapper.GradeMapper;
import com.springboot.pojo.Grade;
import com.springboot.pojo.domain.JsonData;
import com.springboot.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GradeServiceImpl implements GradeService {
    @Autowired
    private GradeMapper gradeMapper;

    @Override
    public Grade getGrade(Integer classNumber) {
        return gradeMapper.getGrade(classNumber);
    }

    @Override
    public JsonData saveGrade(Grade grade) {
        try {
            gradeMapper.saveGrade(grade);
            return JsonData.success("添加成功!");
        }catch (Exception e){
            return JsonData.fail("添加失败"+e);
        }
    }
}
