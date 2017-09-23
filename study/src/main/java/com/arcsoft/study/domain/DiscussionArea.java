package com.arcsoft.study.domain;

public class DiscussionArea {
    private Long id;

    private Long courseId;

    private String type;

    public DiscussionArea(Long id, Long courseId, String type) {
        super();
        this.id = id;
        this.courseId = courseId;
        this.type = type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
