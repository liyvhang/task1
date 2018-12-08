package com.lyh.service.ServiceImpl;

import com.lyh.mapper.FirstMapper;
import com.lyh.model.First;
import com.lyh.service.FirstService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FristServiceImpl implements FirstService {
    @Autowired
    FirstMapper firstMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return firstMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(First record) {
        return firstMapper.insert(record);
    }

    @Override
    public int insertSelective(First record) {
        return firstMapper.insertSelective(record);
    }

    @Override
    public First selectByPrimaryKey(Long id) {
        return firstMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(First record) {
        return firstMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(First record) {
        return firstMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<First> findFirst(String name, Boolean status) {
        return firstMapper.findFirst(name,status);
    }
}
