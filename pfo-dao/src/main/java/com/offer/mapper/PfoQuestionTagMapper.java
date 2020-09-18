package com.offer.mapper;

import com.offer.pojo.PfoQuestion;
import com.offer.pojo.PfoQuestionTag;
import com.offer.pojo.PfoQuestionTagExample;
import com.offer.pojo.PfoTag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface PfoQuestionTagMapper {
    int countByExample(PfoQuestionTagExample example);

    int deleteByExample(PfoQuestionTagExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PfoQuestionTag record);

    int insertSelective(PfoQuestionTag record);

    List<PfoQuestionTag> selectByExample(PfoQuestionTagExample example);

    PfoQuestionTag selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PfoQuestionTag record, @Param("example") PfoQuestionTagExample example);

    int updateByExample(@Param("record") PfoQuestionTag record, @Param("example") PfoQuestionTagExample example);

    int updateByPrimaryKeySelective(PfoQuestionTag record);

    int updateByPrimaryKey(PfoQuestionTag record);

    /*
    * 根据questionId查询标签
    * */
    List<PfoTag> queryTagsByQuestionId(Long questionId);

    /*
     * 根据标签id查询试题表列
     * */
    List<PfoQuestion> queryQuestionsByTagId(Long tagId);

    /**
     * 查询所有试题标签
     * @return
     */
    List<PfoTag> queryAllQuestionTags();
}