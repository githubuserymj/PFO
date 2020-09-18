package com.offer.service;

import com.offer.pojo.PfoFeedback;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface FeedbackService {
    /**
     * 查询全部反馈信息
     * @return
     */
    List<PfoFeedback> queryAllFeedbacks();
    /**
     * 根据id删除反馈信息
     */
    int deleteById(Long feedbackId);
    /**
     * 添加反馈信息
     */
    int addFeedback(PfoFeedback pfoFeedback);
}
