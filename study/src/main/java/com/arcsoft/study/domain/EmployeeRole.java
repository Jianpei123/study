package com.arcsoft.study.domain;

public class EmployeeRole {
    private Long id;

    private Long employeeId;

    private Long roleId;

    public EmployeeRole(Long id, Long employeeId, Long roleId) {
        super();
        this.id = id;
        this.employeeId = employeeId;
        this.roleId = roleId;
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

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

}
