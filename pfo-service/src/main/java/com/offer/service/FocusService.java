package com.offer.service;

import com.offer.pojo.PfoFocus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by YMJ on 2019-09-11.
 */
@Component
public interface FocusService {
    /**
     *根据userId查询其所关注的用户
     * @param pfoFocus
     * @return
     */
    List<PfoFocus> queryFocus(PfoFocus pfoFocus);

    /**
     * @param pfoFocus 删除关注记录
     * @return
     */
    int deleteFocus(PfoFocus pfoFocus);

    /**
     * 增加用户关注
     * @param pfoFocus
     * @return
     */
    int addFocus(PfoFocus pfoFocus);
}
