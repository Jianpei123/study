package com.arcsoft.study.domain;

import java.util.Date;

public class Comment {
    private Long id;

    private Long employeeId;

    private String content;

    private Long discussionAreaId;

    private Date date;

    private Long parentCommentId;

    public Comment(Long id, Long employeeId, String content, Long discussionAreaId, Date date, Long parentCommentId) {
        super();
        this.id = id;
        this.employeeId = employeeId;
        this.content = content;
        this.discussionAreaId = discussionAreaId;
        this.date = date;
        this.parentCommentId = parentCommentId;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getDiscussionAreaId() {
        return discussionAreaId;
    }

    public void setDiscussionAreaId(Long discussionAreaId) {
        this.discussionAreaId = discussionAreaId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getParentCommentId() {
        return parentCommentId;
    }

    public void setParentCommentId(Long parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

}
