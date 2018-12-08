package com.lyh.service.ServiceImpl;

import com.lyh.mapper.ReplyMapper;
import com.lyh.model.Reply;
import com.lyh.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReplyServiceImpl implements ReplyService {
    @Autowired
    ReplyMapper replyMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return replyMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Reply record) {
        return replyMapper.insert(record);
    }

    @Override
    public int insertSelective(Reply record) {
        return replyMapper.insertSelective(record);
    }

    @Override
    public Reply selectByPrimaryKey(Long id) {
        return replyMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Reply record) {
        return replyMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Reply record) {
        return replyMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Reply> findReply(Long commentId, String nickname) {
        return replyMapper.findReply(commentId,nickname);
    }
}
