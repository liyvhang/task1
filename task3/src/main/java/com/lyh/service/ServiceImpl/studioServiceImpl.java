package com.lyh.service.ServiceImpl;

import com.lyh.mapper.StudioMapper;
import com.lyh.model.Studio;
import com.lyh.service.StudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class studioServiceImpl implements StudioService {
    @Autowired
    StudioMapper studioMapper;
    @Override
    public int deleteByPrimaryKey(Long id) {
        return studioMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Studio record) {
        return studioMapper.insert(record);
    }

    @Override
    public int insertSelective(Studio record) {
        return studioMapper.insertSelective(record);
    }

    @Override
    public Studio selectByPrimaryKey(Long id) {
        return studioMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Studio record) {
        return studioMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Studio record) {
        return studioMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Studio> findStudio(String name, Boolean status) {
        return studioMapper.findStudio(name,status);
    }
}
