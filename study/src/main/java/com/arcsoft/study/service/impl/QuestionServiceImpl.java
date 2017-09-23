package com.arcsoft.study.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.arcsoft.study.domain.Question;
import com.arcsoft.study.mapper.QuestionMapper;
import com.arcsoft.study.service.IQuestionService;

@Service
public class QuestionServiceImpl implements IQuestionService {

    @Resource
    private QuestionMapper questionMapper;

    @Override
    public int addQuestion(Question question) {
        // TODO Auto-generated method stub
        return questionMapper.addQuestion(question);
    }

    @Override
    public int deleteQuestion(Long id) {
        // TODO Auto-generated method stub
        return questionMapper.deleteQuestion(id);
    }

    @Override
    public int updateQuestion(Long id) {
        // TODO Auto-generated method stub
        return questionMapper.updateQuestion(id);
    }

    @Override
    public List<Map> selectQuestionByVideoId(Long id, int questionType) {
        return questionMapper.selectQuestionByVideoId(id, questionType);
    }

    @Override
    public List<Map> selectQuestionByCourseId(Long id, int questionType) {
        return questionMapper.selectQuestionByCourseId(id, questionType);
    }

    @Override
    public int updateRead(Long id) {
        return questionMapper.updateRead(id);
    }

    @Override
    public List<Map> getQuestionByLecturer(Long lecturerId) {
        // TODO 自动生成的方法存根
        return questionMapper.getQuestionByLecturer(lecturerId);
    }

}
