package com.arcsoft.study.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arcsoft.study.domain.Chapter;
import com.arcsoft.study.mapper.ChapterMapper;
import com.arcsoft.study.service.IChapterService;

@RestController
public class ChapterController {

    @Resource
    private IChapterService chapterService;

    @RequestMapping("/addChapter")
    public int addChapter(Chapter chapter) {
        return chapterService.addChapter(chapter);
    }

    @RequestMapping("/updateChapter")
    public int updateChapter(Chapter chapter) {
        // TODO 自动生成的方法存根
        return chapterService.updateChapter(chapter);
    }

    @RequestMapping("/deleteChapter")
    public int deleteChapter(Chapter chapter) {
        return chapterService.deleteChapter(chapter);
    }

    @RequestMapping("/getChaptersByCourse")
    public List<Map> getChaptersByCourse(Long courseId) {
        return chapterService.getChaptersByCourse(courseId);
    }
}
