package com.springboot.service;
import com.springboot.pojo.Grade;
import com.springboot.pojo.domain.JsonData;

public interface GradeService {
    Grade getGrade(Integer classNumber);
    JsonData saveGrade(Grade grade);
}
