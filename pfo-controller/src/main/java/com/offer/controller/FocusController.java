package com.offer.controller;

import com.offer.pojo.PfoFocus;
import com.offer.pojo.PfoUser;
import com.offer.service.FocusService;
import com.offer.vo.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by YMJ on 2019-09-11.
 */
@Controller
@RequestMapping("focus")
public class FocusController {
    @Autowired
    FocusService focusService;

    /**
     * 根据pfoFocus参数查询用户关注与用户粉丝情况
     * @return
     */
    @RequestMapping("queryFocus")
    @ResponseBody
    public ResultData queryFocus(PfoFocus pfoFocus){
        ResultData resultData = new ResultData();
        if(pfoFocus.getUserId() != null || pfoFocus.getFocusUserId() != null){
            List<PfoFocus> focusList = focusService.queryFocus(pfoFocus);
            if(null == focusList || focusList.size() == 0){
                resultData.setCode(3);
                resultData.setMessage("查无数据");
            }else{
                resultData.setCode(0);
                resultData.setData(focusList);
            }
        }else{
            resultData.setCode(5);
            resultData.setMessage("参数有误");
        }
        return resultData;
    }

    /**
     * pfoFocus中有userId和focusId
     * 接受userId,focusId,删除表中user_id为userId并且focus_Id为focusId的数据记录
     * 表示userId用户取消关注focus_id用户
     * @param pfoFocus 想要删除的记录
     * @return
     */
    @RequestMapping("deleteFocus")
    @ResponseBody
    public ResultData deleteFocus(PfoFocus pfoFocus,HttpSession session){
        ResultData resultData = new ResultData();
        //此处pfoFocus的userId和focusUserId必须不为空才能删除唯一记录，否则很可能删除到其他记录
        if(pfoFocus.getUserId()!=null && pfoFocus.getFocusUserId()!=null){
            PfoUser user = (PfoUser) session.getAttribute("user");
            if(null == user){
                resultData.setCode(4);
                resultData.setMessage("请登陆后进行操作");
                return resultData;
            }
            if(!user.getUserId().equals(pfoFocus.getUserId())){
                resultData.setCode(4);
                resultData.setMessage("当前登录用户与操作所属目标用户不符，无法进行此操作！");
                return resultData;
            }

            int deleteStatus = focusService.deleteFocus(pfoFocus);
            if(deleteStatus>0){
                resultData.setCode(0);
            }else{
                resultData.setCode(3);
                resultData.setMessage("取关失败");
            }
        }else{
            resultData.setCode(4);
            resultData.setMessage("参数有误");
        }
        return resultData;
    }

    /**
     * 根据信息添加用户关注
     * @param pfoFocus
     * @return
     */
    @RequestMapping("addFocus")
    @ResponseBody
    public ResultData addFocus(PfoFocus pfoFocus){
        ResultData resultData = new ResultData();
        //关注必须有用户与被关注的人
        if(null != pfoFocus.getUserId() && null != pfoFocus.getFocusUserId()){
            ResultData checkFocus = queryFocus(pfoFocus);//查询关注信息表，防止二次关注
            if(checkFocus.getCode() !=0 ){
                int addFocusStatus = focusService.addFocus(pfoFocus);
                if(addFocusStatus>0){
                    resultData.setCode(0);
                }else{
                    resultData.setCode(3);
                    resultData.setMessage("关注失败");
                }
            }else{
                resultData.setCode(3);
                resultData.setMessage("您已关注了此用户");
            }
        }else{
            resultData.setCode(5);
            resultData.setMessage("参数有误");
        }
        return resultData;
    }
}
