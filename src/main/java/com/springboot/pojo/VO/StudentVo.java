package com.springboot.pojo.VO;

import lombok.Data;

@Data
public class StudentVo {
    private String paramOne;
    private String paramTwo;
    private Integer pageSize=10;
    private Integer pageNum=1;
    private String name;
    private Integer number;
    private Integer classNumber;
}
