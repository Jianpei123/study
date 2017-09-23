package com.arcsoft.study.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.arcsoft.study.domain.Type;

@Mapper
public interface TypeMapper {
    @Select("select * from type where parent_type_id in (0,1) order by parent_type_id")
    List<Type> getPrimaryType();

    @Select("select t1.id,t1.name,t2.name as parentType,t2.id as parentTypeId from type t1 left join type t2 on t1.parent_type_id=t2.id where t1.id!=1 order by t1.parent_type_id")
    List<Map> getAllType();

    @Select("select t1.id as value,t1.name as label,t2.name as parentType,t2.id as parentTypeId from type t1 left join type t2 on t1.parent_type_id=t2.id where t1.id!=1 order by t1.parent_type_id")
    List<Map<String, Object>> getCascadedType();

    @Insert("insert into type values(0,#{name},#{parentTypeId},#{creatorId})")
    int addType(Type type);

    @Update("update type set name=#{name},parent_type_id=#{parentTypeId} where id=#{id}")
    int updateType(Type type);

    @Delete("delete from type where id=#{id}")
    int deleteType(Type type);
}
