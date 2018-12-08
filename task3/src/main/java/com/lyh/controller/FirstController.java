package com.lyh.controller;

import com.lyh.model.First;
import com.lyh.service.FirstService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class FirstController {
    @Autowired
    FirstService service;
    private static final Logger logger = LoggerFactory.getLogger(FirstController.class);
    @ResponseBody
    @RequestMapping(value = "/first", method = RequestMethod.GET)
    public Map<String, Object> selectFirst(@RequestParam(value = "name", required = false) String name,
                                             @RequestParam(value = "status", required = false) Boolean status) {
        logger.warn(name,status);
        logger.warn("---------查询被调用-----");
        Map<String, Object> map = new HashMap<String, Object>();
        List<First> first = service.findFirst(name, status);
        if (first == null || first.size() == 0) {
            map.put("code", "100");
            map.put("message", "查询失败");
            map.put("data", "无法找到结果");
        } else {
            map.put("code", "500");
            map.put("message", "查询成功");
            map.put("data", first);
        }
        logger.warn("查询结果：" + first);
        return map;
    }
    @ResponseBody
    @RequestMapping(value = "/a/first", method = RequestMethod.POST)
    public Map<String, Object> addFirst(First record, BindingResult result) {
        logger.warn("----添加方法被调用-----");
        logger.warn("添加的数据" + record);
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            for (ObjectError error : errors) {
                logger.warn(error.getDefaultMessage());
            }
        }
        Map<String, Object> map = new HashMap<String, Object>();
        int x = service.insertSelective(record);
        logger.warn("结果" + String.valueOf(x));
        if (x == 0) {
            map.put("code", "100");
            map.put("message", "添加失败");
        } else {
            map.put("code", "500");
            map.put("message", "添加成功");
        }
        return map;
    }
    @ResponseBody
    @RequestMapping(value = "/first", method = RequestMethod.PUT)
    public Map<String, Object> updateFirst(First record) {
        logger.warn("----更新方法被调用-----");
        logger.warn("更新内容" + String.valueOf(record));
        Map<String, Object> map = new HashMap<String, Object>();
        int x = service.updateByPrimaryKeySelective(record);
        if (x == 0) {
            map.put("code", "100");
            map.put("message", "修改失败");
        } else {
            map.put("code", "500");
            map.put("message", "修改成功");
        }
        return map;
    }
    @ResponseBody
    @RequestMapping(value = "/first/{id}", method = RequestMethod.DELETE)
    public Map<String, Object> deleteFirst(@PathVariable Long id) {
        logger.warn("----删除方法被调用-----");
        logger.warn("删除数据的id为" + id);
        Map<String, Object> map = new HashMap<String, Object>();
        int y = service.deleteByPrimaryKey(id);
        if (y == 0) {
            map.put("code", "100");
            map.put("message", "删除失败");
        } else {
            map.put("code", "500");
            map.put("message", "删除成功");
        }
        return map;
    }
    @ResponseBody
    @RequestMapping(value = "/first/{id}", method = RequestMethod.GET)
    public Map<String,Object> getId(@PathVariable Long id){
        Map<String,Object> map = new HashMap<String, Object>();
        logger.warn("获取传参id为："+String.valueOf(id));
        First record = service.selectByPrimaryKey(id);
        logger.warn("结果"+record);
        if (record == null){
            map.put("code", "100");
            map.put("message", "查询失败");
            map.put("data", "无法找到结果");
        } else {
            map.put("code", "500");
            map.put("message", "查询成功");
            map.put("data", record);
        }
        return map;
    }
}
