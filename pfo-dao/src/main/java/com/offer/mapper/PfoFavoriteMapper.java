package com.offer.mapper;

import com.offer.pojo.PfoFavorite;
import com.offer.pojo.PfoFavoriteExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface PfoFavoriteMapper {
    int countByExample(PfoFavoriteExample example);

    int deleteByExample(PfoFavoriteExample example);

    int deleteByPrimaryKey(Long favoriteId);

    int insert(PfoFavorite record);

    int insertSelective(PfoFavorite record);

    List<PfoFavorite> selectByExample(PfoFavoriteExample example);

    PfoFavorite selectByPrimaryKey(Long favoriteId);

    int updateByExampleSelective(@Param("record") PfoFavorite record, @Param("example") PfoFavoriteExample example);

    int updateByExample(@Param("record") PfoFavorite record, @Param("example") PfoFavoriteExample example);

    int updateByPrimaryKeySelective(PfoFavorite record);

    int updateByPrimaryKey(PfoFavorite record);

    //自定义接口
    //根据条件查询用户收藏情况
    List<PfoFavorite> queryFavorite(PfoFavorite pfoFavorite);
}