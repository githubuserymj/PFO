package com.offer.controller;

import com.offer.dta.OrderParameter;
import com.offer.dta.PageInfoResult;
import com.offer.pojo.*;
import com.offer.service.RecommendService;
import com.offer.service.RecruitmentService;
import com.offer.service.SignUpService;
import com.offer.vo.ResultData;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * @Describe
 * @created ipromisemr
 * @time: 2019/9/17 12:30
 **/
@Controller
@RequestMapping("recruitment")
public class RecruitmentController {
    @Autowired
    RecruitmentService recruitmentService;

    @Autowired
    SignUpService signUpService;

    @Autowired
    RecommendService recommendService;

    /**
     * 通过招聘主键查看招聘详情信息  关联公司表
     * @param recruitmentId
     * @return
     */
    @RequestMapping("queryByRecruitmentId")
    @ResponseBody
    public ResultData queryByRecruitmentId(Integer recruitmentId) {
//        System.out.println(recruitmentId);
        ResultData resultData = new ResultData();
        PfoRecruitment recruitment = recruitmentService.queryByRecruitmentId2(recruitmentId);
        if (null != recruitment) {
            resultData.setCode(0);
            resultData.setData(recruitment);
        } else {
            resultData.setData(3);
            resultData.setMessage("查无数据");
        }
        return resultData;
    }

    /**
     * 通过招聘主键查看招聘详情信息  关联公司表,用户表  公司与用户 一对一
     * @param recruitmentId
     * @return
     */
    @RequestMapping("queryByRecruitmentId2")
    @ResponseBody
    public ResultData queryByRecruitmentId2(Integer recruitmentId) {
//        System.out.println(recruitmentId);
        ResultData resultData = new ResultData();
        PfoRecruitment recruitment = recruitmentService.queryByRecruitmentId2(recruitmentId);
        if (null != recruitment) {
            resultData.setCode(0);
            resultData.setData(recruitment);
        } else {
            resultData.setData(3);
            resultData.setMessage("查无数据");
        }
        return resultData;
    }

    /**
     * 获取所有招聘信息
     * @return
     */
    @RequestMapping("queryAllRecruitment")
    @ResponseBody
    public ResultData queryAllRecruitment(){
        ResultData resultData = new ResultData();
        List<PfoRecruitment> recruitmentList = recruitmentService.queryAllRecruitment();
        if (null != recruitmentList && 0 < recruitmentList.size()) {
            resultData.setCode(0);
            resultData.setData(recruitmentList);
        } else {
            resultData.setCode(3);
            resultData.setMessage("查无数据");
        }

        return resultData;
    }

    /**
     * 获取推荐招聘信息
     * @return
     */
    @RequestMapping("queryRecruitmentByRecommend")
    @ResponseBody
    public ResultData queryRecruitmentByRecommend() {
        ResultData resultData = new ResultData();

        Integer recommendId = 1;
        // 取pfo_recommend表里的第一条记录
        PfoRecommend pfoRecommend = recommendService.queryRecommend(recommendId);
        String ids = pfoRecommend.getRecruitmentIdList();

//        System.out.println(ids);


//        if (ids == null) {
//            //  推荐列表为空，则推荐列表中插入数据
//        } else {
//
//        }

        // 查询招聘信息表
        List<PfoRecruitment> recruitmentList = recruitmentService.queryRecruitmentByRecommend(ids);


        if (null != recruitmentList) {
            resultData.setCode(0);
            resultData.setData(recruitmentList);
        } else {
            resultData.setCode(5);
            resultData.setMessage("参数错误");
        }

        return resultData;
    }

    /**
     * 任意条件查询  单表  recruit_recommend.html
     * @param pfoRecruitment
     * @return
     */
    @RequestMapping("queryRecruitmentRecently")
    public @ResponseBody ResultData queryRecruitment(PfoRecruitment pfoRecruitment) {
        ResultData resultData = new ResultData();
        List<PfoRecruitment> recruitmentList = recruitmentService.queryRecruitmentRecently(pfoRecruitment);
        if (null != recruitmentList && 0 < recruitmentList.size()) {
            resultData.setCode(0);
            resultData.setData(recruitmentList);
        } else {
            resultData.setCode(3);
            resultData.setMessage("查无数据");
        }
        return resultData;
    }

    /**
     * 查询公司所有的招聘信息  不分页
     * @param companyId
     * @return
     */
    @RequestMapping("queryRecruitmentByCompanyId")
    @ResponseBody
    public ResultData queryRecruitWithCompanyId(Integer companyId) {
        ResultData resultData = new ResultData();
        List<PfoRecruitment> recruitmentList = recruitmentService.queryByCompanyId(companyId);
        if (null != recruitmentList && 0 < recruitmentList.size()) {
            resultData.setCode(0);
            resultData.setData(recruitmentList);
        } else {
            resultData.setData(3);
            resultData.setMessage("查无数据");
        }

        return resultData;
    }

    /**
     * 分页查询  单表
     * @param pageParameter
     * @param recruitment
     * @param orderObj
     * @return
     */
    @RequestMapping("queryRecruitmentsWithPage")
    @ResponseBody
    public PageInfoResult<PfoRecruitment> queryRecruitmentsWithPage(@Param("pageParameter") PageInfoResult pageParameter, @Param("recruitment") PfoRecruitment recruitment, @Param("orderObj") OrderParameter orderObj) {
        orderObj.setOrderKey("dt");
        orderObj.setOrderType("desc");
        return recruitmentService.queryRecruitmentsWithPage(pageParameter, recruitment, orderObj);
    }

    /**
     * 任意条件分页查询  多表
     * @return
     */
    @RequestMapping("queryAllRecruitmentsWithPage")
    public @ResponseBody PageInfoResult<PfoRecruitment> queryAllRecruitmentsWithPage(@Param("pageParameter") PageInfoResult pageParameter, @Param("recruitment") PfoRecruitment recruitment, @Param("orderObj") OrderParameter orderObj) {
        orderObj.setOrderKey("dt");
        orderObj.setOrderType("desc");
        return recruitmentService.queryAllRecruitmentsWithPage(pageParameter, recruitment, orderObj);
    }

    /**
     * 根据公司查询招聘信息  在公司主页，只查询部分，不分页
     * @param companyId
     * @return
     */
    @RequestMapping("queryByCompany")
    public @ResponseBody ResultData queryByCompany(Long companyId) {
        ResultData resultData = new ResultData();
        PfoCompany pfoCompany = new PfoCompany();
        pfoCompany.setCompanyId(companyId);
        List<PfoRecruitment> recruitmentList = recruitmentService.queryByCompany(pfoCompany);
        if (null != recruitmentList && 0 < recruitmentList.size()) {
            resultData.setCode(0);
            resultData.setData(recruitmentList);
        } else {
            resultData.setCode(3);
            resultData.setMessage("oh, 查无数据");
        }

        return resultData;
    }

    /**
     * 查询属于公司的所有招聘信息，含分页  招聘列表页
     * @param pageParameter
     * @param recruitment
     * @param orderObj
     * @param companyId
     * @return
     */
    @RequestMapping("queryRecruitmentByCompanyWithPage")
    public @ResponseBody PageInfoResult<PfoRecruitment> queryByCompany2(@Param("pageParameter") PageInfoResult pageParameter, @Param("recruitment") PfoRecruitment recruitment, @Param("orderObj") OrderParameter orderObj, @Param("companyId") Long companyId) {
//        System.out.println(companyId);
        recruitment.setCompanyId(companyId);
        orderObj.setOrderKey("dt");
        orderObj.setOrderType("desc");
        return recruitmentService.queryRecruitmentByCompanyWithPage(pageParameter, recruitment, orderObj);
    }

    /**
     * 查询属于公司的所有招聘信息，含分页  招聘列表页
     * @param pageParameter
     * @param recruitment
     * @param orderObj
     * @param companyName
     * @return
     */
    @RequestMapping("queryRecruitmentByCompanyNameWithPage")
    public @ResponseBody PageInfoResult<PfoRecruitment> queryByCompanyName(@Param("pageParameter") PageInfoResult pageParameter, @Param("recruitment") PfoRecruitment recruitment, @Param("orderObj") OrderParameter orderObj, @Param("companyName") String companyName) {
        orderObj.setOrderKey("dt");
        orderObj.setOrderType("desc");
        return recruitmentService.queryByCompanyNameWithPage(pageParameter, recruitment, orderObj, companyName);
    }

    /**
     * 获取招聘信息报名人数 招聘信息详情页
     * @param recruitmentId
     * @return
     */
    @RequestMapping("queryCountByRecruitmentId")
    @ResponseBody
    public ResultData queryCountByRecruitmentId(Integer recruitmentId) {
        ResultData resultData = new ResultData();
        if (null != recruitmentId) {
            Integer result = recruitmentService.queryCountByRecruitmentId(recruitmentId);

            if (result > 0) {
                resultData.setCode(result);
            } else {
                resultData.setCode(3);
                resultData.setMessage("查无数据");
            }
        } else {
            resultData.setCode(5);
            resultData.setMessage("参数错误");
        }

        return resultData;
    }

    /**
     * 获取该公司所有招聘信息的报名总数
     * @param companyId
     * @return
     */
    @RequestMapping("queryCountByCompanyId")
    @ResponseBody
    public ResultData queryCountByCompanyId(Integer companyId) {
        ResultData resultData = new ResultData();
        if (null != companyId) {
            Integer result = recruitmentService.queryCountByCompanyId(companyId);

            if (result > 0) {
                resultData.setCode(result);
            } else {
                resultData.setCode(3);
                resultData.setMessage("查无数据");
            }
        } else {
            resultData.setCode(5);
            resultData.setMessage("参数错误");
        }

        return resultData;
    }

    /**
     * 查看公司的招聘信息及报名人数
     * @param companyId
     * @return
     */
    @RequestMapping("queryExampleBycompanyId")
    @ResponseBody
    public ResultData queryExampleBycompanyId(Long companyId) {
        ResultData resultData = new ResultData();
        Map<Long, Integer> countMap = recruitmentService.queryExampleBycompanyId(companyId);

        ArrayList<Map> mapArrayList = new ArrayList<>();
        for (Map.Entry<Long, Integer> entry : countMap.entrySet()) {
            Map<String, Object> exampleMap = new HashMap<>();

            Long id = entry.getKey();
            PfoRecruitment pfoRecruitment = recruitmentService.queryByRecruitmentId(id);
            exampleMap.put("recruitmentId", pfoRecruitment.getRecruitmentId());
            exampleMap.put("recruitmentTitle", pfoRecruitment.getRecruitmentTitle());
            exampleMap.put("position", pfoRecruitment.getPosition());
            exampleMap.put("count", entry.getValue());

            mapArrayList.add(exampleMap);
        }

//        System.out.println(mapArrayList);

        resultData.setCode(0);
        resultData.setData(mapArrayList);
        return resultData;
    }

    @RequestMapping("deleteByPrimaryKey")
    public @ResponseBody ResultData deleteByPrimaryKey(Long recruitmentId) {
        ResultData resultData = new ResultData();
        if (null != recruitmentId) {
            int result = recruitmentService.deleteByPrimaryKey(recruitmentId);

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
     * 通过主键修改招聘信息
     * @param pfoRecruitment
     * @return
     */
    @RequestMapping("updateByPrimaryKey")
    public @ResponseBody ResultData updateByPrimaryKey(PfoRecruitment pfoRecruitment) {
        pfoRecruitment.setDeliverTime(new Date());
        ResultData resultData = new ResultData();

//        System.out.println(pfoRecruitment.getDeliverTime());
        if (null != pfoRecruitment.getRecruitmentId()) {
            int result = recruitmentService.updateByPrimaryKey(pfoRecruitment);
            if (result > 0) {
                resultData.setCode(0);
            } else {
                resultData.setCode(3);
                resultData.setMessage("更新失败");
            }
        } else {
            resultData.setCode(5);
            resultData.setMessage("参数错误");
        }
        return resultData;
    }

    /**
     * 添加招聘信息
     * @param pfoRecruitment
     * @return
     */
    @RequestMapping("addRecruitmentByCompanyId")
    public @ResponseBody ResultData addRecruitment(PfoRecruitment pfoRecruitment) {

//        System.out.println(pfoRecruitment.getCompanyId());

        ResultData resultData = new ResultData();

        pfoRecruitment.setDeliverTime(new Date());

        if (pfoRecruitment.getCompanyId() != null && pfoRecruitment.getRecruitmentContent() != null) {

            int result = recruitmentService.addRecruitment(pfoRecruitment);
            if (result > 0) {
                resultData.setCode(0);
                resultData.setMessage("发布成功");
            } else {
                resultData.setCode(3);
                resultData.setMessage("添加失败");
            }
        } else {
            resultData.setCode(5);
            resultData.setMessage("传入参数错误");
        }
        return resultData;
    }



    /**
     * 加载日历招聘信息
     * @return
     */
    public ResultData loadExampleOnCalendar() {
        return null;
    }

}
