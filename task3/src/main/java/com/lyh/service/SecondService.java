package com.lyh.service;

import com.lyh.model.Second;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SecondService {
    int deleteByPrimaryKey(Long id);

    int insert(Second record);

    int insertSelective(Second record);

    Second selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Second record);

    int updateByPrimaryKey(Second record);

    List<Second> findSecond(@Param("name")String name,@Param("status")Short status);
}
