package com.lyh.service;

import com.lyh.model.Studio;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudioService {
    //    根据id删除
    int deleteByPrimaryKey(Long id);
    //插入
    int insert(Studio record);
    //动态插入
    int insertSelective(Studio record);
    //根据id查询
    Studio selectByPrimaryKey(Long id);
    //动态更新
    int updateByPrimaryKeySelective(Studio record);
    //更新
    int updateByPrimaryKey(Studio record);
    //    根据名称和状态查询
    List<Studio> findStudio(@Param("name")String name, @Param("status")Boolean status);

}
