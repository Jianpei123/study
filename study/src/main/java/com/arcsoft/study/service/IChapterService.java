package com.arcsoft.study.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.arcsoft.study.domain.Chapter;

public interface IChapterService {

    int addChapter(Chapter chapter);

    int updateChapter(Chapter chapter);

    int deleteChapter(Chapter chapter);

    List<Map> getChaptersByCourse(@Param("courseId") Long courseId);
}
