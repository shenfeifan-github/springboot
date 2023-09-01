package com.springboot.controller;

import com.alibaba.excel.EasyExcel;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.pojo.EasyExcel.ScoreExcel;
import com.springboot.pojo.Grade;
import com.springboot.pojo.Score;
import com.springboot.pojo.VO.ScoreVo;
import com.springboot.pojo.domain.JsonData;
import com.springboot.service.GradeService;
import com.springboot.service.ScoreService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/score")
public class ScoreController {
    @Autowired
   private ScoreService scoreService;
    @Autowired
    private GradeService gradeService;
    @PostMapping("/getScore")
    public JsonData getScore(@RequestBody ScoreVo vo){
        try {
            PageHelper.startPage(vo.getPageNum(),vo.getPageSize());
            List<Score> result = scoreService.getScore(vo);
            PageInfo<Score> pageInfo = new PageInfo<>(result);
            return JsonData.success(pageInfo,"查询成功!");

        }catch (Exception e){
            return JsonData.fail("查询失败"+e);
        }
    }

    @ApiOperation(value = "导出成绩列表")
    @PostMapping("/export")
    public JsonData export(Integer classNumber) throws IOException {
        String sheet=null;
        if(classNumber==null){
            sheet="年级成绩表";
        }
        if (classNumber !=null){
          Grade grade= gradeService.getGradeByNumber(classNumber);
          if (!ObjectUtils.isEmpty(grade)){
              sheet=grade.getClassName()+"成绩表";
          }else {
              return JsonData.fail("选择的班级不存在!");
          }
        }
        try {
             List<ScoreExcel> list =scoreService.getScoreExcel(classNumber);
            SimpleDateFormat dateFormat=new SimpleDateFormat("yyyyMMddHHmmss");
            String fileName="成绩列表"+dateFormat.format(new Date())+".xlsx";
            EasyExcel.write(fileName, ScoreExcel.class)
                    .sheet(sheet)
                    .doWrite(() -> {
                        return list;
                    });
            return JsonData.success(fileName,"导出成功");
        }catch (Exception e){
            return JsonData.fail("导出失败"+e);
        }

    }
}
