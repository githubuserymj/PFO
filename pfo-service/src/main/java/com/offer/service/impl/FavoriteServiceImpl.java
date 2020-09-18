package com.offer.service.impl;

import com.offer.mapper.PfoFavoriteMapper;
import com.offer.pojo.PfoFavorite;
import com.offer.pojo.PfoFavoriteExample;
import com.offer.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by YMJ on 2019-09-11.
 */
@Service
public class FavoriteServiceImpl implements FavoriteService {
    @Autowired
    PfoFavoriteMapper pfoFavoriteMapper;

    @Override
    public int addFavorite(PfoFavorite pfoFavorite) {
        return pfoFavoriteMapper.insertSelective(pfoFavorite);
    }

    @Override
    public int deleteFavorite(Long favoriteId) {
        return pfoFavoriteMapper.deleteByPrimaryKey(favoriteId);
    }

    @Override
    public List<PfoFavorite> queryFavorite(PfoFavorite pfoFavorite) {
        return pfoFavoriteMapper.queryFavorite(pfoFavorite);
    }
}
