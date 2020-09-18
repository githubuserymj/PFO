package com.offer.mapper;

import com.offer.pojo.PfoMessage;
import com.offer.pojo.PfoMessageExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface PfoMessageMapper {
    int countByExample(PfoMessageExample example);

    int deleteByExample(PfoMessageExample example);

    int deleteByPrimaryKey(Long messageId);

    int insert(PfoMessage record);

    int insertSelective(PfoMessage record);

    List<PfoMessage> selectByExampleWithBLOBs(PfoMessageExample example);

    List<PfoMessage> selectByExample(PfoMessageExample example);

    PfoMessage selectByPrimaryKey(Long messageId);

    int updateByExampleSelective(@Param("record") PfoMessage record, @Param("example") PfoMessageExample example);

    int updateByExampleWithBLOBs(@Param("record") PfoMessage record, @Param("example") PfoMessageExample example);

    int updateByExample(@Param("record") PfoMessage record, @Param("example") PfoMessageExample example);

    int updateByPrimaryKeySelective(PfoMessage record);

    int updateByPrimaryKeyWithBLOBs(PfoMessage record);

    int updateByPrimaryKey(PfoMessage record);

    //自定义接口实现
    List<PfoMessage> queryPfoMessage(PfoMessage message);
}