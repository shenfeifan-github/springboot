package com.springboot.pojo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class Student {
    private Integer id;
    private String name;
    private Integer number;//学号
    private Integer classNumber;//班号
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}
