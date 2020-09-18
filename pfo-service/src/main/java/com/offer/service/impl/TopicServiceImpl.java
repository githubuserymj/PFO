package com.offer.service.impl;

import com.offer.mapper.PfoTopicMapper;
import com.offer.pojo.PfoTopic;
import com.offer.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {
    @Autowired
    PfoTopicMapper pfoTopicMapper;
    /**
     * 查询所有主题
     * @return
     */
    @Override
    public List<PfoTopic> queryAllTopics() {
        return pfoTopicMapper.selectByExample(null);
    }

    /**
     * 根据主题ID查询同主题的所有帖子
     * @param topicId
     * @return
     */
    @Override
    public List<PfoTopic> queryTopicWithPosts(Integer topicId) {
        return pfoTopicMapper.queryTopicWithPosts(topicId);
    }


}
