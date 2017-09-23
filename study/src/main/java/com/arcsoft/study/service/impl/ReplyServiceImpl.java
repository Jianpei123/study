package com.arcsoft.study.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.arcsoft.study.domain.Reply;
import com.arcsoft.study.mapper.ReplyMapper;
import com.arcsoft.study.service.IReplyService;

@Service
public class ReplyServiceImpl implements IReplyService {

    @Resource
    private ReplyMapper replyMapper;

    @Override
    public List<Map> getReplyByQuestionId(Long id) {
        return replyMapper.getReplyByQuestionId(id);
    }

    @Override
    public int addReply(Reply reply) {
        return replyMapper.addReply(reply);
    }

    @Override
    public int deleteReply(Long id) {
        return replyMapper.deleteReply(id);
    }

    @Override
    public List<Map> getReplyNotRead(Long targetId) {

        return replyMapper.getReplyNotRead(targetId);
    }

    @Override
    public int updateRead(Long id) {
        return replyMapper.updateRead(id);
    }

    @Override
    public List<Map> getReplyByLecturer(Long lecturerId) {
        // TODO 自动生成的方法存根
        return replyMapper.getReplyByLecturer(lecturerId);
    }

}
