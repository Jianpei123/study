package com.arcsoft.study.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arcsoft.study.domain.Department;
import com.arcsoft.study.service.IDepartmentService;

@RestController
public class DepartmentController {

    @Resource
    private IDepartmentService departmentService;

    @RequestMapping("/addDepartment")
    public int addDepartment(Department department) {
        return departmentService.addDepartment(department);
    }

    @RequestMapping("/updateDepartment")
    public int updateDepartment(Department department) {
        return departmentService.updateDepartment(department);
    }

    @RequestMapping("/deleteDepartment")
    public int deleteDepartment(Department department) {
        return departmentService.deleteDepartment(department);
    }

    @RequestMapping("/getAllDepartment")
    public List<Department> getAllDepartment() {
        return departmentService.getAllDepartment();
    }
}
