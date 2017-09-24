package com.arcsoft.study.service;

import java.util.List;
import java.util.Map;

import com.arcsoft.study.domain.Employee;
import com.arcsoft.study.domain.Group;

public interface IGroupService {

    int addGroup(Group group);

    int deleteGroup(Group group);

    int updateGroupName(Group group, List<Long> addMembers, List<Long> deleteMembers);

    List<Map> getMyGroup(Employee employee);

}
