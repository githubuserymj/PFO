package com.offer.mapper;

import com.offer.pojo.PfoExercised;
import com.offer.pojo.PfoExercisedExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface PfoExercisedMapper {
    int countByExample(PfoExercisedExample example);

    int deleteByExample(PfoExercisedExample example);

    int deleteByPrimaryKey(Long exercisedId);

    int insert(PfoExercised record);

    int insertSelective(PfoExercised record);

    /*
    * 试题表与做题记录关联查询
    * */
    List<PfoExercised> queryAllExercisedWithQuestions();

    /*多条件分页查询排序所有做题记录*/
    List<PfoExercised> queryAllExercised(@Param("exercised")PfoExercised exercised,@Param("orderMap")Map orderMap);

    List<PfoExercised> selectByExampleWithBLOBs(PfoExercisedExample example);

    List<PfoExercised> selectByExample(PfoExercisedExample example);

    PfoExercised selectByPrimaryKey(Long exercisedId);

    int updateByExampleSelective(@Param("record") PfoExercised record, @Param("example") PfoExercisedExample example);

    int updateByExampleWithBLOBs(@Param("record") PfoExercised record, @Param("example") PfoExercisedExample example);

    int updateByExample(@Param("record") PfoExercised record, @Param("example") PfoExercisedExample example);

    int updateByPrimaryKeySelective(PfoExercised record);

    int updateByPrimaryKeyWithBLOBs(PfoExercised record);

    int updateByPrimaryKey(PfoExercised record);
}