package com.springboot.service;
import com.springboot.pojo.Grade;
import com.springboot.pojo.VO.GradeVo;
import com.springboot.pojo.domain.JsonData;

import java.util.List;

public interface GradeService {
    Grade getGradeByNumber(Integer classNumber);
    List<Grade> getGrade(GradeVo gradeVo);
    JsonData saveGrade(Grade grade);
    List<Grade> getGradeName();
}
