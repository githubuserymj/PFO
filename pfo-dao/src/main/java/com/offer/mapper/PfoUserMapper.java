package com.offer.mapper;

import com.offer.pojo.PfoUser;
import com.offer.pojo.PfoUserExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@Mapper
public interface PfoUserMapper {
    int countByExample(PfoUserExample example);

    int deleteByExample(PfoUserExample example);

    int deleteByPrimaryKey(Long userId);

    int insert(PfoUser record);

    int insertSelective(PfoUser record);

    List<PfoUser> selectByExample(PfoUserExample example);

    PfoUser selectByPrimaryKey(Long userId);

    int updateByExampleSelective(@Param("record") PfoUser record, @Param("example") PfoUserExample example);

    int updateByExample(@Param("record") PfoUser record, @Param("example") PfoUserExample example);

    int updateByPrimaryKeySelective(PfoUser record);

    int updateByPrimaryKey(PfoUser record);

    //自定义接口
    List<PfoUser> queryUsers(PfoUser pfoUser);
    //手机号找回密码
    String queryUserPwd(String userPhone);
    //手机号修改密码
    int updateUserPwd(@Param("userPhone") String userPhone,@Param("newUserPwd") String newUserPwd);
}