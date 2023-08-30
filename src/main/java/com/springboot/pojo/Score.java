package com.springboot.pojo;

import lombok.Data;

@Data
public class Score {
    private Integer id;
    private String  name;//学生名称
    private Integer number;//学号
    private String  className;//班级名称
    private Integer classNumber;//班号
    private Integer mathematics;//数学成绩
    private Integer philology;//语文成绩
    private Integer english;//英语成绩
    private Integer chemistry;//化学成绩
    private Integer physics;//物理成绩
    private Integer biology;//生物成绩
    private Integer general;//总分
    private Integer integerscience;//理科总分
}
