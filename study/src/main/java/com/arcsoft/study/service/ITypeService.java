package com.arcsoft.study.service;

import java.util.List;
import java.util.Map;

import com.arcsoft.study.domain.Type;

public interface ITypeService {
    List<Type> getPrimaryType();

    List<Map> getAllType();

    List<Map<String, Object>> getCascadedType();

    int addType(Type type);

    int updateType(Type type);

    int deleteType(Type type);
}
