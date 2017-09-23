package com.arcsoft.study.domain;

public class GroupEmployee {
    private Long id;

    private Long employeeId;

    private Long groupId;

    public GroupEmployee() {
        super();
    }

    public GroupEmployee(Long id, Long employeeId, Long groupId) {
        super();
        this.id = id;
        this.employeeId = employeeId;
        this.groupId = groupId;
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

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

}
