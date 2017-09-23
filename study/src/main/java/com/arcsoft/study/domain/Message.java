package com.arcsoft.study.domain;

import java.util.Date;

public class Message {
    private Long id;

    private String content;

    private Long messageTypeId;

    private Date date;

    private Long employeeId;

    private Integer read;

    public Message(Long id, String content, Long messageTypeId, Date date, Long employeeId, Integer read) {
        super();
        this.id = id;
        this.content = content;
        this.messageTypeId = messageTypeId;
        this.date = date;
        this.employeeId = employeeId;
        this.read = read;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getMessageTypeId() {
        return messageTypeId;
    }

    public void setMessageTypeId(Long messageTypeId) {
        this.messageTypeId = messageTypeId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getRead() {
        return read;
    }

    public void setRead(Integer read) {
        this.read = read;
    }

}
