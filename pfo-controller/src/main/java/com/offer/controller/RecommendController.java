package com.offer.controller;

import com.offer.pojo.PfoRecommend;
import com.offer.pojo.PfoRecruitment;
import com.offer.service.RecommendService;
import com.offer.service.RecruitmentService;
import com.offer.service.SignUpService;
import com.offer.vo.ResultData;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @Describe
 * @created ipromisemr
 * @time: 2019/9/17 15:24
 **/
@Controller
@RequestMapping("recommend")
public class RecommendController {

    @Autowired
    private RecommendService recommendService;

    @Autowired
    private SignUpService signUpService;

    /**
     * 获取所有推荐信息
     * @return
     */
    @RequestMapping("queryAllRecommend")
    public @ResponseBody ResultData queryAllRecommend() {
        ResultData resultData = new ResultData();
        List<PfoRecommend> recommendList = recommendService.queryAllRecommend();
        if (null != recommendList && recommendList.size() > 0) {
            resultData.setCode(0);
            resultData.setData(recommendList);
        } else {
            resultData.setCode(3);
            resultData.setMessage("查无数据");
        }

        return resultData;
    }

    /**
     * 任意条件获取推送信息
     * @return
     */
    @RequestMapping("queryRecommend")
    public @ResponseBody ResultData queryRecommend() {
        ResultData resultData = new ResultData();
        List<PfoRecommend> recommendList = recommendService.queryAllRecommend();
        if (null != recommendList && recommendList.size() > 0) {
            resultData.setCode(0);
            resultData.setData(recommendList);
        } else {
            resultData.setCode(3);
            resultData.setMessage("查无数据");
        }

        return resultData;
    }

    /**
     * 根据主键修改推送信息（管理员）
     * @param record
     * @return
     */
    @RequestMapping("updateRecommend")
    public @ResponseBody ResultData updateByPrimaryKeySelective(PfoRecommend record) {
        ResultData resultData = new ResultData();
        if (null != record.getRecommendId()) {
            int result = recommendService.updateByPrimaryKeySelective(record);
            if (result > 0) {
                resultData.setCode(0);
            } else {
                resultData.setCode(3);
                resultData.setMessage("删除失败");
            }
        } else {
            resultData.setCode(5);
            resultData.setMessage("参数错误");
        }

        return resultData;
    }

    /**
     * 添加
     * @param pfoRecommend
     * @return
     */
    @RequestMapping("addRecommend")
    public @ResponseBody ResultData addPfoRecommend(PfoRecommend pfoRecommend) {
        ResultData resultData = new ResultData();

        String recruitmentList = new String();
        StringBuffer stringBuffer = new StringBuffer(recruitmentList);
        Map<String, Integer> countMap = signUpService.queryByRecruitmentIdWithCount();
        for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
            String recruitmentId = new String(entry.getKey() + ",");
            stringBuffer.append(recruitmentId);
        }

        recruitmentList = stringBuffer.toString();
        pfoRecommend.setRecruitmentIdList(recruitmentList);
        System.out.println("pfoRecommend.getRecruitmentIdList:" + pfoRecommend.getRecruitmentIdList());
        int result = recommendService.addPfoRecommend(pfoRecommend);
        if (result > 0) {
            resultData.setCode(0);
            resultData.setMessage("添加成功");
        } else {
            resultData.setCode(3);
            resultData.setMessage("添加失败");
        }

        return resultData;
    }

    /**
     * 删除推送记录
     * @param recommendId
     * @return
     */
    @RequestMapping("deleteRecommend")
    public @ResponseBody ResultData deleteByPrimaryKey(Integer recommendId) {
        ResultData resultData = new ResultData();
        if (null != recommendId) {
            int result = recommendService.deleteByPrimaryKey(recommendId);
            if (result > 0) {
                resultData.setCode(0);
            } else {
                resultData.setCode(3);
                resultData.setMessage("删除失败");
            }
        } else {
            resultData.setCode(5);
            resultData.setMessage("参数错误");
        }

        return resultData;
    }

}
