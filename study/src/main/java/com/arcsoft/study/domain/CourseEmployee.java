package com.arcsoft.study.domain;

public class CourseEmployee {
    private Long id;

    private Long courseId;

    private Long employeeId;

    private Long sectionId;

    private Integer progress;

    public CourseEmployee(Long id, Long courseId, Long employeeId, Long sectionId, Integer progress) {
        super();
        this.id = id;
        this.courseId = courseId;
        this.employeeId = employeeId;
        this.sectionId = sectionId;
        this.progress = progress;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getSectionId() {
        return sectionId;
    }

    public void setSectionId(Long sectionId) {
        this.sectionId = sectionId;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

}
