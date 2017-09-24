package com.arcsoft.study.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.arcsoft.study.domain.Employee;

@Mapper
public interface EmployeeMapper {
    @Insert("insert into employee values(0,#{name},#{sex},#{age},#{phone},#{jobNumber},000000,#{departmentId},#{roleId},1,0)")
    @Options(useGeneratedKeys = true)
    int addEmployee(Employee employee);

    @Update("update employee set name=#{name},sex=#{sex},age=#{age},phone=#{phone},job_number=#{jobNumber},department_id=#{departmentId},state=#{state},role_id=#{roleId} where id=#{id}")
    int updateEmployee(Employee employee);

    @Select("select id,name,password,role_id roleId,icon from employee where phone=#{phone}")
    Employee getEmployeeByPhone(Employee employee);

    @Select("select id,name from employee where department_id=#{departmentId}")
    List<Employee> getEmployeeByDepartment(@Param("departmentId") Long departmentId);

    @Select("select id,name from department")
    @Results({ @Result(id = true, property = "id", column = "id"), @Result(property = "name", column = "name"),
            @Result(property = "members", column = "id", many = @Many(select = "com.arcsoft.study.mapper.EmployeeMapper.getEmployeeByDepartment")) })
    List<Map> getEmployeeGroupByDepartment();

    /**
     * 
     * @param state
     * @return List<Employee>
     * @function 根据在职状态查询员工
     */
    @Select("select * from employee where state=#{state}")
    public List<Map> findEmployeeByState(String state);

    @Select("select e.id,e.name,e.job_number jobNumber,e.phone,e.sex,e.age,e.state,d.name departmentName,d.id departmentId,r.id roleId,r.name roleName from employee e left join department d on e.department_id=d.id left join role r on e.role_id=r.id order by job_number")
    List<Map> getAllEmployee();

    /**
     * 
     * @param name
     * @return List<Employee>
     * @function 根据名字查询员工信息
     */
    @Select("select e.id,e.name,e.job_number jobNumber,e.phone,e.sex,e.age,e.state,d.name departmentName,d.id departmentId,r.id roleId,r.name roleName from employee e left join department d on e.department_id=d.id left join role r on r.id=e.role_id where e.name like #{0} order by job_number")
    public List<Map> findEmployeeByName(String name);

    /**
     * 
     * @param employeeList
     * @return
     * @function 批量插入员工信息
     */
    @InsertProvider(type = com.arcsoft.study.util.InsertManyEmployees.class, method = "insertAll")
    @Options(useGeneratedKeys = true)
    public int addEmployeeExcel(@Param("list") List<Employee> employeeList);

    /**
     * 
     * @param employee
     * @return n
     * @function 更新密码
     */
    @Update("update employee set password =#{password} where id=#{id} ")
    public int updatePassword(Employee employee);

    @Select("select id,name,icon from employee where id=#{id}")
    public Map findEmployeeById(Long id);

    @Update("update employee set icon=#{url} where id=#{id}")
    public int updateIcon(@Param("id") Long id, @Param("url") String url);

    @Select("select e.id,e.name,e.job_number jobNumber,e.phone,e.sex,e.age,e.state,d.name departmentName,d.id departmentId,r.id roleId,r.name roleName from employee e left join department d on e.department_id=d.id left join role r on e.role_id=r.id where e.role_id=2 order by job_number")
    public List<Map> getAllTeacher();
}
