package com.lyh.service.impl;

import com.lyh.dao.JobDao;
import com.lyh.dao.StudentDao;
import com.lyh.entity.Job;
import com.lyh.entity.Page;
import com.lyh.entity.Student;
import com.lyh.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentDao dao;
    @Autowired
    private JobDao jobDao;
    @Override
    public int addStudent(Student student) {
        return dao.addStudent(student);
    }

    @Override
    public int deleteStudent(Long id) {
        return dao.deleteStudent(id);
    }

    @Override
    public int updateStudent(Student student) {
        return dao.updateStudent(student);
    }

    @Override
    public Student selectStudentKey(Long id) {
        return dao.selectStudentKey(id);
    }

    @Override
    public List<Student> selectStudent(Boolean employmentStatus, Boolean excellenceDegree) {
        return dao.selectStudent(employmentStatus,excellenceDegree);
    }

    @Override
    public List<Student> findStudent(Page page) {
        return dao.findStudent(page);
    }

    @Override
    public int total() {
        return dao.total();
    }

    @Override
    public int excellenceDegreeTotal(Boolean employmentStatus) {
        return dao.excellenceDegreeTotal(employmentStatus);
    }

    @Override
    public List<Student> byName(String name) {
        return dao.byName(name);
    }

    @Override
    public int totalJob(String professionName) {
        return dao.totalJob(professionName);
    }

    @Override
    public void updateProNum(Student student) {
        int tag = dao.addStudent(student);
        if (tag==1){
            String professionName = student.getProfessionName();
            jobDao.updateJobNum(professionName);
        }
    }
}
