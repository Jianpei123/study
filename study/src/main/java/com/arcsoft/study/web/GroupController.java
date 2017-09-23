package com.arcsoft.study.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.arcsoft.study.domain.Employee;
import com.arcsoft.study.domain.Group;
import com.arcsoft.study.service.IGroupService;
import com.arcsoft.study.util.Constant;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@RestController
public class GroupController {

    @Resource
    private IGroupService groupService;

    @RequestMapping("/addGroup")
    public int addGroup(@RequestBody Group group, HttpSession httpSession) {
        group.setCreatorId((Long) httpSession.getAttribute("adminId"));
        return groupService.addGroup(group);
    }

    @RequestMapping("/deleteGroup")
    public int deleteGroup(Group group) {
        return groupService.deleteGroup(group);
    }

    @RequestMapping("/updateGroupName")
    public int updateGroupName(Group group,
            @RequestParam(value = "addMembers[]", required = false) List<Long> addMembers,
            @RequestParam(value = "deleteMembers[]", required = false) List<Long> deleteMembers) {
        return groupService.updateGroupName(group, addMembers, deleteMembers);
    }

    @RequestMapping("/getMyGroup")
    public List<Map> getMyGroup(Employee employee, HttpSession httpSession, Integer pageNum) {
        employee.setId((Long) httpSession.getAttribute("adminId"));
        if (pageNum != null) {
            Page<Object> page = PageHelper.startPage(pageNum, Constant.pageSize);
            List<Map> result = groupService.getMyGroup(employee);
            Map<String, Object> total = new HashMap<>();
            total.put("size", Constant.pageSize);
            total.put("total", page.getTotal());
            result.add(total);
            return result;
        }
        return groupService.getMyGroup(employee);
    }
}
