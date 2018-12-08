package com.lyh.controller;

import com.lyh.model.Second;
import com.lyh.service.SecondService;
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
public class SecondController {
    @Autowired
    SecondService service;
    private static final Logger logger = LoggerFactory.getLogger(SecondController.class);

    @ResponseBody
    @RequestMapping(value = "/second", method = RequestMethod.GET)
    public Map<String, Object> selectSecond(@RequestParam(value = "name", required = false) String name,
                                            @RequestParam(value = "status", required = false) Short status) {
        logger.warn(name, status);
        logger.warn("---------查询被调用-----");
        Map<String, Object> map = new HashMap<String, Object>();
        List<Second> secondList = service.findSecond(name, status);
        if (secondList == null || secondList.size() == 0) {
            map.put("code", "200");
            map.put("message", "查询失败");
            map.put("data", "无法找到结果");
        } else {
            map.put("code", "300");
            map.put("message", "查询成功");
            map.put("data", secondList);
        }
        logger.warn("查询结果：" + secondList);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/a/second", method = RequestMethod.POST)
    public Map<String, Object> addSecond(Second record, BindingResult result) {
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
            map.put("code", "200");
            map.put("message", "添加失败");
        } else {
            map.put("code", "300");
            map.put("message", "添加成功");
        }
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/second", method = RequestMethod.PUT)
    public Map<String, Object> updateSecond(Second record) {
        logger.warn("----更新方法被调用-----");
        logger.warn("更新内容" + String.valueOf(record));
        Map<String, Object> map = new HashMap<String, Object>();
        int x = service.updateByPrimaryKeySelective(record);
        if (x == 0) {
            map.put("code", "200");
            map.put("message", "修改失败");
        } else {
            map.put("code", "300");
            map.put("message", "修改成功");
        }
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/second/{id}", method = RequestMethod.DELETE)
    public Map<String, Object> deleteSecond(@PathVariable Long id) {
        logger.warn("----删除方法被调用-----");
        logger.warn("删除数据的id为" + id);
        Map<String, Object> map = new HashMap<String, Object>();
        int y = service.deleteByPrimaryKey(id);
        if (y == 0) {
            map.put("code", "200");
            map.put("message", "删除失败");
        } else {
            map.put("code", "300");
            map.put("message", "删除成功");
        }
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/second/{id}", method = RequestMethod.GET)
    public Map<String, Object> getId(@PathVariable Long id) {
        Map<String, Object> map = new HashMap<String, Object>();
        logger.warn("获取传参id为：" + String.valueOf(id));
        Second record = service.selectByPrimaryKey(id);
        logger.warn("结果" + record);
        if (record == null) {
            map.put("code", "200");
            map.put("message", "查询失败");
            map.put("data", "无法找到结果");
        } else {
            map.put("code", "300");
            map.put("message", "查询成功");
            map.put("data", record);
        }
        return map;
    }
}
