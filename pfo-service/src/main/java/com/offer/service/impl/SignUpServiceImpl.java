package com.offer.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.offer.dta.OrderParameter;
import com.offer.dta.PageInfoResult;
import com.offer.mapper.PfoSignUpMapper;
import com.offer.pojo.PfoExam;
import com.offer.pojo.PfoRecruitment;
import com.offer.pojo.PfoSignUp;
import com.offer.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Describe
 * @created ipromisemr
 * @time: 2019/9/17 10:22
 **/
@Service
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    PfoSignUpMapper pfoSignUpMapper;

    @Override
    public List<PfoSignUp> queryAllSignUp() {
        return pfoSignUpMapper.querySignUp();
    }

    @Override
    public List<PfoSignUp> querySignUp(PfoSignUp pfoSignUp) {
        return pfoSignUpMapper.querySignUp(pfoSignUp);
    }

    @Override
    public List<PfoSignUp> queryByRecruitmentId(Long recruitmentId) {
        return null;
    }

    /**
     * 分页查询  单表
     * @param pageParameter
     * @param signUp
     * @param orderObj
     * @return
     */
    @Override
    public PageInfoResult<PfoSignUp> querySignUpWithPage(PageInfoResult pageParameter, PfoSignUp signUp, OrderParameter orderObj) {
        PageHelper.startPage(pageParameter.getCurrPage(), pageParameter.getPageSize());

        Map<String, String> orderMap = new HashMap<>();
        if (orderObj != null) {
            HashMap<String, String> sortMap = new HashMap<>();
            if (orderObj.getOrderKey() != null) {
                String[] keys = orderObj.getOrderKey().split(",");
                String[] types = orderObj.getOrderType().split(",");
                for (int i = 0; i < keys.length; i++) {
                    if ("dt".equals(keys[i])) {
                        sortMap.put("deliver_time", types[i]);
                    }
                }
            }

            for (Map.Entry<String, String> entry : sortMap.entrySet()) {
                String key = "pfo_sign_up." + entry.getKey();
                orderMap.put(key, entry.getValue());
            }
        }

        List<PfoSignUp> signUps = pfoSignUpMapper.querySignUpWithPage(signUp, orderMap);

        PageInfo<PfoSignUp> pageInfo = new PageInfo<>(signUps);
        List<PfoSignUp> signUpList = pageInfo.getList();
        pageParameter.setTotalSize(pageInfo.getTotal());
        pageParameter.setDataList(signUpList);

        return pageParameter;
    }

    /**
     * 报名表分页
     * @param pageParameter
     * @param signUp
     * @param orderObj
     * @return
     */
    @Override
    public PageInfoResult<PfoSignUp> queryAllSignUpWithPage(PageInfoResult pageParameter, PfoSignUp signUp, OrderParameter orderObj) {
        PageHelper.startPage(pageParameter.getCurrPage(), pageParameter.getPageSize());

        Map<String, String> orderMap = new HashMap<>();
        if (orderObj != null) {
            Map<String, String> sortMap = new HashMap<>();
            if (orderObj.getOrderKey() != null) {
                String[] keys = orderObj.getOrderKey().split(",");
                String[] types = orderObj.getOrderType().split(",");
                for (int i = 0; i < keys.length; i++) {
                    if ("dt".equals(keys[i])) {
                        sortMap.put("deliver_time", types[i]);
                    }
                }
            }

            for (Map.Entry<String, String> entry : sortMap.entrySet()) {
                String key = "s." + entry.getKey();
                orderMap.put(key, entry.getValue());
            }

        }

        List<PfoSignUp> signUps = pfoSignUpMapper.queryAllSignUpWithPage(signUp, orderMap);

        PageInfo<PfoSignUp> pageInfo = new PageInfo<>(signUps);

        List<PfoSignUp> pfoSignUpList = pageInfo.getList();
        pageParameter.setTotalSize(pageInfo.getTotal());
        pageParameter.setDataList(pfoSignUpList);
        return pageParameter;
    }


    /**
     * 获取招聘信息的报名表信息  关联报名表，招聘信息表，用户表，公司表
     * @param pageParameter
     * @param signUp
     * @param orderObj
     * @param recruitmentId
     * @return
     */
    public PageInfoResult<PfoSignUp> queryByRecruitmentIdWithPage(PageInfoResult pageParameter, PfoSignUp signUp, OrderParameter orderObj, Long recruitmentId) {
        PageHelper.startPage(pageParameter.getCurrPage(), pageParameter.getPageSize());
        Map<String, String> orderMap = new HashMap<>();

        if (orderObj != null) {
            HashMap<String, String> sortMap = new HashMap<>();
            if (orderObj.getOrderKey() != null) {
                String[] keys = orderObj.getOrderKey().split(",");
                String[] types = orderObj.getOrderType().split(",");
                for (int i = 0; i < keys.length; i++) {
                    if ("dt".equals(keys[i])) {
                        sortMap.put("deliver_time", types[i]);
                    }
                }
            }

            for (Map.Entry<String, String> entry: sortMap.entrySet()) {
                String key = "pfo_sign_up." + entry.getKey();
                orderMap.put(key, entry.getValue());
            }
        }


        List<PfoSignUp> signUps = pfoSignUpMapper.queryByRecruitmentIdWithPage(signUp, orderMap, recruitmentId);
        PageInfo<PfoSignUp> pageInfo = new PageInfo<>(signUps);

        List<PfoSignUp> signUpList = pageInfo.getList();
        pageParameter.setTotalSize(pageInfo.getTotal());
        pageParameter.setDataList(signUpList);

        return pageParameter;
    }

    /**
     * 根据用户(主键)获取报名表信息
     * @param pageParameter
     * @param signUp
     * @param orderObj
     * @return
     */
    @Override
    public PageInfoResult<PfoSignUp> queryByUserIdWithPage(PageInfoResult pageParameter, PfoSignUp signUp, OrderParameter orderObj, Integer userId) {
        PageHelper.startPage(pageParameter.getCurrPage(), pageParameter.getPageSize());

        Map<String, String> orderMap = new HashMap<>();
        if (orderObj != null) {
            Map<String, String> sortMap = new HashMap<>();
            if (orderObj.getOrderKey() != null) {
                String[] keys = orderObj.getOrderKey().split(",");
                String[] types = orderObj.getOrderType().split(",");
                for (int i = 0; i < keys.length; i++) {
                    if ("deliver_time".equals(keys[i])) {
                        sortMap.put("deliver_time", types[i]);
                    }
                }
            }

            for (Map.Entry<String, String> entry : sortMap.entrySet()) {
                String key = "pfo_sign_up." + entry.getKey();
                orderMap.put(key, entry.getValue());
            }
        }

        List<PfoSignUp> signUps = pfoSignUpMapper.queryByUserIdWithPage(signUp, orderMap, userId);
        PageInfo<PfoSignUp> pageInfo = new PageInfo<>(signUps);

        List<PfoSignUp> signUpList = pageInfo.getList();
        pageParameter.setTotalSize(pageInfo.getTotal());
        pageParameter.setDataList(signUpList);
        return pageParameter;
    }


    /**
     * 根据主键删除报名信息
     * @param signUpId
     * @return
     */
    @Override
    public int deleteByPrimaryKey(Long signUpId) {
        return pfoSignUpMapper.deleteByPrimaryKey(signUpId);
    }

    @Override
    public int updateByPrimaryKey(PfoSignUp record) {
        return pfoSignUpMapper.updateByPrimaryKey(record);
    }

    /**
     * 添加报名信息
     * @param signUp
     * @return
     */
    @Override
    public int addSignUp(PfoSignUp signUp) {
        return pfoSignUpMapper.insert(signUp);
    }

    /**
     * key:recruitment_id
     * value:1
     * key:signUpCount
     * value:3
     * key:recruitment_id
     * value:4
     * key:signUpCount
     * value:3
     * key:recruitment_id
     * value:2
     * key:signUpCount
     * value:2
     * key:recruitment_id
     * value:5
     * key:signUpCount
     * value:1
     * 获取报名表中每个招聘信息的报名人数(并按招聘信息的发布时间排序)  推荐算法
     * @return
     */
    public Map<String, Integer> queryByRecruitmentIdWithCount() {
        HashMap<String, Integer> hashMap = new HashMap<>();
        List<Map<String, Integer>> mapList = pfoSignUpMapper.queryByRecruitmentIdWithCount();
        System.out.println("mapList的大小：" + mapList.size());
        for (Map<String, Integer> map : mapList) {
            String recruitmentId = null;
            Integer count = null;
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                if ("recruitment_id".equals(entry.getKey())) {
                    recruitmentId = new String(String.valueOf(entry.getValue()));
                }
                if ("signUpCount".equals(entry.getKey())) {
                    count = new Integer(String.valueOf(entry.getValue()));
                }
                hashMap.put(recruitmentId, count);
            }

        }

        return hashMap;
    }

}
