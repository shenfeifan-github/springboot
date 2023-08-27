package com.springboot.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Date;

@Data
public class User {
    private Integer id;
    private String name;
    private Integer number;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}
