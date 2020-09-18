package com.offer.mapper;

import com.offer.pojo.PfoCompany;
import com.offer.pojo.PfoRecruitment;
import com.offer.pojo.PfoRecruitmentExample;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface PfoRecruitmentMapper {
    int countByExample(PfoRecruitmentExample example);

    int deleteByExample(PfoRecruitmentExample example);

    int deleteByPrimaryKey(Long recruitmentId);

    int insert(PfoRecruitment record);

    int insertSelective(PfoRecruitment record);

    List<PfoRecruitment> selectByExampleWithBLOBs(PfoRecruitmentExample example);

    List<PfoRecruitment> selectByExample(PfoRecruitmentExample example);

    PfoRecruitment selectByPrimaryKey(Long recruitmentId);

    int updateByExampleSelective(@Param("record") PfoRecruitment record, @Param("example") PfoRecruitmentExample example);

    int updateByExampleWithBLOBs(@Param("record") PfoRecruitment record, @Param("example") PfoRecruitmentExample example);

    int updateByExample(@Param("record") PfoRecruitment record, @Param("example") PfoRecruitmentExample example);

    int updateByPrimaryKeySelective(PfoRecruitment record);

    int updateByPrimaryKeyWithBLOBs(PfoRecruitment record);

    int updateByPrimaryKey(PfoRecruitment record);

    /**
     * 获取所有招聘信息
     * @return
     */
    List<PfoRecruitment> queryAllRecruitment();

    /**
     * 获取推荐招聘信息
     * @param idList
     * @return
     */
    List<PfoRecruitment> queryRecruitmentByRecommend(List<Long> idList);

    /**
     * 获取近期招聘信息 单表 20条
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
     * 查看招聘详情  关联公司信息
     * @param recruitmentId
     * @return
     */
    PfoRecruitment queryByRecruitmentId(Integer recruitmentId);

    /**
     * 查看招聘详情  关联公司,用户信息(企业员工/管理员)  公司与用户一对一
     * @param recruitmentId
     * @return
     */
    PfoRecruitment queryByRecruitmentId2(Integer recruitmentId);

    /**
     * 分页查询所有招聘信息 关联公司表
     * @param recruitment
     * @param orderMap
     * @return
     */
    List<PfoRecruitment> queryAllRecruitmentsWithPage(@Param("recruitment") PfoRecruitment recruitment, @Param("orderMap") Map orderMap);

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
     * 招聘消息列表按公司查询  招聘列表页面
     * @param recruitment
     * @param orderMap
     * @return
     */
    List<PfoRecruitment> queryRecruitmentByCompanyWithPage(@Param("recruitment") PfoRecruitment recruitment, @Param("orderMap") Map orderMap, @Param("companyName") String companyName);

    /**
     * 根据公司查询招聘信息
     * @param pfoCompany
     * @return
     */
    List<PfoRecruitment> queryByCompany(@Param("pfoCompany") PfoCompany pfoCompany);

    /**
     * 查看公司招聘信息及报名人数
     * @param companyId
     * @return
     */
    @MapKey("recruitment_id")
    List<Map<Long, Integer>> queryExampleBycompanyId(@Param("companyId") Long companyId);
}