package com.springboot.pojo;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String name;
    private Integer number;
    private Data createTime;
}
