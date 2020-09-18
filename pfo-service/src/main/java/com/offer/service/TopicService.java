package com.offer.service;

import com.offer.pojo.PfoTopic;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TopicService {
    /**
     * 查询全部主题
     * @return
     */
    List<PfoTopic> queryAllTopics();

    /**
     * 根据主题ID查询同主题的所有帖子
     * @param topicId 帖子id
     * @return
     */
    List<PfoTopic> queryTopicWithPosts(Integer topicId);
}
