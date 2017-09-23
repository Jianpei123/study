package com.arcsoft.study.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arcsoft.study.domain.Role;
import com.arcsoft.study.mapper.RoleMapper;
import com.arcsoft.study.service.IRoleService;

@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public int addRole(String name) {
        int n = roleMapper.addRole(name);
        return n;
    }

    @Override
    public int deleteRole(int id) {
        int n = roleMapper.deleteRole(id);
        return n;
    }

    @Override
    public int updateRole(Role role) {
        int n = roleMapper.updateRole(role);
        return n;
    }

    @Override
    public List<Role> selectRole() {
        List<Role> roleList = roleMapper.selectRole();
        return roleList;
    }

}
