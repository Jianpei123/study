package com.arcsoft.study.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.arcsoft.study.domain.Section;

@Mapper
public interface SectionMapper {
    @Insert("insert into section values(0,#{name},#{type},#{url},#{creatorId},#{sequenceNumber},#{length},#{chapterId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int addSection(Section section);

    @Update("update section set url=#{url} where id=#{sectionId}")
    int updateUrl(@Param("sectionId") Long sectionId, @Param("url") String url);

    @Delete("delete from section where id=#{id}")
    int deleteSection(Section section);

    @Update("update section set name=#{name},type=#{type},url=#{url},sequence_number=#{sequenceNumber},chapter_id=#{chapterId} where id=#{id}")
    int updateSection(Section section);
}
