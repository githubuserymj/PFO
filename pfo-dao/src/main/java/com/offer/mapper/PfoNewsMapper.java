package com.offer.mapper;

import com.offer.pojo.PfoNews;
import com.offer.pojo.PfoNewsExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface PfoNewsMapper {
    int countByExample(PfoNewsExample example);

    int deleteByExample(PfoNewsExample example);

    /**
     * 根据主键删除消息
     * @param newsId
     * @return
     */
    int deleteByPrimaryKey(Integer newsId);

    /**
     * 添加推送消息
     * @param record
     * @return
     */
    int insert(PfoNews record);

    int insertSelective(PfoNews record);

    List<PfoNews> selectByExampleWithBLOBs(PfoNewsExample example);

    List<PfoNews> selectByExample(PfoNewsExample example);

    PfoNews selectByPrimaryKey(Integer newsId);

    int updateByExampleSelective(@Param("record") PfoNews record, @Param("example") PfoNewsExample example);

    int updateByExampleWithBLOBs(@Param("record") PfoNews record, @Param("example") PfoNewsExample example);

    int updateByExample(@Param("record") PfoNews record, @Param("example") PfoNewsExample example);

    int updateByPrimaryKeySelective(PfoNews record);

    int updateByPrimaryKeyWithBLOBs(PfoNews record);

    /**
     * 根据pfoNews参数更新推送消息
     * @param record
     * @return
     */
    int updateByPrimaryKey(PfoNews record);

    /**
     * 获取所有推送消息
     * @return
     */
    List<PfoNews> queryAllNews();

    /**
     * 根据pfoNews进行任意条件查询
     * @param pfoNews
     * @return
     */
    List<PfoNews> queryNews(PfoNews pfoNews);

}