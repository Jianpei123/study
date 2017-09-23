package com.arcsoft.study.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.arcsoft.study.domain.Department;
import com.arcsoft.study.mapper.DepartmentMapper;
import com.arcsoft.study.service.IDepartmentService;

@Service
public class DepartmentServiceImpl implements IDepartmentService {

    @Resource
    private DepartmentMapper departmentMapper;

    @Override
    public int addDepartment(Department department) {
        // TODO 自动生成的方法存根
        return departmentMapper.addDepartment(department);
    }

    @Override
    public int updateDepartment(Department department) {
        // TODO 自动生成的方法存根
        return departmentMapper.updateDepartment(department);
    }

    @Override
    public int deleteDepartment(Department department) {
        // TODO 自动生成的方法存根
        return departmentMapper.deleteDepartment(department);
    }

    @Override
    public List<Department> getAllDepartment() {
        // TODO 自动生成的方法存根
        return departmentMapper.getAllDepartment();
    }

}
