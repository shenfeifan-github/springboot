package com.springboot.mapper;
import com.springboot.pojo.Grade;
import com.springboot.pojo.VO.GradeVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GradeMapper {
    Grade getGradeByNumber(@Param("classNumber") Integer classNumber);
    List<Grade> getGrade(@Param("vo") GradeVo gradeVo);
    List<Integer> getCodeShare();
    void saveGrade(@Param("vo") Grade grade);
    List<Grade> getGradeName();
}
