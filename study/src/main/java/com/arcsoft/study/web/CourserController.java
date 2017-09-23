package com.arcsoft.study.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.arcsoft.study.domain.Course;
import com.arcsoft.study.service.ICourserService;
import com.arcsoft.study.service.IQuestionService;
import com.arcsoft.study.service.IRecommendService;
import com.arcsoft.study.service.IReplyService;
import com.arcsoft.study.util.QINIUServer;

@RestController
public class CourserController {

    @Autowired
    private ICourserService couserService;

    @Autowired
    private IRecommendService recommendService;

    @Autowired
    private IQuestionService questionService;

    @Autowired
    private IReplyService replyService;

    @RequestMapping("/findAllCourse")
    public List<Course> findAllCourse() {
        return couserService.findAllCourse();
    }

    @RequestMapping("/findCourseByTeacher")
    public List<Map> findCourseByTeacher(HttpSession httpSession) {
        return couserService.findCourseByTeacher((Long) httpSession.getAttribute("adminId"));
    }

    @RequestMapping("/addCourse")
    public int addCourse(Course course, @RequestParam("type[]") List<Long> type,
            @RequestParam(value = "members[]", required = false) List<Long> members,
            @RequestParam(value = "groups[]", required = false) List<Long> groups, HttpSession httpSession) {
        course.setCreateTime(new Date());
        course.setLecturerId((Long) httpSession.getAttribute("adminId"));
        return couserService.addCourse(course, type, members, groups);
    }

    @RequestMapping("/deleteCourse")
    public int deleteCourse(Course course) {
        return couserService.deleteCourse(course);
    }

    @RequestMapping("/updateCourse")
    public int updateCourse(Course course, @RequestParam("type[]") List<Long> type,
            @RequestParam(value = "addMembers[]", required = false) List<Long> addMembers,
            @RequestParam(value = "deleteMembers[]", required = false) List<Long> deleteMembers,
            @RequestParam(value = "addGroups[]", required = false) List<Long> addGroups,
            @RequestParam(value = "deleteGroups[]", required = false) List<Long> deleteGropus) {
        return couserService.updateCourse(course, type, addMembers, deleteMembers, addGroups, deleteGropus);
    }

    @RequestMapping("/getUpToken")
    public String getUpToken() {
        return QINIUServer.getUpToken();
    }

    @RequestMapping("/getRecommendedCourse")
    public List<Map> getRecommendCourse(HttpSession httpSession) {
        // return recommendService.recommendCourse((Long)
        // httpSession.getAttribute("userId"));
        return recommendService.recommendCourse(new Long(1));
    }

    @RequestMapping("/getRecentCourse")
    public List<Map> getRecentCourse(Long employeeId) {
        return couserService.getRecentCourse(employeeId);
    }

    @RequestMapping("/getQuestionsAndReplysByCourse")
    public List<Map> getQuestionsByCourse(HttpSession httpSession) {
        List<Map> result = new ArrayList<>();
        result.addAll(questionService.getQuestionByLecturer((Long) httpSession.getAttribute("adminId")));
        result.addAll(replyService.getReplyByLecturer((Long) httpSession.getAttribute("adminId")));
        return result;
    }
}
