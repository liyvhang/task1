package com.lyh.service;

import com.lyh.model.Reply;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReplyService {
    int deleteByPrimaryKey(Long id);

    int insert(Reply record);

    int insertSelective(Reply record);

    Reply selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Reply record);

    int updateByPrimaryKey(Reply record);

    List<Reply> findReply(@Param("commentId")Long commentId, @Param("nickname")String nickname);
}
