package com.offer.service;

import com.offer.dta.OrderParameter;
import com.offer.dta.PageInfoResult;
import com.offer.pojo.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @Describe
 * @created ipromisemr
 * @time: 2019/9/16 9:28
 **/
@Component
public interface SignUpService {
    /**
     * 获取所有报名信息
     * @return
     */
    List<PfoSignUp> queryAllSignUp();

    /**
     * 任意条件查询报名表
     * @param pfoSignUp
     * @return
     */
    List<PfoSignUp> querySignUp(PfoSignUp pfoSignUp);

    /**
     * 根据招聘获取报名表(关联用户表)
     * @return
     */
    List<PfoSignUp> queryByRecruitmentId(@Param("recruitmentId") Long recruitmentId);

    /**
     * 获取所有用户报名表含分页
     * @param pageParameter
     * @param signUp
     * @param orderObj
     * @return
     */
    PageInfoResult<PfoSignUp> queryAllSignUpWithPage(PageInfoResult pageParameter, PfoSignUp signUp, OrderParameter orderObj);

    PageInfoResult<PfoSignUp> querySignUpWithPage(PageInfoResult pageParameter, PfoSignUp signUp, OrderParameter orderObj);

    /**
     * 获取招聘信息的报名表信息  关联报名表，招聘信息表，用户表，公司表
     * @param pageParameter
     * @param signUp
     * @param orderObj
     * @param recruitmentId
     * @return
     */
    PageInfoResult<PfoSignUp> queryByRecruitmentIdWithPage(PageInfoResult pageParameter, PfoSignUp signUp, OrderParameter orderObj, Long recruitmentId);

    /**
     * 根据用户(主键)获取报名信息
     * @param pageParameter
     * @param signUp
     * @param orderObj
     * @return
     */
    PageInfoResult<PfoSignUp> queryByUserIdWithPage(PageInfoResult pageParameter, PfoSignUp signUp, OrderParameter orderObj, Integer userId);

    /**
     * 根据主键删除
     * @param signUpId
     * @return
     */
    int deleteByPrimaryKey(Long signUpId);

    /**
     * 修改报名表信息
     * @param record
     * @return
     */
    int updateByPrimaryKey(PfoSignUp record);

    int addSignUp(PfoSignUp signUp);

    /**
     * 获取报名表中每条招聘信息的人数 根据人数多到少排名 只获取前10条 招聘推荐
     * @return
     */
    Map<String, Integer> queryByRecruitmentIdWithCount();
}
