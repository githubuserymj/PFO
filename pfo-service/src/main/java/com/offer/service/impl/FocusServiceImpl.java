package com.offer.service.impl;

import com.offer.mapper.PfoFocusMapper;
import com.offer.pojo.PfoFocus;
import com.offer.service.FocusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by YMJ on 2019-09-11.
 */
@Service
public class FocusServiceImpl implements FocusService {
    @Autowired
    PfoFocusMapper pfoFocusMapper;

    @Override
    public List<PfoFocus> queryFocus(PfoFocus pfoFocus) {
        return pfoFocusMapper.queryFocus(pfoFocus);
    }

    @Override
    public int deleteFocus(PfoFocus pfoFocus) {
        return pfoFocusMapper.deleteFocus(pfoFocus);
    }

    @Override
    public int addFocus(PfoFocus pfoFocus) {
        return pfoFocusMapper.insertSelective(pfoFocus);
    }
}
