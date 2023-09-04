package com.springboot.mapper;
import com.springboot.pojo.Dto.StudentDto;
import com.springboot.pojo.EasyExcel.StudentExecl;
import com.springboot.pojo.Student;
import com.springboot.pojo.VO.StudentVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StudentMapper {
    List<StudentDto> getStudent(@Param("vo") StudentVo vo);
    Student getStudentNmber(@Param("number") Integer number);
    void saveStudent(@Param("vo") Student vo);
    void removeStudent(@Param("id") Integer id);
    List<StudentExecl> getStudentExcel();
    void updateStudentStatus(@Param("id") Integer id);
    List<Integer> getStudentId();
    void insertStudentList(@Param("list") List<StudentExecl> list);
}
