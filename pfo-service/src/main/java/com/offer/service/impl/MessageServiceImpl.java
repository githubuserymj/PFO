package com.offer.service.impl;

import com.offer.mapper.PfoMessageMapper;
import com.offer.pojo.PfoMessage;
import com.offer.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by YMJ on 2019-09-14.
 */
@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    PfoMessageMapper pfoMessageMapper;
    @Override
    public List<PfoMessage> queryMessage(PfoMessage message) {
        return pfoMessageMapper.queryPfoMessage(message);
    }

    @Override
    public int addMessage(PfoMessage message) {
        message.setMessageType("1");//消息的类型默认设置为，即普通用户间的消息
        long currentTimeMillis = System.currentTimeMillis();
        Timestamp nowTime = new Timestamp(currentTimeMillis);//满足条件当前时间存入消息添加时间
        message.setDeliverTime(nowTime);
        return pfoMessageMapper.insertSelective(message);
    }

    @Override
    public int updateMessage(PfoMessage message) {
        return pfoMessageMapper.updateByPrimaryKeySelective(message);
    }

    @Override
    public int deleteMessage(Long messageId) {
        return pfoMessageMapper.deleteByPrimaryKey(messageId);
    }
}
