package com.offer.controller;

import com.offer.pojo.PfoFeedback;
import com.offer.service.FeedbackService;
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
@RequestMapping("feedback")
public class FeedbackController {
    Date date = new Date();
    @Autowired
    FeedbackService feedbackService;

    /**
     * 展示所有反馈信息
     * @param resultData
     * @return
     */
    @RequestMapping("showAllFeedbacks")
    @ResponseBody
    //显示数据
    public ResultData queryAllFeedbacks(@RequestAttribute(value = "resultData",required = false) ResultData resultData){
        if(null == resultData) {
            resultData = new ResultData();
        } else {
            // 删除失败
            if(resultData.getCode() == 4) {
                return resultData;
            }
        }
        List<PfoFeedback> feedbackList = feedbackService.queryAllFeedbacks();
        if (null == feedbackList || feedbackList.size() ==0){
            resultData.setCode(3);
            resultData.setMessage("查无数据");
        }else{
            resultData.setCode(0);
            System.out.println("反馈信息："+feedbackList.get(0).getFeedbackMessage());
            resultData.setData(feedbackList);
        }
        return resultData;
    }

    /**
     * 根据ID删除反馈信息
     * @param model
     * @param feedbackId
     * @return
     */
    @RequestMapping("deleteById")
    //@ResponseBody
    public String deleteById(Model model, Long feedbackId){
        int affectedRow = feedbackService.deleteById(feedbackId);
        ResultData resultData = new ResultData();
        if (affectedRow > 0){
            resultData.setCode(0);
        }else{
            resultData.setCode(4);
            resultData.setData(feedbackService.queryAllFeedbacks());
            resultData.setMessage("删除失败");
        }
        model.addAttribute("resultData", resultData);
        System.out.println(resultData);
        return "forward:showAllFeedbacks";
    }

    /**
     * 添加反馈信息
     * @param pfoFeedback
     * @return
     */
    @RequestMapping("addFeedback")
    @ResponseBody
    public ResultData addFeedback(PfoFeedback pfoFeedback){
        date = new Date();
        pfoFeedback.setDeliverTime(date);
        int affectedRow = feedbackService.addFeedback(pfoFeedback);
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

}
