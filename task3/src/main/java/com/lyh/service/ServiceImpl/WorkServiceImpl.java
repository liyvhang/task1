package com.lyh.service.ServiceImpl;

import com.lyh.mapper.WorkMapper;
import com.lyh.model.Work;
import com.lyh.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class WorkServiceImpl implements WorkService {
@Autowired
    WorkMapper workMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return workMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Work record) {
        return workMapper.insert(record);
    }

    @Override
    public int insertSelective(Work record) {
        return workMapper.insertSelective(record);
    }

    @Override
    public Work selectByPrimaryKey(Long id) {
        return workMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Work record) {
        return workMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Work record) {
        return workMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Work> findWork(String name, Short status) {
        return workMapper.findWork(name,status);
    }
}
