package com.offer.service;

import com.offer.pojo.PfoRecommend;
import com.offer.pojo.PfoRecommendExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Describe recommend推荐信息业务类
 * @created ipromisemr
 * @time: 2019/9/16 10:38
 **/
@Component
public interface RecommendService {
    /**
     * 获取所有推荐信息
     * @return
     */
    List<PfoRecommend> queryAllRecommend();

    /**
     * 根据任意条件查询Recommend信息
     * @param recommendId
     * @return
     */
    PfoRecommend queryRecommend(Integer recommendId);

    /**
     * 根据主键进行修改推荐信息
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(PfoRecommend record);

    /**
     * 添加推荐信息
     * @param pfoRecommend
     * @return
     */
    int addPfoRecommend(PfoRecommend pfoRecommend);

    /**
     * 根据主键删除推荐信息
     * @param recommendId
     * @return
     */
    int deleteByPrimaryKey(Integer recommendId);
}
