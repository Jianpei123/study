package com.arcsoft.study.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
/**
 *   private Long replyId;
     private Question questionId;
     private Employee answerId;
     private Employee askerId;
     private String replyContent;
     private Date replyTime;

 */
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.arcsoft.study.domain.Reply;

@Mapper
public interface ReplyMapper {

    /**
     * private Long replyId;
     * 
     * private Long questionId;
     * 
     * private Long answerId;
     * 
     * private Long askerId;
     * 
     * private String replyContent;
     * 
     * private Date replyTime;
     * 
     * private Integer read;
     */
    @Select("select * from reply where question_id=#{id} order by reply_time desc")
    @Results({ @Result(property = "replyId", column = "reply_id"),
            @Result(property = "answer", column = "answer_id", one = @One(select = "com.arcsoft.mapper.EmployeeMapper.findEmployeeById")),
            @Result(property = "asker", column = "asker_id", one = @One(select = "com.arcsoft.mapper.EmployeeMapper.findEmployeeById")),
            @Result(property = "replyContent", column = "reply_content"),
            @Result(property = "replyTime", column = "reply_time"), })

    List<Map> getReplyByQuestionId(Long id);

    /**
     * @描述 查询出某位员工所有的未读回复：
     * @param targetId
     *            某位员工的id
     * @return
     */
    @Select("select  r.reply_id , e.name, r.reply_content,r.reply_time from reply r ,employee e where asker_id=#{targetId} and is_read=0")
    List<Map> getReplyNotRead(Long targetId);

    @Insert("insert into reply (question_id,answer_id,asker_id,reply_content,reply_time,is_read,course_id) values(#{questionId},#{answerId},#{askerId},#{replyContent},#{replyTime},0,#{courseId})")
    int addReply(Reply reply);

    @Delete("delete from reply where reply_id=#{id}")
    int deleteReply(Long id);

    @Update("update reply set read=1 where reply_id=#{id}")
    int updateRead(Long id);

    @Select("select 1 reply,c.id courseId,c.name courseName,e.id askerId,r.answer_id answerId,r.question_id questionId,e.name askerName,r.reply_id replyId,reply_content content,reply_time time from reply r left join course c on r.course_id=c.id left join employee e on e.id=c.lecturer_id where r.asker_id=#{lecturerId} and r.is_read=0")
    List<Map> getReplyByLecturer(@Param("lecturerId") Long lecturerId);
}
