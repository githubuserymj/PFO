package com.offer.mapper;

import com.offer.pojo.PfoRecommend;
import com.offer.pojo.PfoRecommendExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface PfoRecommendMapper {
    int countByExample(PfoRecommendExample example);

    int deleteByExample(PfoRecommendExample example);

    /**
     * 根据主键删除推荐信息
     * @param recommendId
     * @return
     */
    int deleteByPrimaryKey(Integer recommendId);

    /**
     * 添加推荐列表
     * @param record
     * @return
     */
    int insert(PfoRecommend record);

    int insertSelective(PfoRecommend record);

    List<PfoRecommend> selectByExample(PfoRecommendExample example);

    PfoRecommend selectByPrimaryKey(Integer recommendId);

    int updateByExampleSelective(@Param("record") PfoRecommend record, @Param("example") PfoRecommendExample example);

    int updateByExample(@Param("record") PfoRecommend record, @Param("example") PfoRecommendExample example);

    /**
     * 根据主键进行修改推荐信息
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(PfoRecommend record);

    int updateByPrimaryKey(PfoRecommend record);

    /**
     * 获取所有推荐信息
     * @return
     */
    List<PfoRecommend> queryAllRecommend();

    /**
     * 根据任意条件查询Recommend信息
     * @param pfoRecommend
     * @return
     */
    List<PfoRecommend> queryRecommend(PfoRecommend pfoRecommend);

}