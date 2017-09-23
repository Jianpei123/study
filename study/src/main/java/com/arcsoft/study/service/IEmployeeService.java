package com.arcsoft.study.service;

import java.util.List;
import java.util.Map;

import com.arcsoft.study.domain.Employee;

public interface IEmployeeService {
    int addEmployee(Employee employee);

    List<Map> getAllEmployee();

    int updateEmployee(Employee employee);

    Employee getEmployeeByPhone(Employee employee);

    List<Map> getEmployeeGroupByDepartment();

    /**
     * 
     * @param state
     * @return List<Employee>
     * @function 根据在职状态查询员工
     */
    public List<Map> findEmployeeByState(String state);

    /**
     * 
     * @param name
     * @return List<Employee>
     * @function 根据名字查询员工信息
     */
    public List<Map> findEmployeeByName(String name);

    public Map findEmployeeById(Long id);

    /**
     * 
     * @param employee
     * @return n
     * @function 更新密码
     */
    public int updatePassword(Employee employee);

    /**
     * 
     * @param employeeList
     * @return n
     * @function 批量插入员工信息
     */
    public int addEmployeeExcel(List<Employee> employeeList);

    public int updateIcon(Long id, String url);

    public List<Map> getAllTeacher();

}
