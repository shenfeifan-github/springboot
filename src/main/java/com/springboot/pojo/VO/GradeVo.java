package com.springboot.pojo.VO;

import lombok.Data;

@Data
public class GradeVo {
    private Integer id;
    private Integer PageSize=10;
    private Integer PageNum=1;
    private String paramOne;
    private String paramTwo;
    private Integer codeshare; //班号
    private String className;//班级名称
    private String charge;//班主任
    private String monitor;//班长
    private String commissary;//学习委员
}
