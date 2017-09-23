package com.arcsoft.study.web;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arcsoft.study.domain.Reply;
import com.arcsoft.study.service.IQuestionService;
import com.arcsoft.study.service.IReplyService;

@RestController
public class ReplyController {

    @Autowired
    private IReplyService replyService;

    @Autowired
    private IQuestionService questionService;

    @RequestMapping("/addReply")
    public int addReply(Reply reply, HttpSession httpSession) {
        reply.setAnswerId((Long) httpSession.getAttribute("adminId"));
        reply.setReplyTime(new Date());
        reply.setRead(1);
        questionService.updateRead(reply.getQuestionId());
        return replyService.addReply(reply);
    }

}
