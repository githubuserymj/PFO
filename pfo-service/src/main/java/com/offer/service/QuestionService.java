package com.offer.service;

import com.offer.dta.OrderParameter;
import com.offer.dta.PageInfoResult;
import com.offer.dta.SpecialPracticeKeys;
import com.offer.pojo.PfoExercised;
import com.offer.pojo.PfoQuestion;
import com.offer.pojo.PfoTag;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@Component
public interface QuestionService {
    /*
     * 查询全部试题
     * */
    List<PfoQuestion> queryAllQuestion();

    /*
     * 分页查询全部试题
     * */
    PageInfoResult<PfoQuestion> queryAllQuestions(PageInfoResult pageParameter);

    /*
     * 多条件分页查询排序所有专项练习试题
     * */
    PageInfoResult<PfoQuestion>queryAllQuestionsByConditions(PageInfoResult pageParameter, PfoQuestion question, OrderParameter orderObj);

    /**
     * 读取excel中的数据,生成list
     * @param file
     * @return
     */
    String readExcelFile( MultipartFile file);

    /*
     * 添加试题
     * */
    int addQuestion(PfoQuestion pfoQuestion);

    /*
     * 删除试题
     * */
    int deleteQuestionById(Long questionId);

    /*
     * 更新试题
     * */
    int updateQuestion(PfoQuestion pfoQuestion);

    /*
     * 根据questionId查询试题
     * */
    PfoQuestion queryQuestionById(Long questionId);

    /**
     * 查询所有试题标签
     * @return List<PfoTag>
     */
    List<PfoTag> queryAllQuestionTags();

    /*
     * 根据试题id查询标签
     * */
    List<PfoTag> queryTagsByQuestionId(Long questionId);

    /*
     * 根据标签id查询试题
     * */
    List<PfoQuestion> queryQuestionsByTagId(Long tagId);

    /*
    * 增添做题记录
    * */
    int addExercised(PfoExercised exercised);

    /*
     * 分页查询全部做题记录
     * */
    PageInfoResult<PfoExercised> queryAllExercised(PageInfoResult pageParameter);

    /*
     * 多条件分页查询排序所有做题记录
     * */
    PageInfoResult<PfoExercised>queryAllExercised(PageInfoResult pageParameter, PfoExercised exercised, OrderParameter orderObj);

    /*
    * 删除做题记录
    * */
    int deleteExercisedById(Long exercisedId);


}
