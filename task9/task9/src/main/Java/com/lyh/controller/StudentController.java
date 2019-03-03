package com.lyh.controller;

import com.lyh.entity.Page;
import com.lyh.entity.Student;
import com.lyh.service.JobService;
import com.lyh.service.RMIService;
import com.lyh.service.StudentService;
import com.lyh.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class StudentController {
    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    //    private StudentService studentService;
//    private JobService jobService;
    //    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-mybatis.xml");
//    StudentService studentService = (StudentService)applicationContext.getBean("studentRMIClient");
    @Autowired
    RedisUtil redisUtil;
    RMIService rmiService = new RMIService();
    StudentService studentService = rmiService.getStudentService();

    @RequestMapping("/home")
    public String home(Model model) {



        List<Student> studentList = studentService.selectStudent(true, true);
        logger.info("根据就业以及优秀状态查到的数据：" + studentList);
        //打乱顺序
        Collections.shuffle(studentList);

        List students = null;

        if (redisUtil.get("studentList") == null) {
            logger.info("优秀学员缓存没有设置，开始设置缓存");
            students = new ArrayList(studentList.subList(0, 4));
            //students = studentList.subList(0,4);
            System.out.println("students = " + students);
            System.out.println("==========student = " + students);
            redisUtil.set("studentList", students, 60 * 60);

        } else {
            logger.info("优秀学员缓存已设置，开始读取缓存");
            students = (List<Student>) redisUtil.get("studentList");
            logger.info("随机4条数据结果为" + students);
        }


        int learning = studentService.excellenceDegreeTotal(true);
        logger.info("结业人数为" + learning);
        int total = studentService.total();
        logger.info("学员总人数" + total);
        int graduate = total - learning;
        logger.info("在学人数为" + graduate);
        model.addAttribute("students", students);
        model.addAttribute("learning", learning);
        model.addAttribute("total", total);
        model.addAttribute("graduate", graduate);
        return "home";
    }

    @RequestMapping(value = "/u/student", method = RequestMethod.GET)
    public ModelAndView listStudent(Page page) {
        logger.info("listStudent方法被调用");
        ModelAndView mav = new ModelAndView();
        List<Student> students = studentService.findStudent(page);
        int total = studentService.total();
        page.caculateLast(total);
        mav.addObject("students", students);
        mav.setViewName("student");
        return mav;
    }

    @RequestMapping(value = "/a/student", method = RequestMethod.GET)
    public String addStudent() {
        logger.warn("跳转添加页面");
        return "addStudent";
    }

    @RequestMapping(value = "/a/student", method = RequestMethod.POST)
    public ModelAndView addStudent(Student student) {
        logger.info("添加方法被调用");
        String name = student.getName();
        System.out.println("name = " + name);
        String position = student.getPosition();
        System.out.println("position = " + position);

        System.out.println("============" + student);
        if (name.isEmpty()) {
            ModelAndView mav = new ModelAndView("redirect:/a/student");
            logger.info("信息不能为空");
            return mav;
        }
        studentService.updateProNum(student);
        int total = studentService.total();
        Page page = new Page();
        page.caculateLast(total);
        ModelAndView mav = new ModelAndView("redirect:/u/student");
        mav.addObject("student", student);
        return mav;
    }

    @RequestMapping(value = "/student/{id}", method = RequestMethod.DELETE)
    public ModelAndView deleteStudent(@PathVariable Long id) {
        logger.info("删除方法被调用");
        logger.info(String.valueOf(id));
        long x = studentService.deleteStudent(id);
        logger.info("删除数据的id:" + String.valueOf(x));
        ModelAndView mav = new ModelAndView("redirect:/u/student");
        return mav;
    }

    //    根据id查询数据
    @RequestMapping(value = "/student/{id}", method = RequestMethod.GET)
    public ModelAndView editStudent(@PathVariable Long id) {
        logger.warn("根据id查询数据");
        Student student = studentService.selectStudentKey(id);
        logger.warn("根据id查询数据结果：" + student);
        ModelAndView mav = new ModelAndView();
        mav.addObject("student", student);
        mav.setViewName("editStudent");
        logger.warn(String.valueOf(student));
        return mav;
    }

    @RequestMapping(value = "/student", method = RequestMethod.PUT)
    public ModelAndView updateStudent(Student student) {
        logger.info(String.valueOf(student));
        logger.info("修改方法被调用");
        studentService.updateStudent(student);
        logger.info(String.valueOf(student));
        ModelAndView mav = new ModelAndView("redirect:/u/student");
        mav.addObject("student", student);
        return mav;
    }

    @RequestMapping(value = "/student/name/", method = RequestMethod.GET)
    public ModelAndView byName(@RequestParam String name) {
        logger.warn("调用模糊查询");
        logger.warn("查询名字：" + name);
        List<Student> students = studentService.byName(name);
        logger.warn("模糊查询结果：" + String.valueOf(students));
        ModelAndView mav = new ModelAndView();
        mav.addObject("students", students);
        mav.setViewName("studentJson");
        logger.warn(String.valueOf(mav));
        return mav;
    }

}