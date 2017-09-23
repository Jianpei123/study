package com.arcsoft.study.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arcsoft.study.domain.Question;
import com.arcsoft.study.service.IQuestionService;

@RestController
public class QuestionController {

    @Autowired
    private IQuestionService questionService;

    @RequestMapping("/addQuestion")
    public int addQuestion(Question question) {
        return questionService.addQuestion(question);
    }

    @RequestMapping("/deleteQuestion")
    public int deleteQuestion(Long id) {
        return questionService.deleteQuestion(id);
    }

    @RequestMapping("/updateQuestion")
    public int updateQuestion(Long id) {
        return questionService.updateQuestion(id);
    }

}
