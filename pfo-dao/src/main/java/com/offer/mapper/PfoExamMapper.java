package com.offer.mapper;

import com.offer.dta.OrderParameter;
import com.offer.pojo.PfoExam;
import com.offer.pojo.PfoExamExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface PfoExamMapper {
    int countByExample(PfoExamExample example);

    int deleteByExample(PfoExamExample example);

    int deleteByPrimaryKey(Long examId);

    int insert(PfoExam record);

    int insertSelective(PfoExam record);

    List<PfoExam> selectByExample(PfoExamExample example);

    PfoExam selectByPrimaryKey(Long examId);

    /*根据考试成绩表的id查询考试成绩
    * @prarm examId
    * @result PfoExam
    * */
    PfoExam selectExamById(Long examId);

    /*
    * 多条件关联试卷查询所有成绩信息
    * */
    List<PfoExam>queryAllExamsWithPaperInfo(@Param("exam") PfoExam exam, @Param("orderMap") Map orderMap);

    int updateByExampleSelective(@Param("record") PfoExam record, @Param("example") PfoExamExample example);

    int updateByExample(@Param("record") PfoExam record, @Param("example") PfoExamExample example);

    int updateByPrimaryKeySelective(PfoExam record);

    int updateByPrimaryKey(PfoExam record);
}