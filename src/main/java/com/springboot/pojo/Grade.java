package com.springboot.pojo;

import lombok.Data;

import java.sql.Date;

@Data
public class Grade {
    private Integer id;
    private Integer codeshare; //班号
    private String className;//班级名称
    private String charge;//班主任
    private String monitor;//班长
    private String commissary;//学习委员
    private Date createTime;

}
