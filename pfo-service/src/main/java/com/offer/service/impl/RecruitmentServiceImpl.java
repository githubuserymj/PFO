package com.offer.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.offer.dta.OrderParameter;
import com.offer.dta.PageInfoResult;
import com.offer.mapper.PfoRecommendMapper;
import com.offer.mapper.PfoRecruitmentMapper;
import com.offer.mapper.PfoSignUpMapper;
import com.offer.pojo.*;
import com.offer.service.RecruitmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Describe
 * @created ipromisemr
 * @time: 2019/9/17 11:39
 **/
@Service
public class RecruitmentServiceImpl implements RecruitmentService {

    @Autowired
    PfoRecruitmentMapper pfoRecruitmentMapper;

    @Autowired
    PfoSignUpMapper pfoSignUpMapper;

    @Autowired
    PfoRecommendMapper pfoRecommendMapper;

    /**
     * 获取所有招聘信息
     * @return
     */
    @Override
    public List<PfoRecruitment> queryAllRecruitment() {
        return pfoRecruitmentMapper.queryAllRecruitment();
    }

    /**
     * 获取近期招聘信息 单表 20条
     * @param pfoRecruitment
     * @return
     */
    @Override
    public List<PfoRecruitment> queryRecruitmentRecently(PfoRecruitment pfoRecruitment) {
        return pfoRecruitmentMapper.queryRecruitmentRecently(pfoRecruitment);
    }

    /**
     * 查询招聘信息及关联公司信息
     * @param companyId
     * @return
     */
    @Override
    public List<PfoRecruitment> queryByCompanyId(Integer companyId) {
        return pfoRecruitmentMapper.queryByCompanyId(companyId);
    }

    /**
     * 根据公司关联查询招聘信息
     * @param pfoCompany
     * @return
     */
    @Override
    public List<PfoRecruitment> queryByCompany(PfoCompany pfoCompany) {
        return pfoRecruitmentMapper.queryByCompany(pfoCompany);
    }

    /**
     * 查看招聘详情  关联公司信息
     * @param recruitmentId
     * @return
     */
    public PfoRecruitment queryByRecruitmentId(Long recruitmentId) {
        return pfoRecruitmentMapper.selectByPrimaryKey(recruitmentId);
    }

    @Override
    public PfoRecruitment queryByRecruitmentId2(Integer recruitmentId) {
        return pfoRecruitmentMapper.queryByRecruitmentId2(recruitmentId);
    }

    /**
     * 分页查询  单表
     * @param pageParameter
     * @param recruitment
     * @param orderObj
     * @return
     */
    @Override
    public PageInfoResult<PfoRecruitment> queryRecruitmentsWithPage(PageInfoResult pageParameter, PfoRecruitment recruitment, OrderParameter orderObj) {
        PageHelper.startPage(pageParameter.getCurrPage(), pageParameter.getPageSize());

        HashMap<String, String> orderMap = new HashMap<>();
        if (orderObj != null) {
            HashMap<String, String> sortMap = new HashMap<>();
            if (orderObj.getOrderKey() != null) {
                String[] keys = orderObj.getOrderKey().split(",");
                String[] types = orderObj.getOrderType().split(",");
                for (int i = 0; i < keys.length; i++) {
                    // 根据发布时间进行排序
                    if ("dt".equals(keys[i])) {
                        sortMap.put("deliver_time", types[i]);
                    }
                }

                for (Map.Entry<String, String> entry : sortMap.entrySet()) {
                    String key = "pfo_recruitment." + entry.getKey();
                    orderMap.put(key, entry.getValue());
                }
            }
        }

        List<PfoRecruitment> recruitments = pfoRecruitmentMapper.queryAllRecruitmentsWithPage(recruitment, orderMap);

        PageInfo<PfoRecruitment> pageInfo = new PageInfo<>(recruitments);

        List<PfoRecruitment> recruitmentList = pageInfo.getList();
        pageParameter.setTotalSize(pageInfo.getTotal());
        pageParameter.setDataList(recruitmentList);

        return pageParameter;
    }

    /**
     *
     * @param pageParameter
     * @param recruitment
     * @param orderObj
     * @return
     */
    @Override
    public PageInfoResult<PfoRecruitment> queryByCompanyNameWithPage(PageInfoResult pageParameter, PfoRecruitment recruitment, OrderParameter orderObj, String companyName) {
        PageHelper.startPage(pageParameter.getCurrPage(), pageParameter.getPageSize());

        HashMap<String, String> orderMap = new HashMap<>();
        if (orderObj != null) {
            HashMap<String, String> sortMap = new HashMap<>();
            if (orderObj.getOrderKey() != null) {
                String[] keys = orderObj.getOrderKey().split(",");
                String[] types = orderObj.getOrderType().split(",");
                for (int i = 0; i < keys.length; i++) {
                    // 根据发布时间进行排序
                    if ("dt".equals(keys[i])) {
                        sortMap.put("deliver_time", types[i]);
                    }
                }

                for (Map.Entry<String, String> entry : sortMap.entrySet()) {
                    String key = "pfo_recruitment." + entry.getKey();
                    orderMap.put(key, entry.getValue());
                }
            }
        }

        System.out.println("companyName:" + companyName);

        List<PfoRecruitment> recruitments = pfoRecruitmentMapper.queryRecruitmentByCompanyWithPage(recruitment, orderMap, companyName);

        System.out.println("recruitments:" + recruitments.size());

        PageInfo<PfoRecruitment> pageInfo = new PageInfo<>(recruitments);

        List<PfoRecruitment> recruitmentList = pageInfo.getList();
        pageParameter.setTotalSize(pageInfo.getTotal());
        pageParameter.setDataList(recruitmentList);

        return pageParameter;
    }

    /**
     * 分页查询 与公司的关联
     * @param pageParameter
     * @param recruitment
     * @param orderObj
     * @return
     */
    @Override
    public PageInfoResult<PfoRecruitment> queryAllRecruitmentsWithPage(PageInfoResult pageParameter, PfoRecruitment recruitment, OrderParameter orderObj) {
        PageHelper.startPage(pageParameter.getCurrPage(), pageParameter.getPageSize());

        HashMap<String, String> orderMap = new HashMap<>();
        if (orderObj != null) {
            HashMap<String, String> sortMap = new HashMap<>();
            if (orderObj.getOrderKey() != null) {
                String[] keys = orderObj.getOrderKey().split(",");
                String[] types = orderObj.getOrderType().split(",");
                for (int i = 0; i < keys.length; i++) {
                    // 根据发布时间进行排序
                    if ("dt".equals(keys[i])) {
                        sortMap.put("deliver_time", types[i]);
                    }
                }

                for (Map.Entry<String, String> entry : sortMap.entrySet()) {
                    String key = "pfo_recruitment." + entry.getKey();
                    orderMap.put(key, entry.getValue());
                }
            }
        }

        List<PfoRecruitment> recruitments = pfoRecruitmentMapper.queryAllRecruitmentsWithPage(recruitment, orderMap);

        PageInfo<PfoRecruitment> pageInfo = new PageInfo<>(recruitments);

        List<PfoRecruitment> recruitmentList = pageInfo.getList();
        pageParameter.setTotalSize(pageInfo.getTotal());
        pageParameter.setDataList(recruitmentList);

        return pageParameter;
    }

    public PageInfoResult<PfoRecruitment> queryRecruitmentByCompanyWithPage(PageInfoResult pageParameter, PfoRecruitment recruitment, OrderParameter orderObj) {
        PageHelper.startPage(pageParameter.getCurrPage(), pageParameter.getPageSize());

        HashMap<String, String> orderMap = new HashMap<>();
        if (orderObj != null) {
            HashMap<String, String> sortMap = new HashMap<>();
            if (orderObj.getOrderKey() != null) {
                String[] keys = orderObj.getOrderKey().split(",");
                String[] types = orderObj.getOrderType().split(",");
                for (int i = 0; i < keys.length; i++) {
                    // 根据发布时间进行排序
                    if ("dt".equals(keys[i])) {
                        sortMap.put("deliver_time", types[i]);
                    }
                }

                for (Map.Entry<String, String> entry : sortMap.entrySet()) {
                    String key = "pfo_recruitment." + entry.getKey();
                    orderMap.put(key, entry.getValue());
                }
            }
        }

        List<PfoRecruitment> recruitments = pfoRecruitmentMapper.queryAllRecruitmentsWithPage(recruitment, orderMap);

        PageInfo<PfoRecruitment> pageInfo = new PageInfo<>(recruitments);

        List<PfoRecruitment> recruitmentList = pageInfo.getList();
        pageParameter.setTotalSize(pageInfo.getTotal());
        pageParameter.setDataList(recruitmentList);

        return pageParameter;
    }

    /**
     * 根据主键删除招聘信息
     * @param recruitmentId
     * @return
     */
    @Override
    public int deleteByPrimaryKey(Long recruitmentId) {
        return pfoRecruitmentMapper.deleteByPrimaryKey(recruitmentId);
    }

    /**
     * 修改招聘信息
     * @param record
     * @return
     */
    @Override
    public int updateByPrimaryKey(PfoRecruitment record) {
        return pfoRecruitmentMapper.updateByPrimaryKey(record);
    }

    /**
     * 添加招聘信息(管理员添加)
     * @param pfoRecruitment
     * @return
     */
    @Override
    public int addRecruitment(PfoRecruitment pfoRecruitment) {
        return pfoRecruitmentMapper.insertSelective(pfoRecruitment);
    }

    /**
     * 获取招聘信息报名人数
     * @param recruitmentId
     * @return
     */
    public Integer queryCountByRecruitmentId(Integer recruitmentId) {
        return pfoRecruitmentMapper.queryCountByRecruitmentId(recruitmentId);
    }

    /**
     * 获取公司招聘人数
     * @param companyId
     * @return
     */
    public Integer queryCountByCompanyId(Integer companyId) {
        return pfoRecruitmentMapper.queryCountByCompanyId(companyId);
    }

    /**
     * 获取推荐招聘信息  (不从pfo_recommend表中获取)
     * @return
     */
    public List<PfoRecruitment> queryRecruitmentByRecommend(String ids) {
        List<Long> idList = new ArrayList<>();
        String idStr[] = ids.split(",");
        Long id = 0L;

        for (int i = 0; i < idStr.length; i++) {
            id = Long.parseLong(idStr[i]);
            idList.add(id);
        }

        List<PfoRecruitment> recruitmentList = pfoRecruitmentMapper.queryRecruitmentByRecommend(idList);
        return recruitmentList;
    }

    /**
     * 公司查看招聘信息的报名人数  用于公司主页的统计
     * @param companyId
     * @return
     */
    public Map<Long, Integer> queryExampleBycompanyId(Long companyId) {
        HashMap<Long, Integer> hashMap2 = new HashMap<>();
        List<Map<Long, Integer>> mapList = pfoRecruitmentMapper.queryExampleBycompanyId(companyId);

        // [{recruitment_id=20190101, signUpCount=2},
        // {recruitment_id=20190102, signUpCount=1}, {recruitment_id=20190103, signUpCount=1}]

        for (Map<Long, Integer> map : mapList) {
            Long recuirtmentId = null;
            Integer count = null;
            for (Map.Entry<Long, Integer> entry : map.entrySet()) {
                if ("recruitment_id".equals(entry.getKey())) {
                    recuirtmentId = new Long(String.valueOf(entry.getValue()));
                }
                if ("signUpCount".equals(entry.getKey())) {
                    count = new Integer(String.valueOf(entry.getValue()));
                }

                hashMap2.put(recuirtmentId, count);
            }
        }

        System.out.println(hashMap2);

        return hashMap2;
    }
}
