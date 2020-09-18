package com.offer.mapper;

import com.offer.dta.PageInfoResult;
import com.offer.pojo.PfoCompany;
import com.offer.pojo.PfoCompanyExample;
import com.offer.pojo.PfoPaper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface PfoCompanyMapper {
    int countByExample(PfoCompanyExample example);

    int deleteByExample(PfoCompanyExample example);

    int deleteByPrimaryKey(Long companyId);

    int insert(PfoCompany record);

    int insertSelective(PfoCompany record);

    List<PfoCompany> selectByExampleWithBLOBs(PfoCompanyExample example);

    List<PfoCompany> selectByExample(PfoCompanyExample example);

//    /**
//     * 根据公司获取试卷列表
//     * @param pageParameter
//     * @param ids
//     * @param company
//     * @return
//     */
//    PageInfoResult<PfoPaper> queryPaperListByCompany(PageInfoResult pageParameter, String ids, PfoCompany company);

    PfoCompany selectByPrimaryKey(Long companyId);

    int updateByExampleSelective(@Param("record") PfoCompany record, @Param("example") PfoCompanyExample example);

    int updateByExampleWithBLOBs(@Param("record") PfoCompany record, @Param("example") PfoCompanyExample example);

    int updateByExample(@Param("record") PfoCompany record, @Param("example") PfoCompanyExample example);

    int updateByPrimaryKeySelective(PfoCompany record);

    int updateByPrimaryKeyWithBLOBs(PfoCompany record);

    int updateByPrimaryKey(PfoCompany record);

    //自定义接口
    List<PfoCompany> queryCompany(PfoCompany pfoCompany);
}