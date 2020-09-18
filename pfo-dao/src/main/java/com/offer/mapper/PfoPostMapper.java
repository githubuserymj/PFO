package com.offer.mapper;

import com.offer.pojo.PfoPost;
import com.offer.pojo.PfoPostExample;
import java.util.List;

import com.offer.pojo.PfoPostRequired;
import com.offer.pojo.PfoTag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface PfoPostMapper {
    int countByExample(PfoPostExample example);

    int deleteByExample(PfoPostExample example);

    int deleteByPrimaryKey(Long postId);

    int insert(PfoPost record);

    int insertSelective(PfoPost record);

    List<PfoPost> selectByExampleWithBLOBs(PfoPostExample example);

    //根据帖子ID查询帖子信息
    List<PfoPost> queryPostByPostId(Long postId);

    //根据需要查询相应字段(排序)
    List<PfoPostRequired> queryByRequiredOrder(PfoPostExample example,@Param("order") Integer order);

    //查询所有帖子信息
    List<PfoPost> selectByExample(PfoPostExample example);

    //根据需要查询相应字段()
    List<PfoPostRequired> queryByRequired(PfoPostExample example);

    //根据标签id显示需要字段
    List<PfoPostRequired> queryRequiredByTagId(Long tagId);

    //根据主题id显示需要字段
    List<PfoPostRequired> queryRequiredByTopicId(Integer topicId);

    //根据用户输入字符进行查询需要字段
    List<PfoPostRequired> queryRequiredByScanner(String scanner);

    //添加帖子
    int addPost(PfoPost pfoPost);

    //根据id修改帖子
    int updateById(PfoPost pfoPost);

    //根据帖子id将浏览量+1
    @Update("update pfo_post set view_count = view_count+1 where post_id=#{postId}")
    int updateViewCountByPostId(Long postId);

    //根据试题id将浏览量+1
    @Update("update pfo_post set view_count = view_count+1 where question_id=#{questionId}")
    int updateViewCountByQuestionId(Long questionId);

    //根据帖子ID评论数+1
    @Update("update pfo_post set comment_count = comment_count+1 where post_id=#{postId}")
    int updateCommentCountByPostId(Long postId);

    //根据试题ID评论数+1
    @Update("update pfo_post set comment_count = comment_count+1 where question_id=#{questionId}")
    int updateCommentCountByQuestionId(Long questionId);

    //根据帖子id将浏览量+1
    @Update("update pfo_post set favor_count = favor_count+1 where post_id=#{postId}")
    int updateFavorCountByPostIdUp(Long postId);

    //根据帖子id将浏览量-1
    @Update("update pfo_post set favor_count = favor_count-1 where post_id=#{postId}")
    int updateFavorCountByPostIdDown(Long postId);

    //根据postId查询需要字段
//    @Select("select post_title, user_name,user_photo,deliver_time,topic_name,view_count,comment_count,favor_count,p.post_id,u.user_id,p.topic_id from pfo_user u,pfo_post p,pfo_topic t where u.user_id=p.user_id and p.topic_id = t.topic_id and p.post_id=#{postId} order by post_id")
    List<PfoPostRequired> queryRequiredByPostId(Long postId);


    //根据id删除帖子
    int deleteById(Long postId);

    //根据帖子id查询它对应的所有标签
    List<PfoTag> queryAllTagsByPostId(Long postId);

    //根据标签id查它对应的所有帖子
    List<PfoPost> queryAllPostsByTagId(Long tagId);

    //查询所有帖子的所有标签
    List<PfoTag> queryAllTagsWithPosts();

    //根据用户ID查询他发布的所有帖子
    List<PfoPost> queryAllPostsByUserId(Long userId);

    //根据主题ID查询所有帖子
    List<PfoPost> queryAllPostsByTopicId(Integer topicId);

    PfoPost selectByPrimaryKey(Long postId);

    int updateByExampleSelective(@Param("record") PfoPost record, @Param("example") PfoPostExample example);

    int updateByExampleWithBLOBs(@Param("record") PfoPost record, @Param("example") PfoPostExample example);

    int updateByExample(@Param("record") PfoPost record, @Param("example") PfoPostExample example);

    int updateByPrimaryKeySelective(PfoPost record);

    int updateByPrimaryKeyWithBLOBs(PfoPost record);

    int updateByPrimaryKey(PfoPost record);
}