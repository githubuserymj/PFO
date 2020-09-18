package com.offer.mapper;

import com.offer.pojo.PfoComment;
import com.offer.pojo.PfoCommentExample;
import com.offer.pojo.PfoPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface PfoCommentMapper {
    int countByExample(PfoCommentExample example);

    int deleteByExample(PfoCommentExample example);

    int deleteByPrimaryKey(Long commentId);

    /**
     * 帖子评论
     * @param postId
     * @return
     */
    //一级评论
    @Select("select * from pfo_comment where post_id=#{postId} order by deliver_time asc")
    List<PfoComment> queryCommentFirstLevel(Long postId);
    //二级评论
    @Select("select * from pfo_comment where  reply_id  in (select comment_id from pfo_comment where post_id=#{postId}) order by deliver_time asc")
    List<PfoComment> queryCommentSecondLevel(Long postId);
    //三级评论
    @Select("select * from pfo_comment where reply_id in (select comment_id from pfo_comment where  reply_id  in (select comment_id from pfo_comment where post_id=#{postId})) order by deliver_time asc")
    List<PfoComment> queryCommentThirdLevel(Long postId);

    /**
     * 试题评论
     * @param questionId
     * @return
     */
    //一级评论
    @Select("select * from pfo_comment where question_id=#{questionId} order by deliver_time asc")
    List<PfoComment> queryQsCommentFirstLevel(Long questionId);
    //二级评论
    @Select("select * from pfo_comment where  reply_id  in (select comment_id from pfo_comment where question_id=#{questionId}) order by deliver_time asc")
    List<PfoComment> queryQsCommentSecondLevel(Long questionId);
    //三级评论
    @Select("select * from pfo_comment where reply_id in (select comment_id from pfo_comment where  reply_id  in (select comment_id from pfo_comment where question_id=#{questionId})) order by deliver_time asc")
    List<PfoComment> queryQsCommentThirdLevel(Long questionId);

    //根据回帖ID查出所回复帖子的userId
    @Select("select * from pfo_comment where comment_id=#{replyId}")
    List<PfoComment> queryUserId(Long replyId);

    //根据用户ID查询所有他所有发布的评论
    @Select("select * from pfo_comment where user_id=#{userId}")
    List<PfoComment> queryAllCommentsByUserId(Long userId);

    //根据id删除数据
    int deleteById(Long commentId);
    //根据id修改评论
    int updateById(PfoComment pfoComment);
    //添加评论
    int addComment(PfoComment pfoComment);

    int insert(PfoComment record);

    int insertSelective(PfoComment record);

    List<PfoComment> selectByExampleWithBLOBs(PfoCommentExample example);
    //查询全部评论
    List<PfoComment> selectByExample(PfoCommentExample example);

    PfoComment selectByPrimaryKey(Long commentId);

    int updateByExampleSelective(@Param("record") PfoComment record, @Param("example") PfoCommentExample example);

    int updateByExampleWithBLOBs(@Param("record") PfoComment record, @Param("example") PfoCommentExample example);

    int updateByExample(@Param("record") PfoComment record, @Param("example") PfoCommentExample example);

    int updateByPrimaryKeySelective(PfoComment record);

    int updateByPrimaryKeyWithBLOBs(PfoComment record);

    int updateByPrimaryKey(PfoComment record);
}