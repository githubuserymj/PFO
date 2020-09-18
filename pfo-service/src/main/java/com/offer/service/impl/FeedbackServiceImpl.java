package com.offer.service.impl;

import com.offer.mapper.PfoFeedbackMapper;
import com.offer.pojo.PfoFeedback;
import com.offer.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    PfoFeedbackMapper pfoFeedbackMapper;
    @Override
    public List<PfoFeedback> queryAllFeedbacks() {
        return pfoFeedbackMapper.selectByExample(null);
    }

    @Override
    public int deleteById(Long feedbackId) {
        return pfoFeedbackMapper.deleteById(feedbackId);
    }

    @Override
    public int addFeedback(PfoFeedback pfoFeedback) {
        return pfoFeedbackMapper.addFeedback(pfoFeedback);
    }
}
