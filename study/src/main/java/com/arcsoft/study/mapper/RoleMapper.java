package com.arcsoft.study.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.arcsoft.study.domain.Role;

@Mapper
public interface RoleMapper {
    @Insert("insert into role (name) values(#{name})")
    public int addRole(String name);

    @Delete("delete from role where id=#{id}")
    public int deleteRole(int id);

    @Update("update role set name=#{name} where id=#{id}")
    public int updateRole(Role role);

    @Select("select * from role ")
    public List<Role> selectRole();
}
