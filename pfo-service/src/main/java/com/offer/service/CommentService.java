package com.offer.service;

import com.offer.pojo.PfoComment;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface CommentService {
    /**
     * 查询全部评论
     * @return
     */
    List<PfoComment> queryAllComments();
    /**
     * 根据id删除评论
     */
    int deleteById(Long commentId);
    /**
     * 根据id修改评论
     */
    int updateById(PfoComment pfoComment);
    /**
     * 添加评论
     */
    int addComment(PfoComment pfoComment);

    /**
     * 查询一级评论
     * @param postId
     * @return
     */
    List<PfoComment> queryCommentFirstLevel(Long postId);

    /**
     * 查询二级评论
     * @param postId
     * @return
     */
    List<PfoComment> queryCommentSecondLevel(Long postId);


    /**
     * 查询三级评论
     * @param postId
     * @return
     */
    List<PfoComment> queryCommentThirdLevel(Long postId);

    /**
     * 查询试题一级评论
     * @param questionId
     * @return
     */
    List<PfoComment> queryQsCommentFirstLevel(Long questionId);

    /**
     * 查询试题二级评论
     * @param questionId
     * @return
     */
    List<PfoComment> queryQsCommentSecondLevel(Long questionId);


    /**
     * 查询试题三级评论
     * @param questionId
     * @return
     */
    List<PfoComment> queryQsCommentThirdLevel(Long questionId);

    //根据回帖ID查出所回复帖子的userId
    List<PfoComment> queryUserId(Long replyId);

    //根据用户ID查询所有他所有发布的评论
    List<PfoComment> queryAllCommentsByUserId(Long userId);
}
