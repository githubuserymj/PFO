package com.offer.mapper;

import com.offer.pojo.PfoResume;
import com.offer.pojo.PfoResumeExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface PfoResumeMapper {
    int countByExample(PfoResumeExample example);

    int deleteByExample(PfoResumeExample example);

    int deleteByPrimaryKey(Long resumeId);

    int insert(PfoResume record);

    int insertSelective(PfoResume record);

    List<PfoResume> selectByExampleWithBLOBs(PfoResumeExample example);

    List<PfoResume> selectByExample(PfoResumeExample example);

    PfoResume selectByPrimaryKey(Long resumeId);

    int updateByExampleSelective(@Param("record") PfoResume record, @Param("example") PfoResumeExample example);

    int updateByExampleWithBLOBs(@Param("record") PfoResume record, @Param("example") PfoResumeExample example);

    int updateByExample(@Param("record") PfoResume record, @Param("example") PfoResumeExample example);

    int updateByPrimaryKeySelective(PfoResume record);

    int updateByPrimaryKeyWithBLOBs(PfoResume record);

    int updateByPrimaryKey(PfoResume record);

    //自定义接口
    List<PfoResume> queryResumes(PfoResume resume);
}