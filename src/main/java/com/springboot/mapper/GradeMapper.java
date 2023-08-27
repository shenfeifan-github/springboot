package com.springboot.mapper;
import com.springboot.pojo.Grade;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface GradeMapper {
    Grade getGrade(@Param("classNumber") Integer classNumber);
}
