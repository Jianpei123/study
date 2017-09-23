package com.arcsoft.study.service;

import java.util.List;
import java.util.Map;

import com.arcsoft.study.domain.Reply;

public interface IReplyService {

    List<Map> getReplyByQuestionId(Long id);

    int addReply(Reply reply);

    int deleteReply(Long id);

    List<Map> getReplyNotRead(Long targetId);

    int updateRead(Long id);

    List<Map> getReplyByLecturer(Long lecturerId);
}
