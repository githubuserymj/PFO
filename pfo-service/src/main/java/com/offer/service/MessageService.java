package com.offer.service;

import com.offer.pojo.PfoMessage;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by YMJ on 2019-09-14.
 */
@Component
public interface MessageService {
    /**
     * 查询消息
     * @param message 参数为查询条件
     * @return 查询到的消息集合
     */
    List<PfoMessage> queryMessage(PfoMessage message);

    /**
     * 添加消息message
     * @param message
     * @return 添加状态
     */
    int addMessage(PfoMessage message);

    /**
     * 更新消息
     * @param message
     * @return 更新状态
     */
    int updateMessage(PfoMessage message);

    /**
     * 根据消息id删除消息
     * @param messageId
     * @return
     */
    int deleteMessage(Long messageId);
}
