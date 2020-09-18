package com.offer.service.impl;

import com.offer.mapper.PfoCommentMapper;
import com.offer.pojo.PfoComment;
import com.offer.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    PfoCommentMapper pfoCommentMapper;
    @Override
    /**
     * 查询全部评论
     */
    public List<PfoComment> queryAllComments() {
        return pfoCommentMapper.selectByExample(null);
    }

    /**
     * 根据ID删除评论
     * @param commentId 评论id
     * @return 返回受影响行数
     */
    @Override
    public int deleteById(Long commentId) {
        return pfoCommentMapper.deleteById(commentId);
    }

    /**
     * 修改评论根据id
     * @param pfoComment
     * @return
     */
    @Override
    public int updateById(PfoComment pfoComment) {
        return pfoCommentMapper.updateById(pfoComment);
    }

    /**
     * 添加评论
     * @param pfoComment
     * @return 受影响行数
     */
    @Override
    public int addComment(PfoComment pfoComment) {
        return pfoCommentMapper.addComment(pfoComment);
    }

    /**
     * 查询一级评论
     * @param postId
     * @return
     */
    @Override
    public List<PfoComment> queryCommentFirstLevel(Long postId) {
        return pfoCommentMapper.queryCommentFirstLevel(postId);
    }

    /**
     * 查询二级评论
     * @param postId
     * @return
     */
    @Override
    public List<PfoComment> queryCommentSecondLevel(Long postId) {
        return pfoCommentMapper.queryCommentSecondLevel(postId);
    }

    /**
     * 查询三级评论
     * @param postId
     * @return
     */
    @Override
    public List<PfoComment> queryCommentThirdLevel(Long postId) {
        return pfoCommentMapper.queryCommentThirdLevel(postId);
    }
    /**
     * 查询试题一级评论
     * @param questionId
     * @return
     */
    @Override
    public List<PfoComment> queryQsCommentFirstLevel(Long questionId) {
        return pfoCommentMapper.queryQsCommentFirstLevel(questionId);
    }
    /**
     * 查询试题二级评论
     * @param questionId
     * @return
     */
    @Override
    public List<PfoComment> queryQsCommentSecondLevel(Long questionId) {
        return pfoCommentMapper.queryQsCommentSecondLevel(questionId);
    }
    /**
     * 查询试题三级评论
     * @param questionId
     * @return
     */
    @Override
    public List<PfoComment> queryQsCommentThirdLevel(Long questionId) {
        return pfoCommentMapper.queryQsCommentThirdLevel(questionId);
    }

    //根据回帖ID查出所回复帖子的userId
    @Override
    public List<PfoComment> queryUserId(Long replyId) {
        return pfoCommentMapper.queryUserId(replyId);
    }

    //根据用户ID查询所有他所有发布的评论
    @Override
    public List<PfoComment> queryAllCommentsByUserId(Long userId) {
        return pfoCommentMapper.queryAllCommentsByUserId(userId);
    }


}
