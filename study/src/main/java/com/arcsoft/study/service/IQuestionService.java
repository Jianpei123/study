package com.arcsoft.study.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.arcsoft.study.domain.Question;

public interface IQuestionService {

    int addQuestion(Question question);

    int deleteQuestion(Long id);

    List<Map> selectQuestionByVideoId(Long id, int questionType);

    List<Map> selectQuestionByCourseId(Long id, int questionType);

    int updateQuestion(Long id);

    int updateRead(Long id);

    List<Map> getQuestionByLecturer(@Param("lecturerId") Long lecturerId);
}
