package com.arcsoft.study.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.arcsoft.study.domain.Department;

@Mapper
public interface DepartmentMapper {
    @Insert("insert into department values(0,#{name})")
    int addDepartment(Department department);

    @Update("update department set name=#{name}")
    int updateDepartment(Department department);

    @Delete("delete from department where id=#{id}")
    int deleteDepartment(Department department);

    @Select("select * from department")
    List<Department> getAllDepartment();
}
