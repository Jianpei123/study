package com.arcsoft.study.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.arcsoft.study.domain.Employee;
import com.arcsoft.study.domain.Group;

@Mapper
public interface GroupMapper {
    @Insert("insert into groups value(0,#{name},#{creatorId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int addGroup(Group group);

    @Delete("delete from groups where id=#{id}")
    int deleteGroup(Group group);

    @Update("update groups set name=#{name} where id=#{id}")
    int updateGroupName(Group group);

    @Insert("insert into group_employee values(0,#{employeeId},#{groupId})")
    int addGroupEmployee(@Param("groupId") Long groupId, @Param("employeeId") Long employeeId);

    @Select("select employee_id from group_employee where group_id=#{groupId}")
    List<Long> getEmployeeByGroup(@Param("groupId") Long groupId);

    @Select("select * from groups where creator_id=#{id}")
    @Results({ @Result(id = true, property = "id", column = "id"), @Result(property = "name", column = "name"),
            @Result(property = "creatorId", column = "creator_id"),
            @Result(column = "id", property = "members", many = @Many(select = "com.arcsoft.mapper.GroupMapper.getEmployeeByGroup")) })
    List<Map> getMyGroup(Employee employee);

    @Select("select e.id from employee e left join group_employee ge on e.id=ge.employee_id where ge.group_id in (${groupsId})")
    List<Long> getEmployeeByGroups(@Param("groupsId") String groupsId);
}
