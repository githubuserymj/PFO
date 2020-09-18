package com.offer.mapper;

import com.offer.dta.OrderParameter;
import com.offer.dta.PageInfoResult;
import com.offer.dta.SpecialPracticeKeys;
import com.offer.pojo.PfoQuestion;
import com.offer.pojo.PfoQuestionExample;
import com.offer.pojo.PfoTag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface PfoQuestionMapper {
    int countByExample(PfoQuestionExample example);

    int deleteByExample(PfoQuestionExample example);

    int deleteByPrimaryKey(Long questionId);

    int insert(PfoQuestion record);

    int insertSelective(PfoQuestion record);

    List<PfoQuestion> selectByExample(PfoQuestionExample example);
    /*
    * 根据试卷表中的id列表查询试题列表
    * */
    List<PfoQuestion> selectQuestionsByIds(List<Long> idList);

    /*
     * 多条件分页查询排序所有专项练习试题
     * */
    List<PfoQuestion> queryAllQuestionsByConditions(@Param("Keys")PfoQuestion Keys, @Param("orderMap")Map orderMap);



    PfoQuestion selectByPrimaryKey(Long questionId);

    int updateByExampleSelective(@Param("record") PfoQuestion record, @Param("example") PfoQuestionExample example);

    int updateByExample(@Param("record") PfoQuestion record, @Param("example") PfoQuestionExample example);

    int updateByPrimaryKeySelective(PfoQuestion record);

    int updateByPrimaryKey(PfoQuestion record);
}