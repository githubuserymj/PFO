package com.offer.service;

import com.offer.pojo.PfoTag;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TagService {
    /**
     * 查询全部标签
     * @return
     */
    List<PfoTag> queryAllTags();
    /**
     * 根据id删除标签
     */
    int deleteById(Long tagId);
    /**
     * 根据id修改标签
     */
    int updateById(PfoTag pfoTag);
    /**
     * 添加帖子
     */
    int addTag(PfoTag pfoTag);
}
