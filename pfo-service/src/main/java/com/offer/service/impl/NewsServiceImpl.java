package com.offer.service.impl;

import com.offer.mapper.PfoNewsMapper;
import com.offer.pojo.PfoNews;
import com.offer.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Describe
 * @created ipromisemr
 * @time: 2019/9/17 16:35
 **/
@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private PfoNewsMapper pfoNewsMapper;

    @Override
    public List<PfoNews> queryAllNews() {
        return pfoNewsMapper.queryAllNews();
    }

    @Override
    public List<PfoNews> queryNews(PfoNews pfoNews) {
        return pfoNewsMapper.queryNews(pfoNews);
    }

    @Override
    public int addNews(PfoNews pfoNews) {
        return pfoNewsMapper.insert(pfoNews);
    }

    @Override
    public int updateNews(PfoNews pfoNews) {
        return pfoNewsMapper.updateByPrimaryKey(pfoNews);
    }

    @Override
    public int deleteByPrimaryKey(Integer newsId) {
        return pfoNewsMapper.deleteByPrimaryKey(newsId);
    }
}
