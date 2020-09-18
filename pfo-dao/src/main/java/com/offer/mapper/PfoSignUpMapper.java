package com.offer.mapper;

import com.offer.pojo.*;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface PfoSignUpMapper {
    int countByExample(PfoSignUpExample example);

    int deleteByExample(PfoSignUpExample example);

    int deleteByPrimaryKey(Long signUpId);

    /**
     * 添加报名表
     * @param record
     * @return
     */
    int insert(PfoSignUp record);

    int insertSelective(PfoSignUp record);

    List<PfoSignUp> selectByExample(PfoSignUpExample example);

    PfoSignUp selectByPrimaryKey(Long signUpId);

    int updateByExampleSelective(@Param("record") PfoSignUp record, @Param("example") PfoSignUpExample example);

    int updateByExample(@Param("record") PfoSignUp record, @Param("example") PfoSignUpExample example);

    int updateByPrimaryKeySelective(PfoSignUp record);

    /**
     * 根据主键修改报名表信息
     * @param record
     * @return
     */
    int updateByPrimaryKey(PfoSignUp record);

    /**
     * 获取所有报名信息
     * @return
     */
    List<PfoSignUp> querySignUp();

    /**
     * 任意条件查询报名表
     * @param pfoSignUp
     * @return
     */
    List<PfoSignUp> querySignUp(PfoSignUp pfoSignUp);

    /**
     * 分页查询  单表
     * @param signUp
     * @param orderMap
     * @return
     */
    List<PfoSignUp> querySignUpWithPage(@Param("signUp") PfoSignUp signUp, @Param("orderMap") Map orderMap);

    /**
     * 多条件分页查询报名表 单表
     * @param pfoSignUp
     * @param orderMap
     * @return
     */
    List<PfoSignUp> queryAllSignUpWithPage(@Param("signUp") PfoSignUp pfoSignUp, @Param("orderMap") Map orderMap);

    /**
     * 根据招聘获取报名表(关联用户表)，含分页
     * @return
     */
    List<PfoSignUp> queryByRecruitmentIdWithPage(@Param("signUp") PfoSignUp pfoSignUp, @Param("orderMap") Map orderMap, @Param("recruitmentId") Long recruitmentId);

    /**
     * 根据用户获取报名表(关联用户表)，含分页
     * @param pfoSignUp
     * @param orderMap
     * @return
     */
    List<PfoSignUp> queryByUserIdWithPage(@Param("signUp") PfoSignUp pfoSignUp, @Param("orderMap") Map orderMap, @Param("userId") Integer userId);

    /**
     * 获取报名表中每个招聘信息的报名人数 按照报名人数排序
     * @return
     */
    @MapKey("recruitment_id")
    List<Map<String, Integer>> queryByRecruitmentIdWithCount();

}