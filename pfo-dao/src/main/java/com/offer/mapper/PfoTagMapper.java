package com.offer.mapper;

import com.offer.pojo.PfoTag;
import com.offer.pojo.PfoTagExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface PfoTagMapper {
    int countByExample(PfoTagExample example);

    int deleteByExample(PfoTagExample example);

    int deleteByPrimaryKey(Long tagId);

    int insert(PfoTag record);

    int insertSelective(PfoTag record);

    //查询所有标签信息
    List<PfoTag> selectByExample(PfoTagExample example);

    //添加标签
    int addTag(PfoTag pfoTag);

    //根据id修改标签
    int updateById(PfoTag pfoTag);

    //根据id删除标签
    int deleteById(Long tagId);

    PfoTag selectByPrimaryKey(Long tagId);

    int updateByExampleSelective(@Param("record") PfoTag record, @Param("example") PfoTagExample example);

    int updateByExample(@Param("record") PfoTag record, @Param("example") PfoTagExample example);

    int updateByPrimaryKeySelective(PfoTag record);

    int updateByPrimaryKey(PfoTag record);
}