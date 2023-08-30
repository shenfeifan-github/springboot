package com.springboot.service.Impl;

import com.springboot.mapper.ScoreMapper;
import com.springboot.pojo.EasyExcel.ScoreExcel;
import com.springboot.pojo.Score;
import com.springboot.pojo.VO.ScoreVo;
import com.springboot.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ScoreServiceImpl implements ScoreService {
    @Autowired
   private ScoreMapper scoreMapper;
    @Override
    public List<Score> getScore(ScoreVo vo) {
        return scoreMapper.getScore(vo);
    }

    @Override
    public List<ScoreExcel> getScoreExcel(Integer classNumber) {
        return scoreMapper.getScoreExcel(classNumber);
    }
}
