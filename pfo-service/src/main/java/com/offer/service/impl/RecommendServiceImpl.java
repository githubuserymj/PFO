package com.offer.service.impl;

import com.offer.mapper.PfoRecommendMapper;
import com.offer.mapper.PfoRecruitmentMapper;
import com.offer.pojo.PfoRecommend;
import com.offer.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Describe
 * @created ipromisemr
 * @time: 2019/9/17 15:16
 **/
@Service
public class RecommendServiceImpl implements RecommendService {

    @Autowired
    private PfoRecommendMapper pfoRecommendMapper;

    /**
     * 获取所有推荐信息
     * @return
     */
    @Override
    public List<PfoRecommend> queryAllRecommend() {
        return pfoRecommendMapper.queryAllRecommend();
    }

    /**
     * 任意条件获取推送信息
     * @param recommendId
     * @return
     */
    @Override
    public PfoRecommend queryRecommend(Integer recommendId) {
        return pfoRecommendMapper.selectByPrimaryKey(recommendId);
    }

    /**
     * 根据主键修改推送信息（管理员）
     * @param record
     * @return
     */
    @Override
    public int updateByPrimaryKeySelective(PfoRecommend record) {
        return pfoRecommendMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 添加推送消息（管理员）
     * @param pfoRecommend
     * @return
     */
    @Override
    public int addPfoRecommend(PfoRecommend pfoRecommend) {
        return pfoRecommendMapper.insertSelective(pfoRecommend);
    }

    /**
     * 根据主键删除推送信息（管理员）
     * @param recommendId
     * @return
     */
    @Override
    public int deleteByPrimaryKey(Integer recommendId) {
        return pfoRecommendMapper.deleteByPrimaryKey(recommendId);
    }
}
