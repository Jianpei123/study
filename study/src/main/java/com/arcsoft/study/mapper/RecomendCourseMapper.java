package com.arcsoft.study.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface RecomendCourseMapper {
    /**
     * @描述 根据用户的播放记录、用户的收藏记录、得出课程的标签 、管理者
     * @param 员工id
     * @return Map（）
     */
    @Select("SELECT t.id type, c.lecturer_id lecturer  FROM course_employee ce ,course c, type t ,employee e WHERE ce.id >= ((SELECT MAX(ce.id) FROM course_employee ce)-(SELECT  MIN(ce.id) FROM course_employee ce)) * RAND() + (SELECT MIN(ce.id) FROM course_employee ce)  and ce.employee_id=5   and  ce.course_id=c.id    and  c.type_id=t.id  LIMIT 1;")
    Map getUserInfo(Long employeeId);

}
