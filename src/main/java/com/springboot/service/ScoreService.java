package com.springboot.service;

import com.springboot.pojo.EasyExcel.ScoreExcel;
import com.springboot.pojo.Score;
import com.springboot.pojo.VO.ScoreVo;

import java.util.List;

public interface ScoreService {
    List<Score> getScore(ScoreVo vo);
    List<ScoreExcel> getScoreExcel(Integer classNumber);
}
