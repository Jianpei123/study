package com.arcsoft.study.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arcsoft.study.domain.Type;
import com.arcsoft.study.service.ITypeService;
import com.arcsoft.study.util.Constant;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@RestController
public class TypeController {

    @Resource
    private ITypeService typeSercie;

    @RequestMapping("/getPrimaryType")
    public List<Type> getPrimaryType() {
        return typeSercie.getPrimaryType();
    }

    @RequestMapping("/addType")
    public int addType(Type type, HttpSession httpSession) {
        type.setCreatorId((Long) httpSession.getAttribute("adminId"));
        return typeSercie.addType(type);
    }

    @RequestMapping("/getAllType")
    public List<Map> getAllType(Integer pageNum) {
        Page<Object> page = PageHelper.startPage(pageNum, Constant.pageSize);
        List<Map> result = typeSercie.getAllType();
        Map<String, Object> total = new HashMap<>();
        total.put("size", Constant.pageSize);
        total.put("total", page.getTotal());
        result.add(total);
        return result;
    }
    
    @RequestMapping("/updateType")
    public int updateType(Type type) {
        return typeSercie.updateType(type);
    }

    @RequestMapping("/deleteType")
    public int deleteType(Type type) {
        return typeSercie.deleteType(type);
    }

    @RequestMapping("/getCascadedType")
    public List<Map<String, Object>> getCascadedType() {
        return typeSercie.getCascadedType();
    }
}
