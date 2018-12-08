package com.lyh.service;

import com.lyh.model.Banner;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BannerService {
    //根据id删除
    int deleteByPrimaryKey(Long id);
    //插入数据
    int insert(Banner record);

    int insertSelective(Banner record);
    //根据id查询
    Banner selectByPrimaryKey(Long id);
    //修改
    int updateByPrimaryKeySelective(Banner record);
    //修改
    int updateByPrimaryKey(Banner record);
    //    动态条件查询
    List<Banner> findBanner(@Param("updateBy")String updateBy, @Param("status")Short status);

}
