package com.arcsoft.study.domain;

import java.util.Date;

public class Question {
    private Long questionId;

    private Long courseId;

    private Long videoId;

    private String questionContent;

    private Date questionTime;

    private Long questionAsker;

    private Integer questionLike;// 可空

    private Integer questionType;// 0为综合讨论区 ，1为老师答疑区

    private Integer read;// 0为未读，1为已读，默认为0

    public Question() {
        super();
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Integer getQuestionType() {
        return questionType;
    }

    public void setQuestionType(Integer questionType) {
        this.questionType = questionType;
    }

    public Integer getRead() {
        return read;
    }

    public void setRead(Integer read) {
        this.read = read;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getVideoId() {
        return videoId;
    }

    public void setVideoId(Long videoId) {
        this.videoId = videoId;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent == null ? null : questionContent.trim();
    }

    public Date getQuestionTime() {
        return questionTime;
    }

    public void setQuestionTime(Date questionTime) {
        this.questionTime = questionTime;
    }

    public Long getQuestionAsker() {
        return questionAsker;
    }

    public void setQuestionAsker(Long questionAsker) {
        this.questionAsker = questionAsker;
    }

    public Integer getQuestionLike() {
        return questionLike;
    }

    public void setQuestionLike(Integer questionLike) {
        this.questionLike = questionLike;
    }
}