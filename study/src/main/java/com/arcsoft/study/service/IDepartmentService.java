package com.arcsoft.study.service;

import java.util.List;

import com.arcsoft.study.domain.Department;

public interface IDepartmentService {
    int addDepartment(Department department);

    int updateDepartment(Department department);

    int deleteDepartment(Department department);

    List<Department> getAllDepartment();

}
