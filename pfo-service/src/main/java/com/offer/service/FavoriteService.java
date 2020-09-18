package com.offer.service;

import com.offer.pojo.PfoFavorite;
import com.offer.vo.ResultData;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by YMJ on 2019-09-11.
 */
@Component
public interface FavoriteService {
    /**
     * 用户新增收藏
     * @param pfoFavorite
     * @return
     */
    public int addFavorite(PfoFavorite pfoFavorite);

    /**
     * 用户取消收藏
     * @param favoriteId
     * @return
     */
    public int  deleteFavorite(Long favoriteId);

    /**
     * 查询用户收藏
     * @param pfoFavorite
     * @return
     */
    public List<PfoFavorite> queryFavorite(PfoFavorite pfoFavorite);
}
