package com.lyh.service.impl;

import com.lyh.dao.JobDao;
import com.lyh.dao.StudentDao;
import com.lyh.entity.Page;
import com.lyh.entity.Student;
import com.lyh.service.StudentService;
import com.whalin.MemCached.MemCachedClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);
    @Autowired
    private StudentDao dao;
    @Autowired
    private JobDao jobDao;
    @Autowired
    MemCachedClient memCachedClient;

    @Override
    public int addStudent(Student student) {
        return dao.addStudent(student);
    }

    @Override
    public int deleteStudent(Long id) {
        if (dao.deleteStudent(id) > 0) {
//            如果删除成功就删除已存在的 key(键)
            memCachedClient.delete("student" + id);
        }
        return 0;
    }

    @Override
    public int updateStudent(Student student) {
        if (dao.updateStudent(student) > 0) {
            //如果更新成功就替换已存在的 key(键) 的 value(数据值)
            memCachedClient.replace("student" + student.getId(), student);
        }
        return 0;
    }

    /**
     * 当执行selectStudentKey查询方法时，系统首先会从缓存中获取userId对应的实体
     * 如果实体还没有被缓存，则执行查询方法并将查询结果放入缓存中
     */

    @Override
    public Student selectStudentKey(Long id) {
        Student student = (Student) memCachedClient.get("student" + id);
        if (student != null) {
            System.out.println("从缓存在获取");
        } else {
            student = dao.selectStudentKey(id);
            memCachedClient.set("student" + id, student);
        }
        return student;
    }

    @Override
    public List<Student> selectStudent(Boolean employmentStatus, Boolean excellenceDegree) {
//        List<Student> studentList;
//        if (memCachedClient.get("studentList"+employmentStatus+excellenceDegree) ==null){
//            logger.info("优秀学员缓存没有设置，开始设置缓存");
//            studentList =  dao.selectStudent(employmentStatus, excellenceDegree);
//            memCachedClient.set("studentList"+employmentStatus+excellenceDegree,studentList);
//        }else {
//            logger.info("优秀学员缓存已设置，开始读取缓存");
//            studentList = ( List<Student>)memCachedClient.get("studentList"+employmentStatus+excellenceDegree);
//            logger.info("优秀学员缓存:"+studentList);
//        }
        return dao.selectStudent(employmentStatus, excellenceDegree);
    }

    @Override
    public List<Student> findStudent(Page page) {
        logger.info("进入分页查询：当前页开始记录"+page.getStart());
        List<Student> students;
        if (memCachedClient.get("students"+page.getStart())==null){
            logger.info("该数据没有缓存，准备设置缓存");
            students = dao.findStudent(page);
            memCachedClient.set("students"+page.getStart(),students);
        }else {
            logger.info("该数据已经有缓存，准备读取缓存");
            students = (List<Student>)memCachedClient.get("students"+page.getStart());
            logger.info("数据为：",students);
        }
        return students;
    }

    @Override
    public int total() {
        int  total;
        if (memCachedClient.get("total")==null){
            logger.info("总条数没有缓存，开始设置");
            total = dao.total();
            memCachedClient.set("total",total);

        }else {
            logger.info("总条数已有缓存，开始读取");
            total =(int)memCachedClient.get("total");
            logger.info("总条数缓存:"+total);
        }
        return total;
    }

    @Override
    public int excellenceDegreeTotal(Boolean employmentStatus) {
        int total = dao.excellenceDegreeTotal(employmentStatus);
        if (memCachedClient.get("totalEmploymentStatus"+employmentStatus) == null){
            logger.info("优秀学员人数没有缓存，马上设置缓存");
           memCachedClient.set("totalEmploymentStatus"+employmentStatus,total);
        }else {
            logger.info("优秀学员人数已有缓存，马上读取缓存");
            int x = (int)memCachedClient.get("totalEmploymentStatus"+employmentStatus);
            logger.info("优秀学员人数:"+x);
        }
        return dao.excellenceDegreeTotal(employmentStatus);
    }

    @Override
    public List<Student> byName(String name) {
        List<Student> studentName ;
        if (memCachedClient.get("student" + name) == null) {
            logger.info("模糊查询数据没有缓存，开始设置缓存");
            studentName = dao.byName(name);
           memCachedClient.set("student" + name, studentName);
        } else {
            System.out.println("模糊查询数据已有缓存，正在获取");
            studentName = (List<Student>)memCachedClient.get("student" + name);
            logger.info("模糊查询数据结果：",studentName);
        }
        return studentName;
    }

    @Override
    public int totalJob(String professionName) {
        int  total;
        if (memCachedClient.get("totalProfessionName"+professionName)==null){
            logger.info("条数没有缓存，开始设置");
            total = dao.totalJob(professionName);
            memCachedClient.set("totalProfessionName"+professionName,total);

        }else {
            logger.info("条数已有缓存，开始读取");
            total =(int)memCachedClient.get("totalProfessionName"+professionName);
            logger.info("条数缓存:"+total);
        }
        return total;
    }

    @Override
    public void updateProNum(Student student) {
        int id = dao.addStudent(student);
        if (id > 0) {
            //添加方法成功就 将value(数据值student) 存储在指定的 key(键"student"+id) 中
            memCachedClient.set("student" + id, student);
        }else {
            logger.info("添加失败，不设置缓存");
        }
        if (id == 1) {
            String professionName = student.getProfessionName();
            jobDao.updateJobNum(professionName);
        }
    }
}
