package com.offer.controller;

import com.offer.pojo.PfoMessage;
import com.offer.service.MessageService;
import com.offer.vo.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by YMJ on 2019-09-14.
 */
@Controller
@RequestMapping("message")
public class MessageController {
    @Autowired
    MessageService messageService;

    /**
     * 接收参数对消息进行查询
     * @param pfoMessage
     * @return
     */
    @RequestMapping("queryMessage")
    @ResponseBody
    public ResultData queryMessage(PfoMessage pfoMessage){
        ResultData resultData = new ResultData();
        if(null != pfoMessage){
            List<PfoMessage> messageList = messageService.queryMessage(pfoMessage);
            if(null != messageList && messageList.size()>0){
                resultData.setCode(0);
                resultData.setData(messageList);
            }else{
                resultData.setCode(3);
                resultData.setMessage("查无数据");
            }
        }else{
            resultData.setCode(4);
            resultData.setMessage("参数有误");
        }
        return resultData;
    }

    /**
     * 接收参数添加消息
     * @param message
     * @return
     */
    @RequestMapping("addMessage")
    @ResponseBody
    public ResultData addMessage(PfoMessage message){
        ResultData resultData = new ResultData();
        if(null != message.getMessageText() && message.getMessageText() != "" && null != message.getUserId() && !message.getUserId().equals("") && null != message.getTargetUserId() && !message.getTargetUserId().equals("") && null != message.getMessageType() && !message.getMessageText().equals("")){
            int insertStatus = messageService.addMessage(message);
            if(insertStatus>0){
                resultData.setCode(0);
            }else{
                resultData.setCode(3);
                resultData.setMessage("添加失败");
            }
        }else{
            resultData.setCode(4);
            resultData.setMessage("参数有误");
        }
        return resultData;
    }

    /**
     * 接受参数更新消息
     * @param message
     * @return
     */
    @RequestMapping("updateMessage")
    @ResponseBody
    public ResultData updateMessage(PfoMessage message){
        ResultData resultData = new ResultData();
        if(null != message.getMessageId() && (message.getMessageText() != null || message.getTargetUserId() != null || message.getUserId() != null)){
            int updateStatus = messageService.updateMessage(message);
            if(updateStatus>0){
                resultData.setCode(0);
            }else{
                resultData.setCode(3);
                resultData.setMessage("更新失败");
            }
        }else{
            resultData.setCode(4);
            resultData.setMessage("参数有误");
        }
        return resultData;
    }

    /**
     * 根据id删除消息数据
     * @param messageId
     * @return
     */
    @RequestMapping("deleteMessage")
    @ResponseBody
    public ResultData deleteMessage(Long messageId){
        ResultData resultData = new ResultData();
        if(null != messageId){
            int deleteStatus = messageService.deleteMessage(messageId);
            if(deleteStatus>0){
                resultData.setCode(0);
            }else{
                resultData.setCode(3);
                resultData.setMessage("删除失败");
            }
        }else{
            resultData.setCode(4);
            resultData.setMessage("参数有误");
        }
        return resultData;
    }
}
