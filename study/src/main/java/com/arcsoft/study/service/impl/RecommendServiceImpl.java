package com.arcsoft.study.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arcsoft.study.mapper.CourseMapper;
import com.arcsoft.study.mapper.RecomendCourseMapper;
import com.arcsoft.study.service.IRecommendService;

@Service
public class RecommendServiceImpl implements IRecommendService {

    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private RecomendCourseMapper rcMapper;

    @Override
    public List<Map> recommendCourse(Long employeeId) {
        Map userInfo = rcMapper.getUserInfo(employeeId);
        long typeId = (long) userInfo.get("type");
        long lecturerId = (long) userInfo.get("lecturer");
        List<Map> map1 = courseMapper.findCourseByType(employeeId, typeId);
        List<Map> map2 = courseMapper.findCourseByLecturer(employeeId, lecturerId);
        map1.addAll(map2);
        return map1;
    }

}
