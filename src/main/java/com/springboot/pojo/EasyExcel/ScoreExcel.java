package com.springboot.pojo.EasyExcel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class ScoreExcel {
    @ExcelProperty(value = "姓名",index = 0)
    private String  name;//学生名称
    @ExcelProperty(value = "学号",index = 1)
    private Integer number;//学号
    @ExcelProperty(value = "班级名称",index = 2)
    private String  className;//班级名称
    @ExcelProperty(value = "班号",index = 3)
    private Integer classNumber;//班号
    @ExcelProperty(value = "数学",index = 4)
    private Integer mathematics;//数学成绩
    @ExcelProperty(value = "语文",index = 5)
    private Integer philology;//语文成绩
    @ExcelProperty(value = "英语",index = 6)
    private Integer english;//英语成绩
    @ExcelProperty(value = "化学",index = 7)
    private Integer chemistry;//化学成绩
    @ExcelProperty(value = "物理",index = 8)
    private Integer physics;//物理成绩
    @ExcelProperty(value = "生物",index = 9)
    private Integer biology;//生物成绩
    @ExcelProperty(value = "理综",index = 10)
    private Integer integerscience;//理科总分
    @ExcelProperty(value = "总分",index = 11)
    private Integer general;//总分
}
