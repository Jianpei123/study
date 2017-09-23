package com.arcsoft.study.mapper;

import java.util.List;
import java.util.Map;

public class BatchProvider {
    public String addCourseGroups(Map<String, Object> paramsMap) {
        List<Long> groups = (List<Long>) paramsMap.get("groups");
        Long courseId = (Long) paramsMap.get("courseId");
        StringBuilder sql = new StringBuilder();
        sql.append("insert into course_group ");
        sql.append("values ");
        for (int i = 0; i < groups.size(); i++) {
            sql.append("(0,#{courseId}," + "#{groups[" + i + "]})");
            sql.append(",");
        }
        sql.deleteCharAt(sql.length() - 1);
        return sql.toString();
    }

    public String deleteCourseGroups(Map<String, Object> paramsMap) {
        List<Long> groups = (List<Long>) paramsMap.get("groups");
        Long courseId = (Long) paramsMap.get("courseId");
        StringBuilder sql = new StringBuilder();
        for (int i = 0; i < groups.size(); i++) {
            sql.append("delete from course_group where course_id=#{courseId} and group_id=" + "#{groups[" + i + "]};");
        }
        sql.deleteCharAt(sql.length() - 1);
        return sql.toString();
    }

    public String addCourseEmployees(Map<String, Object> paramsMap) {
        List<Long> members = (List<Long>) paramsMap.get("members");
        Long courseId = (Long) paramsMap.get("courseId");
        StringBuilder sql = new StringBuilder();
        sql.append("insert into course_employee ");
        sql.append("values ");
        for (int i = 0; i < members.size(); i++) {
            sql.append("(0,#{courseId}," + "#{members[" + i + "]},0,0)");
            sql.append(",");
        }
        sql.deleteCharAt(sql.length() - 1);
        return sql.toString();
    }

    public String deleteCourseEmployees(Map<String, Object> paramsMap) {
        List<Long> members = (List<Long>) paramsMap.get("members");
        Long courseId = (Long) paramsMap.get("courseId");
        StringBuilder sql = new StringBuilder();

        for (int i = 0; i < members.size(); i++) {
            sql.append("delete from course_employee where course_id=#{courseId} and employee_id=" + "#{members[" + i
                    + "]};");
        }
        sql.deleteCharAt(sql.length() - 1);
        return sql.toString();
    }

}
