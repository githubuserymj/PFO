package com.offer.mapper;

import com.offer.pojo.PfoTopic;
import com.offer.pojo.PfoTopicExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface PfoTopicMapper {
    int countByExample(PfoTopicExample example);

    int deleteByExample(PfoTopicExample example);

    int deleteByPrimaryKey(Integer topicId);

    int insert(PfoTopic record);

    int insertSelective(PfoTopic record);

    List<PfoTopic> selectByExample(PfoTopicExample example);

    //一对多查询，根据主题ID查询出所有相关的帖子信息
    List<PfoTopic> queryTopicWithPosts(Integer topicId);

    PfoTopic selectByPrimaryKey(Integer topicId);

    int updateByExampleSelective(@Param("record") PfoTopic record, @Param("example") PfoTopicExample example);

    int updateByExample(@Param("record") PfoTopic record, @Param("example") PfoTopicExample example);

    int updateByPrimaryKeySelective(PfoTopic record);

    int updateByPrimaryKey(PfoTopic record);
}