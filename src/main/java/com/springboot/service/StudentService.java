package com.springboot.service;
import com.springboot.pojo.Dto.StudentDto;
import com.springboot.pojo.EasyExcel.StudentExecl;
import com.springboot.pojo.Student;
import com.springboot.pojo.VO.StudentVo;
import com.springboot.pojo.domain.JsonData;

import java.util.List;

public interface StudentService {
   List<StudentDto> getStudent(StudentVo vo);
   List<StudentExecl> getStudentExcel();
   JsonData saveStudent(Student vo);
   void removeStudent(Integer[] ids);
   void updateStudentStatus(Integer id);
   List<Integer> getStudentId();
   JsonData insertStudentList(List<StudentExecl> list);
}
