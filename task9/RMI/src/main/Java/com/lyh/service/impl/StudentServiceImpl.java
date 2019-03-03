package com.lyh.service.impl;

import com.lyh.dao.JobDao;
import com.lyh.dao.StudentDao;
import com.lyh.entity.Page;
import com.lyh.entity.Student;
import com.lyh.service.StudentService;
import com.lyh.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public class StudentServiceImpl implements StudentService {
    private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);
    @Autowired
    RedisUtil redisUtil;
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
        int num = dao.deleteStudent(id);
        if (num > 0) {
//            如果删除成功就删除已存在的 key(键)
            redisUtil.del("student" + id);
        }
        return num;
    }

    @Override
    public int updateStudent(Student student) {
        if (dao.updateStudent(student) > 0) {
            //如果更新成功就替换已存在的 key(键) 的 value(数据值)
            redisUtil.set("student" + student.getId(), student);
        }
        return 0;
    }

    /**
     * 当执行selectStudentKey查询方法时，系统首先会从缓存中获取userId对应的实体
     * 如果实体还没有被缓存，则执行查询方法并将查询结果放入缓存中
     */

    @Override
    public Student selectStudentKey(Long id) {
        Student student = (Student) redisUtil.get("student" + id);
        if (student != null) {
            System.out.println("从缓存在获取");
        } else {
            student = dao.selectStudentKey(id);
            redisUtil.set("student" + id, student);
        }

        return student;
    }

    @Override
    public List<Student> selectStudent(Boolean employmentStatus, Boolean excellenceDegree) {
        return dao.selectStudent(employmentStatus, excellenceDegree);
    }

    @Override
    public List<Student> findStudent(Page page) {
        logger.info("进入分页查询：当前页开始记录"+page.getStart());
        List<Student> students;
        if (redisUtil.get("students"+page.getStart())==null){
            logger.info("该数据没有缓存，准备设置缓存");
            students = dao.findStudent(page);
            redisUtil.set("students"+page.getStart(),students);
        }else {
            logger.info("该数据已经有缓存，准备读取缓存");
            students = (List<Student>)redisUtil.get("students"+page.getStart());
            logger.info("数据为：",students);
        }
        return students;
    }

    @Override
    public int total() {
        int total;
        if (redisUtil.get("total")==null){
            logger.info("总条数没有缓存，开始设置");
            total = dao.total();
            redisUtil.set("total",total,60*60);

        }else {
            logger.info("总条数已有缓存，开始读取");
            total =(int)redisUtil.get("total");
            logger.info("总条数缓存:"+total);
        }
        return total;
    }


    @Override
    public int excellenceDegreeTotal(Boolean employmentStatus) {
        int total = dao.excellenceDegreeTotal(employmentStatus);
        if (redisUtil.get("totalEmploymentStatus"+employmentStatus) == null){
            logger.info("优秀学员人数没有缓存，马上设置缓存");
            redisUtil.set("totalEmploymentStatus"+employmentStatus,total,60*60);
        }else {
            logger.info("优秀学员人数已有缓存，马上读取缓存");
            int x = (int)redisUtil.get("totalEmploymentStatus"+employmentStatus);
            logger.info("优秀学员人数:"+x);
        }
        return total;
    }
    @Override
    public List<Student> byName(String name) {
        List<Student> studentName ;
        if (redisUtil.get("student" + name) == null) {
            logger.info("模糊查询数据没有缓存，开始设置缓存");
            studentName = dao.byName(name);
            redisUtil.set("student" + name, studentName,60*60);
        } else {
            System.out.println("模糊查询数据已有缓存，正在获取");
            studentName = (List<Student>)redisUtil.get("student" + name);
            logger.info("模糊查询数据结果：",studentName);
        }
        return studentName;
    }

    @Override
    public int totalJob(String professionName) {
        int  total;
        if (redisUtil.get("totalProfessionName"+professionName)==null){
            logger.info("条数没有缓存，开始设置");
            total = dao.totalJob(professionName);
            redisUtil.set("totalProfessionName"+professionName,total,60*60);

        }else {
            logger.info("条数已有缓存，开始读取");
            total =(int)redisUtil.get("totalProfessionName"+professionName);
            logger.info("条数缓存:"+total);
        }
        return total;
    }

    @Override
    public void updateProNum(Student student) {
        int id = dao.addStudent(student);
        if (id > 0) {
            //添加方法成功就 将value(数据值student) 存储在指定的 key(键"student"+id) 中
            redisUtil.set("student" + id, student);
        }else {
            logger.info("添加失败，不设置缓存");
        }
        if (id > 0) {
            String professionName = student.getProfessionName();
            jobDao.updateJobNum(professionName);
        }
    }
}