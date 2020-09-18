package com.offer.service;

import com.offer.dta.OrderParameter;
import com.offer.dta.PageInfoResult;
import com.offer.pojo.PfoCompany;
import com.offer.pojo.PfoExam;
import com.offer.pojo.PfoRecruitment;
import com.offer.pojo.PfoRecruitmentExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @Describe Recruitment招聘信息业务类
 * @created ipromisemr
 * @time: 2019/9/16 9:19
 **/
@Component
public interface RecruitmentService {
    /**
     * 获取所有招聘信息
     * @return
     */
    List<PfoRecruitment> queryAllRecruitment();

    /**
     * 近期招聘信息 单表 前20条
     * @param pfoRecruitment
     * @return
     */
    List<PfoRecruitment> queryRecruitmentRecently(PfoRecruitment pfoRecruitment);

    /**
     * 获取所有招聘信息及关联公司信息
     * @param companyId
     * @return
     */
    List<PfoRecruitment> queryByCompanyId(Integer companyId);

    /**
     * 根据公司查询招聘信息
     * @param pfoCompany
     * @return
     */
    List<PfoRecruitment> queryByCompany(PfoCompany pfoCompany);

    /**
     * 根据主键查看招聘详情 关联公司信息
     * @param recruitmentId
     * @return
     */
    PfoRecruitment queryByRecruitmentId(Long recruitmentId);

    /**
     * 查看招聘详情  关联公司,用户信息(企业员工/管理员)  公司与用户一对一
     * @param recruitmentId
     * @return
     */
    PfoRecruitment queryByRecruitmentId2(Integer recruitmentId);

    /**
     * 多条件分页查询  单表
     * @param pageParameter
     * @param recruitment
     * @param orderObj
     * @return
     */
    PageInfoResult<PfoRecruitment> queryRecruitmentsWithPage(PageInfoResult pageParameter, PfoRecruitment recruitment, OrderParameter orderObj);
    /**
     * 多条件分页查询 多表 关联公司信息
     * @param pageParameter
     * @param recruitment
     * @param orderObj
     * @return
     */
    PageInfoResult<PfoRecruitment> queryAllRecruitmentsWithPage(@Param("pageParameter") PageInfoResult pageParameter, @Param("recruitment") PfoRecruitment recruitment, @Param("orderObj") OrderParameter orderObj);

    PageInfoResult<PfoRecruitment> queryRecruitmentByCompanyWithPage(PageInfoResult pageParameter, PfoRecruitment recruitment, OrderParameter orderObj);

    /**
     * 分页查询  单表
     * @param pageParameter
     * @param recruitment
     * @param orderObj
     * @return
     */
    PageInfoResult<PfoRecruitment> queryByCompanyNameWithPage(PageInfoResult pageParameter, PfoRecruitment recruitment, OrderParameter orderObj, String companyName);

//    /**
//     * 查询日历公司招聘信息
//     * @return
//     */
//    List<PfoRecruitment> queryRecruitmentOnCalendar();
//
    /**
     * 根据主键删除招聘信息
     * @param recruitmentId
     * @return
     */
    int deleteByPrimaryKey(Long recruitmentId);

    /**
     * 修改招聘信息
     * @param record
     * @return
     */
    int updateByPrimaryKey(PfoRecruitment record);

    /**
     * 添加招聘信息
     * @param pfoRecruitment
     * @return
     */
    int addRecruitment(PfoRecruitment pfoRecruitment);

    /**
     * 获取招聘信息报名人数
     * @param recruitmentId
     * @return
     */
    Integer queryCountByRecruitmentId(Integer recruitmentId);

    /**
     * 获取公司招聘人数
     * @param companyId
     * @return
     */
    Integer queryCountByCompanyId(Integer companyId);

    /**
     * 获取推荐招聘信息列表
     * @param ids
     * @return
     */
    List<PfoRecruitment> queryRecruitmentByRecommend(String ids);

    /**
     * 公司查看招聘信息的报名人数  用于公司主页的统计
     * @param companyId
     * @return
     */
    Map<Long, Integer> queryExampleBycompanyId(Long companyId);
}
