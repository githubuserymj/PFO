package com.offer.service.impl;

import com.offer.mapper.PfoTagMapper;
import com.offer.pojo.PfoTag;
import com.offer.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    //导入pfoTagMapper
    @Autowired
    PfoTagMapper pfoTagMapper;
    //重写查找方法
    @Override
    public List<PfoTag> queryAllTags() {
        return pfoTagMapper.selectByExample(null);
    }
    //重写删除方法
    @Override
    public int deleteById(Long tagId) {
        return pfoTagMapper.deleteById(tagId);
    }
    //重写修改方法
    @Override
    public int updateById(PfoTag pfoTag) {
        return pfoTagMapper.updateById(pfoTag);
    }
    //重写添加方法
    @Override
    public int addTag(PfoTag pfoTag) {
        return pfoTagMapper.addTag(pfoTag);
    }
}
