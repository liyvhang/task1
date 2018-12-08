package com.lyh.service.ServiceImpl;

import com.lyh.mapper.SecondMapper;
import com.lyh.model.Second;
import com.lyh.service.SecondService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecondServiceImpl implements SecondService {
    @Autowired
    SecondMapper secondMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return secondMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Second record) {
        return secondMapper.insert(record);
    }

    @Override
    public int insertSelective(Second record) {
        return secondMapper.insertSelective(record);
    }

    @Override
    public Second selectByPrimaryKey(Long id) {
        return secondMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Second record) {
        return secondMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Second record) {
        return secondMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<Second> findSecond(String name, Short status) {
        return secondMapper.findSecond(name,status);
    }
}
