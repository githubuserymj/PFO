package com.offer.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.offer.dta.PageInfoResult;
import com.offer.mapper.PfoPostMapper;
import com.offer.mapper.PfoPostTagMapper;
import com.offer.pojo.*;
import com.offer.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PostServiceImpl implements PostService {
    @Autowired
    PfoPostMapper pfoPostMapper;
    @Autowired
    PfoPostTagMapper pfoPostTagMapper;

    /**
     * 查询全部帖子
     * @return
     */
    @Override
    public List<PfoPost> queryAllPosts() {
        return pfoPostMapper.selectByExample(null);
    }

    /**
     * 根据ID删除帖子
     * @param postId
     * @return
     */
    @Override
    public int deleteById(Long postId) {
        return pfoPostMapper.deleteById(postId);
    }

    /**
     * 根据ID修改帖子
     * @param pfoPost
     * @return
     */
    @Override
    public int updateById(PfoPost pfoPost) {
        return pfoPostMapper.updateById(pfoPost);
    }

    /**
     * 根据ID将帖子浏览数+1
     * @param postId
     * @return
     */
    @Override
    public int updateViewCountByPostId(Long postId) {
        return pfoPostMapper.updateViewCountByPostId(postId);
    }

    /**
     * 将点赞数+1
     * @param postId
     * @return
     */
    @Override
    public int updateFavorCountByPostIdUp(Long postId) {
        return pfoPostMapper.updateFavorCountByPostIdUp(postId);
    }

    /**
     * 将点赞数-1
     * @param postId
     * @return
     */
    @Override
    public int updateFavorCountByPostIdDown(Long postId) {
        return pfoPostMapper.updateFavorCountByPostIdDown(postId);
    }

    /**
     * 根据ID将帖子评论数+1
     * @param postId
     * @return
     */
    @Override
    public int updateCommentCountByPostId(Long postId) {
        return pfoPostMapper.updateCommentCountByPostId(postId);
    }


    /**
     * 根据试题ID将帖子浏览数+1
     * @param questionId
     * @return
     */
    @Override
    public int updateViewCountByQuestionId(Long questionId) {
        return pfoPostMapper.updateViewCountByPostId(questionId);
    }

    /**
     * 根据试题ID将帖子评论数+1
     * @param questionId
     * @return
     */
    @Override
    public int updateCommentCountByQuestionId(Long questionId) {
        return pfoPostMapper.updateCommentCountByPostId(questionId);
    }
    /**
     * 添加帖子
     * @param pfoPost
     * @return
     */
    @Override
    public int addPost(PfoPost pfoPost) {
        return pfoPostMapper.addPost(pfoPost);
    }

    /**
     * 添加帖子与标签的关联记录
     * @param pfoPostTag
     * @return
     */
    @Override
    public int addPostTags(PfoPostTag pfoPostTag) {
        return pfoPostTagMapper.insert(pfoPostTag);
    }

    /**
     * 根据帖子id查询它对应的所有标签
     * @param postId
     * @return
     */
    @Override
    public List<PfoTag> queryAllTagsByPostId(Long postId) {
        return pfoPostMapper.queryAllTagsByPostId(postId);
    }

    /**
     * 根据帖子id删除帖子所有标签
     * @param postId
     * @return
     */
    @Override
    public int deletePostTagsByPostId(Long postId) {
        return pfoPostTagMapper.deletePostTagsByPostId(postId);
    }

    /**
     * 根据标签id查询它对应的所有标签
     * @param tagId
     * @return
     */
    @Override
    public List<PfoPost> queryAllPostsByTagId(Long tagId) {
        return pfoPostMapper.queryAllPostsByTagId(tagId);
    }
    /**
     * 查询所有帖子的所有标签
     * @return
     */
    @Override
    public List<PfoTag> queryAllTagsWithPosts() {
        return pfoPostMapper.queryAllTagsWithPosts();
    }

    /**
     * 根据用户ID查询他发布的所有帖子
     * @param userId
     * @return
     */
    @Override
    public List<PfoPost> queryAllPostsByUserId(Long userId) {
        return pfoPostMapper.queryAllPostsByUserId(userId);
    }

    /**
     * 根据主题ID查询所有帖子
     * @param topicId
     * @return
     */
    @Override
    public List<PfoPost> queryAllPostsByTopicId(Integer topicId) {
        return pfoPostMapper.queryAllPostsByTopicId(topicId);
    }

    @Override
    public PageInfoResult<PfoPost> queryAllPosts(PageInfoResult pageParameter) {
        // 设置分页参数,获取当前页，每页数据条数
        PageHelper.startPage(pageParameter.getCurrPage(), pageParameter.getPageSize());
        // 查询所有
        //System.out.println("fenye");
        List<PfoPost> postList = pfoPostMapper.selectByExample(null);
        // PageInfo 包装查询结果  selelct * from pfo_post where ... limit 1,6
        PageInfo<PfoPost> pageInfo = new PageInfo<PfoPost>(postList);
        //System.out.println(pageInfo);

        // 查询的当前页数据信息
        List<PfoPost> empList = pageInfo.getList();
        // 获取查询的总条数  select count(0) from pfo_post
        pageParameter.setTotalSize(pageInfo.getTotal());
        pageParameter.setDataList(empList);

        return pageParameter;
    }

    /**
     * 根据需要查询字段，自定义查询字段，多表
     * @param example
     * @return
     */
    @Override
    public List<PfoPostRequired> queryByRequired(PfoPostExample example) {
        return pfoPostMapper.queryByRequired(example);
    }

    /**
     * 根据需要查询字段，自定义查询字段(排序)，多表
     * @param example
     * @param order
     * @return
     */
    @Override
    public List<PfoPostRequired> queryByRequiredOrder(PfoPostExample example, Integer order) {
        return pfoPostMapper.queryByRequiredOrder(example,order);
    }


    /**
     * 自定义字段的分页
     * @param pageParameter
     * @return
     */
    @Override
    public PageInfoResult<PfoPostRequired> queryAllPostsRequired(PageInfoResult pageParameter) {
        // 设置分页参数,获取当前页，每页数据条数
        PageHelper.startPage(pageParameter.getCurrPage(), pageParameter.getPageSize());
        // 查询所有
        //System.out.println("fenye");
        List<PfoPostRequired> postList = pfoPostMapper.queryByRequired(null);
        // PageInfo 包装查询结果  selelct * from pfo_post where ... limit 1,6
        PageInfo<PfoPostRequired> pageInfo = new PageInfo<PfoPostRequired>(postList);
        //System.out.println(pageInfo);

        // 查询的当前页数据信息
        List<PfoPostRequired> empList = pageInfo.getList();
        // 获取查询的总条数  select count(0) from pfo_post
        pageParameter.setTotalSize(pageInfo.getTotal());
        pageParameter.setDataList(empList);

        return pageParameter;
    }

    /**
     * 根据帖子id查询帖子信息
     * @param postId
     * @return
     */
    @Override
    public List<PfoPost> queryPostByPostId(Long postId) {
        return pfoPostMapper.queryPostByPostId(postId);
    }

    /**
     * 根据postId查询需要字段
     * @param postId
     * @return
     */
    @Override
    public List<PfoPostRequired> queryRequiredByPostId(Long postId) {
        return pfoPostMapper.queryRequiredByPostId(postId);
    }

    @Override
    public PageInfoResult<PfoPostRequired> queryAllPostsRequiredOrder(PageInfoResult pageParameter, Integer order) {
        // 设置分页参数,获取当前页，每页数据条数
        PageHelper.startPage(pageParameter.getCurrPage(), pageParameter.getPageSize());
        // 查询所有
        //System.out.println("fenye");
        List<PfoPostRequired> postList = pfoPostMapper.queryByRequiredOrder(null,order);
        // PageInfo 包装查询结果  selelct * from pfo_post where ... limit 1,6
        PageInfo<PfoPostRequired> pageInfo = new PageInfo<PfoPostRequired>(postList);
       // System.out.println(pageInfo);

        // 查询的当前页数据信息
        List<PfoPostRequired> empList = pageInfo.getList();
        // 获取查询的总条数  select count(0) from pfo_post
        pageParameter.setTotalSize(pageInfo.getTotal());
        pageParameter.setDataList(empList);

        return pageParameter;
    }

    /**
     * 根据标签id显示需要字段
     * @param tagId
     * @return
     */
    @Override
    public List<PfoPostRequired> queryRequiredByTagId(Long tagId) {
        return pfoPostMapper.queryRequiredByTagId(tagId);
    }

    /**
     * 根据主题id显示需要字段
     * @param topicId
     * @return
     */
    @Override
    public List<PfoPostRequired> queryRequiredByTopicId(Integer topicId) {
        return pfoPostMapper.queryRequiredByTopicId(topicId);
    }

    /**
     * 根据标签id显示需要字段分页
     * @param pageParameter
     * @param tagId
     * @return
     */
    @Override
    public PageInfoResult<PfoPostRequired> queryRequiredByTagIdOrder(PageInfoResult pageParameter, Long tagId) {
        // 设置分页参数,获取当前页，每页数据条数
        PageHelper.startPage(pageParameter.getCurrPage(), pageParameter.getPageSize());
        // 查询所有
        //System.out.println("fenye");
        List<PfoPostRequired> postList = pfoPostMapper.queryRequiredByTagId(tagId);
        // PageInfo 包装查询结果  selelct * from pfo_post where ... limit 1,6
        PageInfo<PfoPostRequired> pageInfo = new PageInfo<PfoPostRequired>(postList);
        //System.out.println(pageInfo);

        // 查询的当前页数据信息
        List<PfoPostRequired> empList = pageInfo.getList();
        // 获取查询的总条数  select count(0) from pfo_post
        pageParameter.setTotalSize(pageInfo.getTotal());
        pageParameter.setDataList(empList);

        return pageParameter;
    }

    /**
     * 根据主题id显示需要字段分页
     * @param pageParameter
     * @param topicId
     * @return
     */
    @Override
    public PageInfoResult<PfoPostRequired> queryRequiredByTopicIdOrder(PageInfoResult pageParameter, Integer topicId) {
        // 设置分页参数,获取当前页，每页数据条数
        PageHelper.startPage(pageParameter.getCurrPage(), pageParameter.getPageSize());
        // 查询所有
        //System.out.println("fenye");
        List<PfoPostRequired> postList = pfoPostMapper.queryRequiredByTopicId(topicId);
        PageInfo<PfoPostRequired> pageInfo = new PageInfo<PfoPostRequired>(postList);
        //System.out.println(pageInfo);

        // 查询的当前页数据信息
        List<PfoPostRequired> empList = pageInfo.getList();
        // 获取查询的总条数  select count(0) from pfo_post
        pageParameter.setTotalSize(pageInfo.getTotal());
        pageParameter.setDataList(empList);

        return pageParameter;
    }

    /**
     * 根据用户输入字符串进行查询需要展示的字段
     * @param scanner
     * @return
     */
    @Override
    public List<PfoPostRequired> queryRequiredByScanner(String scanner) {
        return pfoPostMapper.queryRequiredByScanner(scanner);
    }

    /**
     * 根据用户输入字符串进行查询需要展示的字段分页
     * @param pageParameter
     * @param scanner
     * @return
     */
    @Override
    public PageInfoResult<PfoPostRequired> queryRequiredByScannerOrder(PageInfoResult pageParameter, String scanner) {
        // 设置分页参数,获取当前页，每页数据条数
        PageHelper.startPage(pageParameter.getCurrPage(), pageParameter.getPageSize());
        List<PfoPostRequired> postList = pfoPostMapper.queryRequiredByScanner(scanner);
        PageInfo<PfoPostRequired> pageInfo = new PageInfo<PfoPostRequired>(postList);

        // 查询的当前页数据信息
        List<PfoPostRequired> empList = pageInfo.getList();
        // 获取查询的总条数  select count(0) from pfo_post
        pageParameter.setTotalSize(pageInfo.getTotal());
        pageParameter.setDataList(empList);

        return pageParameter;
    }

}
