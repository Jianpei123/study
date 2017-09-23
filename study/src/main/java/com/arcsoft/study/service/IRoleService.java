package com.arcsoft.study.service;

import java.util.List;

import com.arcsoft.study.domain.Role;

public interface IRoleService {
    public int addRole(String name);

    public int deleteRole(int id);

    public int updateRole(Role role);

    public List<Role> selectRole();
}
