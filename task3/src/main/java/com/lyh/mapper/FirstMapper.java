package com.lyh.mapper;

import com.lyh.model.First;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FirstMapper {
    int deleteByPrimaryKey(Long id);

    int insert(First record);

    int insertSelective(First record);

    First selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(First record);

    int updateByPrimaryKey(First record);

    List<First> findFirst(@Param("name")String name,@Param("status")Boolean status);
}