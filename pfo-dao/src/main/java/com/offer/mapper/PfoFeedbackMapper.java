package com.offer.mapper;

import com.offer.pojo.PfoFeedback;
import com.offer.pojo.PfoFeedbackExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface PfoFeedbackMapper {
    int countByExample(PfoFeedbackExample example);

    int deleteByExample(PfoFeedbackExample example);

    int deleteByPrimaryKey(Long feedbackId);

    int insert(PfoFeedback record);

    int insertSelective(PfoFeedback record);

    List<PfoFeedback> selectByExampleWithBLOBs(PfoFeedbackExample example);

    //查询全部数据
    List<PfoFeedback> selectByExample(PfoFeedbackExample example);

    //根据id删除反馈信息
    int deleteById(Long Feed);

    //添加反馈信息
    int addFeedback(PfoFeedback pfoFeedback);

    PfoFeedback selectByPrimaryKey(Long feedbackId);

    int updateByExampleSelective(@Param("record") PfoFeedback record, @Param("example") PfoFeedbackExample example);

    int updateByExampleWithBLOBs(@Param("record") PfoFeedback record, @Param("example") PfoFeedbackExample example);

    int updateByExample(@Param("record") PfoFeedback record, @Param("example") PfoFeedbackExample example);

    int updateByPrimaryKeySelective(PfoFeedback record);

    int updateByPrimaryKeyWithBLOBs(PfoFeedback record);

    int updateByPrimaryKey(PfoFeedback record);
}