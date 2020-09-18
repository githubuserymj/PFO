package com.offer.service;

import com.offer.pojo.PfoNews;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Describe News推送消息业务类
 * @created ipromisemr
 * @time: 2019/9/16 11:23
 **/
@Component
public interface NewsService {
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

    /**
     * 添加推送消息
     * @param pfoNews
     * @return
     */
    int addNews(PfoNews pfoNews);

    /**
     * 根据pfoNews参数更新推送消息
     * @param pfoNews
     * @return
     */
    int updateNews(PfoNews pfoNews);

    /**
     * 根据主键删除消息
     * @param newsId
     * @return
     */
    int deleteByPrimaryKey(Integer newsId);
}
