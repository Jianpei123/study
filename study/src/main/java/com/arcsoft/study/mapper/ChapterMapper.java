package com.arcsoft.study.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.arcsoft.study.domain.Chapter;
import com.arcsoft.study.domain.Section;

@Mapper
public interface ChapterMapper {

    @Insert("insert into chapter values(0,#{name},#{sequenceNumber},#{courseId})")
    int addChapter(Chapter chapter);

    @Update("update chapter set name=#{name},sequence_number=#{sequenceNumber} where id=#{id}")
    int updateChapter(Chapter chapter);

    @Delete("delete from chapter where id=#{id}")
    int deleteChapter(Chapter chapter);

    @Select("select * from chapter where course_id=#{courseId} order by sequence_number")
    @Results({ @Result(property = "id", column = "id", id = true), @Result(property = "name", column = "name"),
            @Result(property = "sequenceNumber", column = "sequence_number"),
            @Result(property = "courseId", column = "course_id"),
            @Result(property = "sections", column = "id", many = @Many(select = "com.arcsoft.mapper.ChapterMapper.getSectionsByChapter")) })
    List<Map> getChaptersByCourse(@Param("courseId") Long courseId);

    @Select("select * from section where chapter_id=#{chapterId} order by sequence_number")
    List<Section> getSectionsByChapter(@Param("chapterId") Long chapterId);

}
