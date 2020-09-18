package com.offer.mapper;

import com.offer.pojo.PfoFocus;
import com.offer.pojo.PfoFocusExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface PfoFocusMapper {
    int countByExample(PfoFocusExample example);

    int deleteByExample(PfoFocusExample example);

    int deleteByPrimaryKey(Long focusId);

    int insert(PfoFocus record);

    int insertSelective(PfoFocus record);

    List<PfoFocus> selectByExample(PfoFocusExample example);

    PfoFocus selectByPrimaryKey(Long focusId);

    int updateByExampleSelective(@Param("record") PfoFocus record, @Param("example") PfoFocusExample example);

    int updateByExample(@Param("record") PfoFocus record, @Param("example") PfoFocusExample example);

    int updateByPrimaryKeySelective(PfoFocus record);

    int updateByPrimaryKey(PfoFocus record);

//    自定义接口
    //查询userId的用户所关注的用户Id集合
    List<PfoFocus> queryFocus(PfoFocus pfoFocus);

    //删除表中pfoFocus记录
    int deleteFocus(PfoFocus pfoFocus);
}