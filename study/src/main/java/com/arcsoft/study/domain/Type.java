package com.arcsoft.study.domain;

public class Type {
    private Long id;

    private String name;

    private Long parentTypeId;

    private Long creatorId;

    public Type() {
        super();
    }

    public Type(Long id, String name, Long parentTypeId, Long creatorId) {
        super();
        this.id = id;
        this.name = name;
        this.parentTypeId = parentTypeId;
        this.creatorId = creatorId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentTypeId() {
        return parentTypeId;
    }

    public void setParentTypeId(Long parentTypeId) {
        this.parentTypeId = parentTypeId;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

}
