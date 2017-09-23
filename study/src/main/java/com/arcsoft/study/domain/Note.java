package com.arcsoft.study.domain;

import java.util.Date;

public class Note {
    private Long id;

    private Long employeeId;

    private Long courseId;

    private String content;

    private Date date;

    public Note(Long id, Long employeeId, Long courseId, String content, Date date) {
        super();
        this.id = id;
        this.employeeId = employeeId;
        this.courseId = courseId;
        this.content = content;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
