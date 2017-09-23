package com.arcsoft.study.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.arcsoft.study.domain.Employee;
import com.arcsoft.study.domain.Group;
import com.arcsoft.study.domain.GroupEmployee;
import com.arcsoft.study.mapper.GroupMapper;
import com.arcsoft.study.service.IGroupService;

@Service
public class GroupServiceImpl implements IGroupService {

    @Resource
    private GroupMapper groupMapper;

    @Override
    public int addGroup(Group group) {
        // TODO 自动生成的方法存根
        groupMapper.addGroup(group);
        Long groupId = group.getId();
        for (Long employeeId : group.getMembers()) {
            groupMapper.addGroupEmployee(groupId, employeeId);
        }
        return 0;
    }

    @Override
    public int deleteGroup(Group group) {
        // TODO 自动生成的方法存根
        return groupMapper.deleteGroup(group);
    }

    @Override
    public int updateGroupName(Group group, List<Long> addMembers, List<Long> deleteMembers) {
        // TODO 自动生成的方法存根
        return groupMapper.updateGroupName(group);
    }

    @Override
    public List<Map> getMyGroup(Employee employee) {
        // TODO 自动生成的方法存根
        return groupMapper.getMyGroup(employee);
    }

}
