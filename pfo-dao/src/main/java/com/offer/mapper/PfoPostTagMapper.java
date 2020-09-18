package com.offer.mapper;

import com.offer.pojo.PfoPostTag;
import com.offer.pojo.PfoPostTagExample;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface PfoPostTagMapper {
    int countByExample(PfoPostTagExample example);

    int deleteByExample(PfoPostTagExample example);

    int deleteByPrimaryKey(Long id);

    //添加标签与帖子关联记录
    int insert(PfoPostTag record);

    //根据帖子id删除帖子所有标签
    @Delete("delete from pfo_post_tag where post_id=#{postId}")
    int deletePostTagsByPostId(Long postId);

    int insertSelective(PfoPostTag record);

    List<PfoPostTag> selectByExample(PfoPostTagExample example);

    PfoPostTag selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PfoPostTag record, @Param("example") PfoPostTagExample example);

    int updateByExample(@Param("record") PfoPostTag record, @Param("example") PfoPostTagExample example);

    int updateByPrimaryKeySelective(PfoPostTag record);

    int updateByPrimaryKey(PfoPostTag record);
}