package com.springboot.pojo.VO;

import lombok.Data;

@Data
public class UserVo {
    private Integer pageSize=10;
    private Integer pageNum=1;
    private String paramOne;
    private String paramTwo;
    private String userName;
    private String phone;
}
