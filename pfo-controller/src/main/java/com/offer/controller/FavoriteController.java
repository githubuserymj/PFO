package com.offer.controller;

import com.offer.pojo.PfoFavorite;
import com.offer.pojo.PfoUser;
import com.offer.service.FavoriteService;
import com.offer.vo.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by YMJ on 2019-09-11.
 */
@Controller
@RequestMapping("favorite")
public class FavoriteController {
    @Autowired
    FavoriteService favoriteService;

    /**
     * 接收参数完成用户收藏
     * @param pfoFavorite
     * @return
     */
    @RequestMapping("addFavorite")
    @ResponseBody
    public ResultData addFavorite(PfoFavorite pfoFavorite, HttpSession session){
        ResultData resultData = new ResultData();
        PfoUser user = (PfoUser) session.getAttribute("user");
        if(null == user){
            resultData.setCode(4);
            resultData.setMessage("请登陆后进行操作");
            return resultData;
        }
        if(!user.getUserId().equals(pfoFavorite.getUserId())){
            resultData.setCode(4);
            resultData.setMessage("当前登录用户与操作所属目标用户不符，无法进行此操作！");
            return resultData;
        }
        ResultData checkFavorite = queryFavorite(pfoFavorite);
        if(checkFavorite.getCode() != 0){
            int insertStatus = favoriteService.addFavorite(pfoFavorite);
            if(insertStatus>0){
                resultData.setCode(0);
            }else{
                resultData.setCode(3);
                resultData.setMessage("收藏失败");
            }
        }else{
            resultData.setCode(4);
            resultData.setMessage("帖子已被收藏");
        }
        return resultData;
    }

    /**
     * 根据id删除用户收藏记录
     * @param favoriteId
     * @return
     */
    @RequestMapping("deleteFavorite")
    @ResponseBody
    public ResultData  deleteFavorite(Long favoriteId,HttpSession session){
        ResultData resultData = new ResultData();
        PfoUser user = (PfoUser) session.getAttribute("user");
        if(null == user){
            resultData.setCode(4);
            resultData.setMessage("请登陆后进行操作");
            return resultData;
        }
        if(null != favoriteId){
            int deleteStatus = favoriteService.deleteFavorite(favoriteId);
            if(deleteStatus>0){
                resultData.setCode(0);
            }else{
                resultData.setCode(3);
                resultData.setMessage("取消收藏失败");
            }
        }else{
            resultData.setCode(4);
            resultData.setMessage("参数有误");
        }

        return resultData;
    }



    /**
     * 查询用户收藏的帖子
     * 查询收藏某帖子的用户
     * @param pfoFavorite
     * @return
     */
    @RequestMapping("queryFavorite")
    @ResponseBody
    public ResultData  queryFavorite(PfoFavorite pfoFavorite){
        ResultData resultData = new ResultData();
        List<PfoFavorite> favoriteList = favoriteService.queryFavorite(pfoFavorite);
        if(null !=favoriteList && favoriteList.size()>0){
            resultData.setCode(0);
            resultData.setData(favoriteList);
        }else{
            resultData.setCode(3);
            resultData.setMessage("查无数据");
        }
        return resultData;
    }
}
