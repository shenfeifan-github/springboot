package com.springboot.pojo.VO;

import lombok.Data;

@Data
public class UserVo {
    private Integer PageSize=10;
    private Integer PageNum=1;
    private String name;
    private String number;
    private String classNumber;
}
