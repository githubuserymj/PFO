package com.offer.controller;

import com.offer.pojo.PfoPost;
import com.offer.pojo.PfoTopic;
import com.offer.service.TopicService;
import com.offer.vo.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("topic")
public class TopicController {
    @Autowired
    TopicService topicService;
    /**
     * 显示所有topic信息
     * @param resultData
     * @return
     */
    @RequestMapping("showAllTopics")
    @ResponseBody
    public ResultData queryAllTopics(@RequestAttribute(value = "resultData",required = false) ResultData resultData){
        if(null == resultData) {
            resultData = new ResultData();
        } else {
            // 删除失败
            if(resultData.getCode() == 4) {
                return resultData;
            }
        }
        List<PfoTopic> topicList = topicService.queryAllTopics();
        if (null == topicList || topicList.size() ==0){
            resultData.setCode(3);
            resultData.setMessage("查无数据");
        }else{
            resultData.setCode(0);
            //System.out.println("主题名称："+topicList.get(0).getTopicName());
            resultData.setData(topicList);
        }
        return resultData;
    }

    @RequestMapping("queryAllPostByTopicId")
    @ResponseBody
    public ResultData queryAllPostByTopicId(@RequestAttribute(value = "resultData",required = false) ResultData resultData,Integer topicId){
        if(null == resultData) {
            resultData = new ResultData();
        } else {
            // 删除失败
            if(resultData.getCode() == 4) {
                return resultData;
            }
        }
        List<PfoTopic> topicList = topicService.queryTopicWithPosts(topicId);
        System.out.println(topicList);
        if (null == topicList || topicList.size() ==0){
            resultData.setCode(3);
            resultData.setMessage("查无数据");
        }else{
            resultData.setCode(0);
            //System.out.println("主题名称："+topicList.get(0).getPostList().get(0).getPostBody());
            resultData.setData(topicList);
        }
        return resultData;
    }

}
