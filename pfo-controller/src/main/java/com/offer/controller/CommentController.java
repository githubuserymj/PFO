package com.offer.controller;

import com.offer.pojo.PfoComment;
import com.offer.service.CommentService;
import com.offer.vo.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("comment")
public class CommentController {
    //获取当前日期
    Date date = new Date();
    @Autowired
    CommentService commentService;

    /**
     * 展示所有评论信息
     * @param resultData
     * @return
     */
    @RequestMapping("showAllComments")
    @ResponseBody
    //显示数据
    public ResultData queryAllComments(@RequestAttribute(value = "resultData",required = false) ResultData resultData){
        if(null == resultData) {
            resultData = new ResultData();
        } else {
            // 删除失败
            if(resultData.getCode() == 4) {
                return resultData;
            }
        }
        List<PfoComment> commentList = commentService.queryAllComments();
        if(null == commentList || commentList.size() == 0){
            resultData.setCode(3);
            resultData.setMessage("查无数据");
        }else {
            resultData.setCode(0);
            //System.out.println("发布时间"+commentList.get(0).getDeliverTime());
            resultData.setData(commentList);
        }
        return resultData;
    }

    /**
     * 根据ID删除评论
     * @param model
     * @param commentId
     * @return
     */
    @RequestMapping("deleteById")
    @ResponseBody
    public ResultData deleteById(Model model, Long commentId){
        int affectedRow = commentService.deleteById(commentId);
        ResultData resultData = new ResultData();
        if(affectedRow > 0){
            resultData.setCode(0);
            resultData.setMessage("删除成功");
        } else {
            resultData.setCode(4);
            //resultData.setData(commentService.queryAllComments());
            resultData.setMessage("删除失败");
        }
        model.addAttribute("resultData", resultData);
        //System.out.println(resultData);
        //return "/../hello";
        //return "forward:showAllComments";
        return resultData;
    }


    /**
     * 根据ID修改评论信息
     * @param pfoComment
     * @return
     */
    @RequestMapping("updateById")
    @ResponseBody
    public ResultData updateById(PfoComment pfoComment){
        //获取系统当前时间，插入到对象数组
        date = new Date();
        pfoComment.setDeliverTime(date);
        int affectedRow = commentService.updateById(pfoComment);
        ResultData resultData = new ResultData();
        if (affectedRow > 0) {
            resultData.setCode(0);
            resultData.setMessage("修改成功");
        } else {
            resultData.setCode(5);
            resultData.setMessage("修改失败");
        }
        return resultData;
    }

    /**
     * 添加评论信息
     * @param pfoComment
     * @return
     */
    @RequestMapping("addComment")
    @ResponseBody
    public ResultData addComment(PfoComment pfoComment){
        //获取系统当前时间，插入到对象数组
        date = new Date();
        pfoComment.setDeliverTime(date);
        int affectedRow = commentService.addComment(pfoComment);
        ResultData resultData = new ResultData();
        if (affectedRow > 0){
            resultData.setCode(0);
            resultData.setMessage("添加成功");
        }else{
            resultData.setCode(6);
            resultData.setMessage("添加失败");
        }
        return resultData;
    }


    @RequestMapping("queryCommentFirstLevel")
    @ResponseBody
    public ResultData queryCommentFirstLevel(Long postId){
        List<PfoComment> commentList = commentService.queryCommentFirstLevel(postId);
        ResultData resultData = new ResultData();
        if(null == commentList || commentList.size() == 0){
            resultData.setCode(3);
            resultData.setMessage("查无数据");
        }else {
            resultData.setCode(0);
            //System.out.println("发布时间"+commentList.get(0).getDeliverTime());
            resultData.setData(commentList);
        }
        return resultData;
    }


    @RequestMapping("queryCommentSecondLevel")
    @ResponseBody
    public ResultData queryCommentSecondLevel(Long postId){
        List<PfoComment> commentList = commentService.queryCommentSecondLevel(postId);
        ResultData resultData = new ResultData();
        if(null == commentList || commentList.size() == 0){
            resultData.setCode(3);
            resultData.setMessage("查无数据");
        }else {
            resultData.setCode(0);
            //System.out.println("发布时间"+commentList.get(0).getDeliverTime());
            resultData.setData(commentList);
        }
        return resultData;
    }


    @RequestMapping("queryCommentThirdLevel")
    @ResponseBody
    public ResultData queryCommentThirdLevel(Long postId){
        List<PfoComment> commentList = commentService.queryCommentThirdLevel(postId);
        ResultData resultData = new ResultData();
        if(null == commentList || commentList.size() == 0){
            resultData.setCode(3);
            resultData.setMessage("查无数据");
        }else {
            resultData.setCode(0);
           // System.out.println("发布时间"+commentList.get(0).getDeliverTime());
            resultData.setData(commentList);
        }
        return resultData;
    }


    @RequestMapping("queryQsCommentFirstLevel")
    @ResponseBody
    public ResultData queryQsCommentFirstLevel(Long questionId){
        List<PfoComment> commentList = commentService.queryQsCommentFirstLevel(questionId);
        ResultData resultData = new ResultData();
        if(null == commentList || commentList.size() == 0){
            resultData.setCode(3);
            resultData.setMessage("查无数据");
        }else {
            resultData.setCode(0);
            //System.out.println("发布时间"+commentList.get(0).getDeliverTime());
            resultData.setData(commentList);
        }
        return resultData;
    }


    @RequestMapping("queryQsCommentSecondLevel")
    @ResponseBody
    public ResultData queryQsCommentSecondLevel(Long questionId){
        List<PfoComment> commentList = commentService.queryQsCommentSecondLevel(questionId);
        ResultData resultData = new ResultData();
        if(null == commentList || commentList.size() == 0){
            resultData.setCode(3);
            resultData.setMessage("查无数据");
        }else {
            resultData.setCode(0);
            //System.out.println("发布时间"+commentList.get(0).getDeliverTime());
            resultData.setData(commentList);
        }
        return resultData;
    }


    @RequestMapping("queryQsCommentThirdLevel")
    @ResponseBody
    public ResultData queryQsCommentThirdLevel(Long questionId){
        List<PfoComment> commentList = commentService.queryQsCommentThirdLevel(questionId);
        ResultData resultData = new ResultData();
        if(null == commentList || commentList.size() == 0){
            resultData.setCode(3);
            resultData.setMessage("查无数据");
        }else {
            resultData.setCode(0);
            // System.out.println("发布时间"+commentList.get(0).getDeliverTime());
            resultData.setData(commentList);
        }
        return resultData;
    }

    /**
     * 根据回帖ID查询被回复的帖子中(所有信息)   //查询userId,postId
     * @param replyId
     * @return
     */
    @RequestMapping("queryUserId")
    @ResponseBody
    public ResultData queryUserId(Long replyId){
        List<PfoComment> commentList = commentService.queryUserId(replyId);
        ResultData resultData = new ResultData();
        if(null == commentList || commentList.size() == 0){
            resultData.setCode(3);
            resultData.setMessage("查无数据");
        }else {
            resultData.setCode(0);
            //System.out.println("发布时间"+commentList.get(0).getDeliverTime());
            resultData.setData(commentList);
        }
        return resultData;
    }

    @RequestMapping("queryPostId")
    @ResponseBody
    public ResultData queryPostId(Long replyId){
        List<PfoComment> commentList = commentService.queryUserId(replyId);
        ResultData resultData = new ResultData();
        if(null == commentList || commentList.size() == 0){
            resultData.setCode(3);
            resultData.setMessage("查无数据");
        }else {
            resultData.setCode(0);
            //System.out.println("发布时间"+commentList.get(0).getDeliverTime());
            resultData.setData(commentList.get(0).getPostId());
        }
        return resultData;
    }

    /**
     * 根据用户ID查询所有评论回复
     * @param userId
     * @return
     */
    @RequestMapping("queryAllCommentsByUserId")
    @ResponseBody
    public ResultData queryAllCommentsByUserId(Long userId){
        List<PfoComment> commentList = commentService.queryAllCommentsByUserId(userId);
        ResultData resultData = new ResultData();
        if(null == commentList || commentList.size() == 0){
            resultData.setCode(3);
            resultData.setMessage("查无数据");
        }else {
            resultData.setCode(0);
            //System.out.println(commentList);
            for (int i = 0; i < commentList.size(); i++) {
                if (commentList.get(i).getPostId() == null){
                   // System.out.println(queryUserId(commentList.get(i).getReplyId()).getData());
                   // System.out.println("测试");
                    commentList.get(i).setPostId((Long)queryPostId(commentList.get(i).getReplyId()).getData());
                    //差一层评论
                }
            }
            resultData.setData(commentList);
        }
        return resultData;
    }


}
