package com.springboot.pojo.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.springboot.pojo.Grade;
import lombok.Data;

import java.sql.Date;

@Data
public class UserDto {
    private Integer id;
    private String name;
    private Integer number;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    private Integer classNumber;
    private Grade grade;
}
