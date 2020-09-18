package com.offer.controller;

import com.offer.dta.OrderParameter;
import com.offer.dta.PageInfoResult;
import com.offer.pojo.PfoExam;
import com.offer.pojo.PfoRecruitment;
import com.offer.pojo.PfoSignUp;
import com.offer.pojo.PfoUser;
import com.offer.service.RecruitmentService;
import com.offer.service.SignUpService;
import com.offer.vo.ResultData;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Describe 报名表
 * @created ipromisemr
 * @time: 2019/9/17 10:30
 **/
@Controller
@RequestMapping("signUp")
public class SignUpController {
    @Autowired
    SignUpService signUpService;

    @Autowired
    RecruitmentService recruitmentService;

    /**
     * 获取所有报名信息
     * @return
     */
    @RequestMapping("queryAllSignUp")
    @ResponseBody
    public ResultData queryAllSignUp() {
        ResultData resultData = new ResultData();
        List<PfoSignUp> signUpList = signUpService.queryAllSignUp();
        signUpList.forEach(System.out::println);
        if (null != signUpList && 0 < signUpList.size()) {
            resultData.setCode(0);
            resultData.setData(signUpList);
        } else {
            resultData.setData(3);
            resultData.setMessage("查无数据");
        }
        return resultData;
    }

    /**
     * 任意条件查询报名信息
     * @param pfoSignUp
     * @return
     */
    @RequestMapping("querySignUp")
    @ResponseBody
    public ResultData querySignUp(PfoSignUp pfoSignUp) {
        ResultData resultData = new ResultData();
        List<PfoSignUp> signUpList = signUpService.querySignUp(pfoSignUp);
        signUpList.forEach(System.out::println);
        if (null != signUpList && 0 < signUpList.size()) {
            resultData.setCode(0);
            resultData.setData(signUpList);
        } else {
            resultData.setData(3);
            resultData.setMessage("查无数据");
        }
        return resultData;
    }

    @RequestMapping("querySignUpWithPage")
    @ResponseBody
    public PageInfoResult<PfoSignUp> querySignUpWithPage(@Param("pageParameter") PageInfoResult pageParameter, @Param("signUp") PfoSignUp signUp, @Param("orderObj") OrderParameter orderObj) {
        // 按成绩升序
        orderObj.setOrderKey("dt");
        orderObj.setOrderType("desc");
        return signUpService.querySignUpWithPage(pageParameter, signUp, orderObj);
    }

    /**
     * 分页查询,获取所有用户的报名表,多表关联
     * @return
     */
    @RequestMapping("queryAllSignUpWithPage")
    @ResponseBody
    public PageInfoResult<PfoSignUp> queryAllSignUpWithPage(@Param("pageParameter") PageInfoResult pageParameter, @Param("signUp") PfoSignUp signUp, @Param("orderObj") OrderParameter orderObj) {
        orderObj.setOrderKey("dt");
        orderObj.setOrderType("asc");
        return signUpService.queryAllSignUpWithPage(pageParameter, signUp, orderObj);
    }

    /**
     * 根据用户获取报名信息，即用户查看所有自己的报名信息
     * @return
     */
    @RequestMapping("queryByUserIdWithPage")
    @ResponseBody
    public PageInfoResult<PfoSignUp> queryByUserIdWithPage(@Param("pageParameter") PageInfoResult pageParameter, @Param("signUp") PfoSignUp signUp, @Param("orderObj") OrderParameter orderObj, @Param("userId") Integer userId) {
        System.out.println(userId);
        orderObj.setOrderType("dt");
        orderObj.setOrderType("asc");
        return signUpService.queryByUserIdWithPage(pageParameter, signUp, orderObj, userId);
    }

    /**
     * 查看招聘信息的报名列表  含分页
     * @param pageParameter
     * @param signUp
     * @param orderObj
     * @param recruitmentId
     * @return
     */
    @RequestMapping("queryByRecruitmentIdWithPage")
    @ResponseBody
    public PageInfoResult<PfoSignUp> queryByRecruitmentIdWithPage(PageInfoResult pageParameter, PfoSignUp signUp, OrderParameter orderObj, Long recruitmentId) {
        orderObj.setOrderType("dt");
        orderObj.setOrderType("desc");
        return signUpService.queryByRecruitmentIdWithPage(pageParameter, signUp, orderObj, recruitmentId);
    }

    /**
     * 根据公司查看招聘信息的报名表  关联招聘信息，公司信息  公司管理员查看
     * @return
     */
    @RequestMapping("queryByCompanyWithPage")
    @ResponseBody
    public PageInfoResult<PfoSignUp> queryByCompanyWithPage(@Param("pageParameter") PageInfoResult pageParameter, @Param("signUp") PfoSignUp signUp, @Param("orderObj") OrderParameter orderObj, @Param("userId") Integer userId) {
        System.out.println(userId);
        orderObj.setOrderType("dt");
        orderObj.setOrderType("asc");
        return signUpService.queryByUserIdWithPage(pageParameter, signUp, orderObj, userId);
    }

//    /**
//     * 查看公司的招聘信息的报名列表
//     * @param pageParameter
//     * @param companyId
//     * @return
//     */
//    @RequestMapping("querySignUpInfoByCompanyId")
//    @ResponseBody
//    public PageInfoResult<PfoRecruitment> querySignUpInfoByCompanyId(PageInfoResult pageParameter, Integer companyId) {
//
//        return recruitmentService.querySignUpInfoByCompanyId(pageParameter, companyId);
//    }

    /**
     * 删除报名信息
     * @param signUpId
     * @return
     */
    @RequestMapping("deleteByPrimaryKey")
    @ResponseBody
    public ResultData deleteByPrimaryKey(Long signUpId) {
        System.out.println("删除报名信息：" + signUpId);

        ResultData resultData = new ResultData();
        if (null != signUpId) {
            int result = signUpService.deleteByPrimaryKey(signUpId);
            if (result > 0) {
                resultData.setCode(0);
                resultData.setMessage("删除成功");
            } else {
                resultData.setCode(3);
                resultData.setMessage("报名信息删除失败");
            }
        } else {
            resultData.setCode(4);
            resultData.setMessage("参数错误");
        }

        return resultData;
    }

    /**
     * 修改报名信息
     */
    @RequestMapping("updateSignUp")
    @ResponseBody
    public ResultData updateByPrimaryKey(PfoSignUp record) {
        ResultData resultData = new ResultData();
        if (null != record.getSignUpId()) {
            int result = signUpService.updateByPrimaryKey(record);
            if (result > 0) {
                resultData.setCode(0);
            } else {
                resultData.setCode(3);
                resultData.setMessage("更新成功");
            }
        } else {
            resultData.setCode(5);
            resultData.setMessage("参数错误");
        }

        return resultData;
    }

    /**
     * 添加报名信息
     * @param userId
     * @param recruitmentId
     * @param deliverTime
     * @return
     */
    @RequestMapping("addSignUp")
    @ResponseBody
    public ResultData addSignUp(String userId, String recruitmentId, String deliverTime) {
        ResultData resultData = new ResultData();
        PfoSignUp signUp = new PfoSignUp();

        signUp.setUserId(Long.parseLong(userId));
        signUp.setRecruitmentId(Long.parseLong(recruitmentId));
        signUp.setDeliverTime(new Date());

        if ((null != signUp.getUserId()) && (null != signUp.getRecruitmentId())) {
            int result = signUpService.addSignUp(signUp);
            if (result > 0) {
                resultData.setCode(0);
                resultData.setMessage("报名成功！");
            } else {
                resultData.setCode(3);
                resultData.setMessage("报名失败");
            }
        } else {
            resultData.setCode(5);
            resultData.setMessage("参数错误");
        }

        return resultData;
    }

//    /**
//     * 获取招聘信息的推荐列表  根据报名表的报名人数
//     * @return
//     */
//    @RequestMapping("queryByRecruitmentIdWithCount")
//    @ResponseBody
//    public ResultData queryByRecruitmentIdWithCount() {
//        ResultData resultData = new ResultData();
//        String recruitmentList = new String();
//        StringBuffer stringBuffer = new StringBuffer(recruitmentList);
//        Map<String, Integer> countMap = signUpService.queryByRecruitmentIdWithCount();
//        for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
//            String recruitmentId = new String(entry.getKey() + ",");
//            stringBuffer.append(recruitmentId);
//        }
//
//        if (null != stringBuffer && stringBuffer.length() != 0) {
//            resultData.setCode(0);
//            resultData.setData(stringBuffer.toString());
//        } else {
//            resultData.setCode(3);
//            resultData.setMessage("查无数据");
//        }
//
////        resultData.setCode(0);
////        resultData.setData(countMap);
//        return resultData;
//    }


}
