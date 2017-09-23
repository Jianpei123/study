package com.arcsoft.study.service;

import java.util.List;
import java.util.Map;

import com.arcsoft.study.domain.Course;

public interface ICourserService {
    List<Course> findAllCourse();

    int addCourse(Course course, List<Long> type, List<Long> members, List<Long> groups);

    int deleteCourse(Course course);

    int updateCourse(Course course, List<Long> type, List<Long> addMembers, List<Long> deleteMembers,
            List<Long> addGroups, List<Long> deleteGropus);

    List<Map> findCourseByTeacher(Long lecturerId);

    List<Map> getRecommendedCourse(Long employeeId);

    List<Map> getRecentCourse(Long employeeId);
}
