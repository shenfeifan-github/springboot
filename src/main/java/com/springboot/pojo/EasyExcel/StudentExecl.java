package com.springboot.pojo.EasyExcel;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;


@Data
public class StudentExecl {
    @ExcelProperty(value = "姓名",index = 0)
    private String name;
    @ExcelProperty(value = "学号",index = 1)
    private Integer number;//学号
    @ExcelProperty(value = "班号",index = 2)
    private Integer classNumber;//班号
    @ExcelProperty(value = "班级名称",index = 3)
    private String className;//班号
}
