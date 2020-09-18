package com.offer.service;

import com.offer.dta.PageInfoResult;
import com.offer.pojo.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PostService {

    /**
     * 查询全部帖子
     * @return
     */
    List<PfoPost> queryAllPosts();
    /**
     * 根据id删除帖子
     */
    int deleteById(Long postId);
    /**
     * 根据id修改帖子
     */
    int updateById(PfoPost pfoPost);

    //根据帖子id将浏览量+1
    int updateViewCountByPostId(Long postId);

    //根据帖子id将点赞数+1
    int updateFavorCountByPostIdUp(Long postId);

    //根据帖子id将点赞数-1
    int updateFavorCountByPostIdDown(Long postId);

    //根据帖子id将评论数+1
    int updateCommentCountByPostId(Long postId);

    //根据试题id将浏览量+1
    int updateViewCountByQuestionId(Long questionId);

    //根据试题id将评论数+1
    int updateCommentCountByQuestionId(Long questionId);

    //根据postId查询需要字段
    List<PfoPostRequired> queryRequiredByPostId(Long postId);

    /**
     * 添加帖子
     */
    int addPost(PfoPost pfoPost);

    /**
     * 添加帖子与标签的关联记录
     * @param pfoPostTag
     * @return
     */
    int addPostTags(PfoPostTag pfoPostTag);

    /**
     * 根据帖子ID查询它对应的所有标签
     */
    List<PfoTag> queryAllTagsByPostId(Long postId);

    /**
     * 根据帖子id删除帖子所有标签
     * @param postId
     * @return
     */
    int deletePostTagsByPostId(Long postId);
    /**
     * 根据标签ID显示所有帖子信息
     */
    List<PfoPost> queryAllPostsByTagId(Long tagId);

    /**
     * 查询所有帖子的所有标签(去重)
     */
    List<PfoTag> queryAllTagsWithPosts();

    /**
     * 根据用户ID查询他发布的所有帖子
     */
    List<PfoPost> queryAllPostsByUserId(Long userId);
    /**
     * 根据主题ID查询所有帖子
     */
    List<PfoPost> queryAllPostsByTopicId(Integer topicId);

    PageInfoResult<PfoPost> queryAllPosts(PageInfoResult pageParameter);

    /**
     * 根据需要字段自定义查询结果集
     * @param example
     * @return
     */
    List<PfoPostRequired> queryByRequired(PfoPostExample example);

    /**
     * 根据需要字段自定义查询结果集(排序)
     * @param example
     * @param order
     * @return
     */
    List<PfoPostRequired> queryByRequiredOrder(PfoPostExample example,Integer order);

    /**
     * 自定义字段的分页
     * @param pageParameter
     * @return
     */
    PageInfoResult<PfoPostRequired> queryAllPostsRequired(PageInfoResult pageParameter);

    /**
     * 根据id查询帖子信息
     * @param postId
     * @return
     */
    List<PfoPost> queryPostByPostId(Long postId);


    /**
     * 自定义字段的分页(根据最新发布,最多回复,最多浏览排序)
     * @param pageParameter
     * @return
     */
    PageInfoResult<PfoPostRequired> queryAllPostsRequiredOrder(PageInfoResult pageParameter,Integer order);


    //根据标签id显示需要字段
    List<PfoPostRequired> queryRequiredByTagId(Long tagId);

    //根据主题id显示需要字段
    List<PfoPostRequired> queryRequiredByTopicId(Integer topicId);

    /**
     * 根据标签id显示需要字段分页
     * @param pageParameter
     * @param tagId
     * @return
     */
    PageInfoResult<PfoPostRequired> queryRequiredByTagIdOrder(PageInfoResult pageParameter,Long tagId);

    /**
     * 根据主题id显示需要字段分页
     * @param pageParameter
     * @param topicId
     * @return
     */
    PageInfoResult<PfoPostRequired> queryRequiredByTopicIdOrder(PageInfoResult pageParameter,Integer topicId);

    //根据用户输入字符进行查询需要字段
    List<PfoPostRequired> queryRequiredByScanner(String scanner);

    /**
     * 根据用户输入字符进行查询需要字段分页
     * @param pageParameter
     * @param scanner
     * @return
     */
    PageInfoResult<PfoPostRequired> queryRequiredByScannerOrder(PageInfoResult pageParameter,String scanner);








}
