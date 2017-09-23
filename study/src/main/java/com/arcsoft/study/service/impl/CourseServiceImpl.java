package com.arcsoft.study.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arcsoft.study.domain.Course;
import com.arcsoft.study.mapper.CourseMapper;
import com.arcsoft.study.mapper.GroupMapper;
import com.arcsoft.study.service.ICourserService;

@Service
public class CourseServiceImpl implements ICourserService {

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private GroupMapper groupMapper;

    @Override
    public List<Course> findAllCourse() {
        // TODO 自动生成的方法存根
        return courseMapper.findAllCourse();
    }

    @Override
    public List<Map> findCourseByTeacher(Long lecturerId) {
        // TODO 自动生成的方法存根
        return courseMapper.findCourseByTeacher(lecturerId);
    }

    @Override
    public int addCourse(Course course, List<Long> type, List<Long> members, List<Long> groups) {
        // TODO 自动生成的方法存根
        course.setTypeId(type.get(1));
        courseMapper.addCourse(course);
        Long courseId = course.getId();
        if (members != null) {
            courseMapper.addCourseEmployees(courseId, members);
        }
        if (members != null) {
            courseMapper.addCourseGroups(courseId, groups);
        }
        return 0;
    }

    @Override
    public int deleteCourse(Course course) {
        // TODO 自动生成的方法存根
        return courseMapper.deleteCourse(course);
    }

    @Override
    public int updateCourse(Course course, List<Long> type, List<Long> addMembers, List<Long> deleteMembers,
            List<Long> addGroups, List<Long> deleteGropus) {
        // TODO 自动生成的方法存根
        course.setTypeId(type.get(1));
        courseMapper.updateCourse(course);
        Long courseId = course.getId();
        if (addMembers != null) {
            courseMapper.addCourseEmployees(courseId, addMembers);
        }

        if (deleteMembers != null) {
            courseMapper.deleteCourseEmployees(courseId, deleteMembers);
        }

        if (addGroups != null) {
            courseMapper.addCourseGroups(courseId, addGroups);
        }

        if (deleteGropus != null) {
            courseMapper.deleteCourseGroups(courseId, deleteGropus);
        }
        return courseMapper.updateCourse(course);
    }

    @Override
    public List<Map> getRecommendedCourse(Long employeeId) {
        // TODO 自动生成的方法存根
        return courseMapper.getRecommendedCourse(employeeId);
    }

    @Override
    public List<Map> getRecentCourse(Long employeeId) {
        // TODO 自动生成的方法存根
        return courseMapper.getRecentCourse(employeeId);
    }

}
