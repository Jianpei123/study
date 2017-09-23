package com.arcsoft.study.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.arcsoft.study.domain.Course;

@Mapper
public interface CourseMapper {
    /**
     * @描述 根据课程的type进行查询。
     * @Param 课程类型 typeId 员工id employeeId
     * @return List<Map>
     */
    @Select("select  c.id ,c.name,c.description,c.image,c.create_time from course_employee ce ,course c where  ce.employee_id=#{employeeId} and ce.course_id=c.id and c.type_id=#{typeId} order by c.create_time limit 3")
    List<Map> findCourseByType(@Param("employeeId") Long employeeId, @Param("typeId") Long typeId);

    /**
     * @描述 根据课程的管理者进行查询。
     * @Param 员工id employeeId 管理者id lecturer_id
     * @return List<Map>
     */
    @Select("select  c.id ,c.name,c.description,c.image,c.create_time from course_employee ce ,course c where  ce.employee_id=#{employeeId} and ce.course_id=c.id and c.lecturer_id=#{lecturerId} order by c.create_time limit 2")
    List<Map> findCourseByLecturer(@Param("employeeId") Long employeeId, @Param("lecturerId") Long lecturer_id);

    /**
     * @描述 实现收藏课程功能
     * @param employeeId
     *            员工id courseId 课程id
     * @return int
     */
    @Update("update course_employee set is_collected=1 where employee_id=#{employeeId} and course_id=#{courseId}")
    int collectCourse(Long employeeId, Long courseId);

    /**
     * @描述 查询员工收藏的课程
     * @param 员工id
     * @return List<Course>
     */
    @Select("select c.id,c.name,c.image,c.description,c.lecturer_id,c.create_time from course c, course_employee ce where ce.id=c.id and ce.employee_id=#{employeeId}")
    List<Course> selectCollectedCourse(Long employeeId);

    /**
     * @描述 取消收藏课程
     * @param employeeId
     *            员工id courseId 课程id
     * @return int
     */
    @Update("update course_employee set is_collected=0 where employee_id=#{employeeId} and course_id=#{courseId}")
    int cancelCollectCourse(Long employeeId, Long courseId);

    @Select("select * from course")
    List<Course> findAllCourse();

    @Insert("insert into course values(0,#{name},#{description},#{label},#{typeId},#{lecturerId},#{createTime},0)")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int addCourse(Course course);

    @Delete("delete from course where id=#{id}")
    int deleteCourse(Course course);

    @Update("update course set name=#{name},description=#{description},type_id=#{typeId} where id=#{id}")
    int updateCourse(Course course);

    @Select("select c.id id,c.name name,description,t.name typeName,t.id typeId,t.parent_type_id parentTypeId from course c left join type t on c.type_id=t.id where lecturer_id=#{lecturerId}")
    @Results({ @Result(property = "id", column = "id", id = true), @Result(property = "name", column = "name"),
            @Result(property = "description", column = "description"),
            @Result(property = "typeName", column = "typeName"), @Result(property = "typeId", column = "typeId"),
            @Result(property = "parentTypeId", column = "parentTypeId"),
            @Result(property = "groups", column = "id", many = @Many(select = "com.arcsoft.mapper.CourseMapper.getCourseGroup")),
            @Result(property = "members", column = "id", many = @Many(select = "com.arcsoft.mapper.CourseMapper.getCourseEmployee")) })
    List<Map> findCourseByTeacher(Long lecturerId);

    @Select("select group_id as id from course_group where course_id=#{courseId}")
    List<Long> getCourseGroup(@Param("courseId") Long courseId);

    @Select("select employee_id as id from course_employee where course_id=#{courseId}")
    List<Long> getCourseEmployee(@Param("courseId") Long courseId);

    @InsertProvider(type = BatchProvider.class, method = "addCourseEmployees")
    int addCourseEmployees(@Param("courseId") Long courseId, @Param("members") List<Long> members);

    @InsertProvider(type = BatchProvider.class, method = "deleteCourseEmployees")
    int deleteCourseEmployees(@Param("courseId") Long courseId, @Param("members") List<Long> members);

    @InsertProvider(type = BatchProvider.class, method = "addCourseGroups")
    int addCourseGroups(@Param("courseId") Long courseId, @Param("groups") List<Long> groups);

    @InsertProvider(type = BatchProvider.class, method = "deleteCourseGroups")
    int deleteCourseGroups(@Param("courseId") Long courseId, @Param("groups") List<Long> groups);

    List<Map> getRecommendedCourse(Long employeeId);

    @Select("select * from course c left join course_employee ce on c.id=ce.course_id where ce.employee_id=#{employeeId} order by create_time limit 0,5")
    List<Map> getRecentCourse(@Param("employeeId") Long employeeId);
}
