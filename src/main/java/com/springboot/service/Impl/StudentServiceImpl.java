package com.springboot.service.Impl;
import com.springboot.mapper.GradeMapper;
import com.springboot.mapper.StudentMapper;
import com.springboot.pojo.Dto.StudentDto;
import com.springboot.pojo.EasyExcel.StudentExecl;
import com.springboot.pojo.Student;
import com.springboot.pojo.VO.StudentVo;
import com.springboot.pojo.domain.JsonData;
import com.springboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private GradeMapper gradeMapper;

    @Override
    public List<StudentDto> getStudent(StudentVo vo) {
        return studentMapper.getStudent(vo);
    }

    @Override
    public JsonData saveStudent(Student vo) {
       Student student= studentMapper.getStudentNmber(vo.getNumber());
       if (!ObjectUtils.isEmpty(student)){
           return JsonData.fail("学号和"+student.getName()+"同学重复!");
       }
       List<Integer> list=gradeMapper.getCodeShare();
       if (list.contains(vo.getClassNumber())){
           studentMapper.saveStudent(vo);
       }else {
         return JsonData.fail("班级号必须为:"+list.toString());
       }
       return JsonData.success("新增成功!");
    }

    @Override
    public void removeStudent(Integer[] ids) {
        for (Integer id:ids){
            studentMapper.removeStudent(id);
        }
    }

    @Override
    public List<StudentExecl> getStudentExcel() {
        return studentMapper.getStudentExcel();
    }

    @Override
    public void updateStudentStatus(Integer id) {
        studentMapper.updateStudentStatus(id);
    }

    @Override
    public List<Integer> getStudentId() {
        return studentMapper.getStudentId();
    }

    @Override
    public JsonData insertStudentList(List<StudentExecl> list) {
        try {
            studentMapper.insertStudentList(list);
            return JsonData.success("导入成功!");
        }catch (Exception e){
            return JsonData.fail("导入失败!"+e);
        }

    }
}
