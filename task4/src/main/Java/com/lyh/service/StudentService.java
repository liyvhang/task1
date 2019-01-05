package com.lyh.service;

import com.lyh.entity.Page;
import com.lyh.entity.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentService {

//    添加
    int addStudent(Student student);
//删除
    int deleteStudent(Long id);
//编辑信息
    int updateStudent(Student student);
//根据id查询
    Student selectStudentKey(Long id);
//根据就业和优秀程度查询信息
    List<Student> selectStudent(@Param("employmentStatus") Boolean employmentStatus, @Param("Boolean") Boolean excellenceDegree);
//分页查询
    List<Student> findStudent(Page page);

    int total();
//根据就业状态查询人数
    int excellenceDegreeTotal(Boolean employmentStatus);

    //根据名字模糊查询
    public List<Student> byName(String name);
    //    根据职业类型查询条数
    int totalJob(String professionName);

    void updateProNum(Student student);
}
