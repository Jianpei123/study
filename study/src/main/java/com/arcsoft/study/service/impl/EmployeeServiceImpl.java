package com.arcsoft.study.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.arcsoft.study.domain.Employee;
import com.arcsoft.study.mapper.EmployeeMapper;
import com.arcsoft.study.service.IEmployeeService;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Resource
    private EmployeeMapper employeeMapper;

    @Override
    public int addEmployee(Employee employee) {
        // TODO 自动生成的方法存根
        return employeeMapper.addEmployee(employee);
    }

    @Override
    public List<Map> getAllEmployee() {
        // TODO 自动生成的方法存根
        return employeeMapper.getAllEmployee();
    }

    @Override
    public int updateEmployee(Employee employee) {
        // TODO 自动生成的方法存根
        return employeeMapper.updateEmployee(employee);
    }

    @Override
    public Employee getEmployeeByPhone(Employee employee) {
        // TODO 自动生成的方法存根
        return employeeMapper.getEmployeeByPhone(employee);
    }

    @Override
    public List<Map> getEmployeeGroupByDepartment() {
        // TODO 自动生成的方法存根
        return employeeMapper.getEmployeeGroupByDepartment();
    }

    @Override
    public int updatePassword(Employee employee) {
        int n = employeeMapper.updatePassword(employee);
        return n;
    }

    @Override
    public List<Map> findEmployeeByState(String state) {
        // TODO Auto-generated method stub
        return employeeMapper.findEmployeeByState(state);
    }

    @Override
    public List<Map> findEmployeeByName(String name) {
        // TODO Auto-generated method stub
        return employeeMapper.findEmployeeByName(name);
    }

    @Override
    public int addEmployeeExcel(List<Employee> employeeList) {
        // TODO Auto-generated method stub
        return employeeMapper.addEmployeeExcel(employeeList);
    }

    @Override
    public int updateIcon(Long id, String url) {
        // TODO 自动生成的方法存根
        return employeeMapper.updateIcon(id, url);
    }

    @Override
    public Map findEmployeeById(Long id) {
        // TODO 自动生成的方法存根
        return employeeMapper.findEmployeeById(id);
    }

    @Override
    public List<Map> getAllTeacher() {
        // TODO 自动生成的方法存根
        return employeeMapper.getAllTeacher();
    }

}
