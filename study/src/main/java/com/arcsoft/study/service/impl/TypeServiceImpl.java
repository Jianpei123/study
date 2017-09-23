package com.arcsoft.study.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.arcsoft.study.domain.Type;
import com.arcsoft.study.mapper.TypeMapper;
import com.arcsoft.study.service.ITypeService;

@Service
public class TypeServiceImpl implements ITypeService {

    @Resource
    private TypeMapper typeMapper;

    @Override
    public int addType(Type type) {
        // TODO 自动生成的方法存根
        return typeMapper.addType(type);
    }

    @Override
    public List<Type> getPrimaryType() {
        // TODO 自动生成的方法存根
        return typeMapper.getPrimaryType();
    }

    @Override
    public List<Map> getAllType() {
        // TODO 自动生成的方法存根
        return typeMapper.getAllType();
    }

    @Override
    public int updateType(Type type) {
        // TODO 自动生成的方法存根
        return typeMapper.updateType(type);
    }

    @Override
    public int deleteType(Type type) {
        // TODO 自动生成的方法存根
        return typeMapper.deleteType(type);
    }

    @Override
    public List<Map<String, Object>> getCascadedType() {
        // TODO 自动生成的方法存根
        List<Map<String, Object>> dbTypes = typeMapper.getCascadedType();
        List<Map<String, Object>> result = new ArrayList<>();
        for (Map<String, Object> type : dbTypes) {
            if ("1".equals(type.get("parentTypeId").toString())) {
                Map<String, Object> parentType = new HashMap<>();
                parentType.put("value", type.get("value"));
                parentType.put("label", type.get("label"));
                parentType.put("children", new ArrayList<>());
                for (Map<String, Object> childType : dbTypes) {
                    Map<String, Object> child = new HashMap<>();
                    child.put("value", childType.get("value"));
                    child.put("label", childType.get("label"));
                    if (childType.get("parentTypeId").toString().equals(parentType.get("value").toString())) {
                        ((ArrayList) parentType.get("children")).add(child);
                    }
                }
                result.add(parentType);
            }
        }
        return result;
    }

}
