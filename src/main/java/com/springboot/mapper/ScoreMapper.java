package com.springboot.mapper;

import com.springboot.pojo.EasyExcel.ScoreExcel;
import com.springboot.pojo.Score;
import com.springboot.pojo.VO.ScoreVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ScoreMapper {
    List<Score> getScore(@Param("vo") ScoreVo vo);
    List<ScoreExcel> getScoreExcel(@Param("classNumber") Integer calssNumber);
}
