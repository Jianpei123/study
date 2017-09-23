package com.arcsoft.study.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.arcsoft.study.domain.Chapter;
import com.arcsoft.study.mapper.ChapterMapper;
import com.arcsoft.study.service.IChapterService;

@Service
public class ChapterServiceImpl implements IChapterService {

    @Resource
    private ChapterMapper chapterMapper;

    @Override
    public int addChapter(Chapter chapter) {
        // TODO 自动生成的方法存根
        return chapterMapper.addChapter(chapter);
    }

    @Override
    public int updateChapter(Chapter chapter) {
        // TODO 自动生成的方法存根
        return chapterMapper.updateChapter(chapter);
    }

    @Override
    public int deleteChapter(Chapter chapter) {
        // TODO 自动生成的方法存根
        return chapterMapper.deleteChapter(chapter);
    }

    @Override
    public List<Map> getChaptersByCourse(Long courseId) {
        // TODO 自动生成的方法存根
        return chapterMapper.getChaptersByCourse(courseId);
    }

}
