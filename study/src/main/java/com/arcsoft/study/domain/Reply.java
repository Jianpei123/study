package com.arcsoft.study.domain;

import java.util.Date;

public class Reply {
    private Long replyId;

    private Long questionId;

    private Long answerId;

    private Long askerId;

    private String replyContent;

    private Date replyTime;

    private int read;// 0为未读，1为已读。

    private Long courseId;

    public Reply() {
        super();
    }

    public Reply(Long questionId, Long answerId, Long askerId, String replyContent, Date replyTime) {
        this.questionId = questionId;
        this.answerId = answerId;
        this.askerId = askerId;
        this.replyContent = replyContent;
        this.replyTime = replyTime;
    }

    public int getRead() {
        return read;
    }

    public void setRead(int read) {
        this.read = read;
    }

    public Long getReplyId() {
        return replyId;
    }

    public void setReplyId(Long replyId) {
        this.replyId = replyId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    public Long getAskerId() {
        return askerId;
    }

    public void setAskerId(Long askerId) {
        this.askerId = askerId;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent == null ? null : replyContent.trim();
    }

    public Date getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

}