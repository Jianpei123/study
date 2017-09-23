package com.arcsoft.study.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.arcsoft.study.domain.Question;

@Mapper
public interface QuestionMapper {
    /**
     * private Long questionId; private Long courseId; private Long videoId;
     * private String questionContent; private Date questionTime; private Long
     * questionAsker; private Integer questionLike;//可空 private Integer
     * question_type; private Integer read;
     */
    @Insert("insert into question(course_id,video_id,question_content,question_time,question_asker,question_like,question_type,read) values(#{courseId},#{videoId},#{questionContent},#{questionTime},#{questionAsker},0,#{quesitionType},0)")
    int addQuestion(Question question);

    @Delete("delete from question where questionId=#{id}")
    int deleteQuestion(Long id);

    /**
     * @描述 根据视频id 和 评论区的类别 来加载评论。
     * @param videoId,
     *            questionType(0为综合讨论区，1为老师答疑区)
     * @return List<Map>
     */
    // @Select("select * from question q,employee e,reply r,video v where
    // q.video_id=#{id} and q.video_id=v.video_id and q.question_asker=e.id and
    // r.question_id=q.question_id and r.answer_id=e.id and r.asker_id=e.id ")

    @Select("select * from question where video_id=#{id} and question_type=#{questionType} order by question_like desc")
    @Results({ @Result(id = true, property = "questionId", column = "question_id"),
            @Result(property = "videoId", column = "video_id"),
            @Result(property = "questionContent", column = "question_content"),
            @Result(property = "questionTime", column = "question_time"),
            @Result(property = "questionAsker", column = "question_asker", one = @One(select = "com.arcsoft.mapper.EmployeeMapper.findEmployeeById")),
            @Result(property = "questionLike", column = "question_like"),
            @Result(property = "replyList", column = "question_id", many = @Many(select = "com.arcsoft.mapper.ReplyMapper.getReplyByQuestionId")) })
    List<Map> selectQuestionByVideoId(Long id, int questionType);

    /**
     * @描述 根据课程id 和 评论区的类别加载评论
     * @param 课程id
     *            和 评论的类别 （0为综合讨论区，1为老师答疑区）
     * @return List<Map>
     */
    @Select("select * from question where course_id=#{id} and question_type=#{questionType} order by question_like desc")
    @Results({ @Result(id = true, property = "questionId", column = "question_id"),
            @Result(property = "videoId", column = "video_id"),
            @Result(property = "questionContent", column = "question_content"),
            @Result(property = "questionTime", column = "question_time"),
            @Result(property = "questionAsker", column = "question_asker", one = @One(select = "com.arcsoft.mapper.EmployeeMapper.findEmployeeById")),
            @Result(property = "questionLike", column = "question_like"),
            @Result(property = "replyList", column = "question_id", many = @Many(select = "com.arcsoft.mapper.ReplyMapper.getReplyByQuestionId")) })
    List<Map> selectQuestionByCourseId(Long id, int questionType);

    /**
     * @描述 加赞的数量
     * @return
     */
    @Update("update question set question_like=question+1 where question_id=#{id}")
    int updateQuestion(Long id);

    /**
     * @描述 修改未读
     * @return
     */
    @Update("update question set is_read =1 where question_id=#{id}")
    int updateRead(Long id);

    @Select("select 1 question,q.course_id courseId,c.name courseName,e.id askerId,e.name askerName,q.question_id questionId,question_content content,question_time time from question q left join course c on q.course_id=c.id left join employee e on q.question_asker=e.id where is_read=0 and q.course_id in (select c.id id from course c left join employee e on c.lecturer_id=e.id where e.id=#{lecturerId})")
    List<Map> getQuestionByLecturer(@Param("lecturerId") Long lecturerId);

}
