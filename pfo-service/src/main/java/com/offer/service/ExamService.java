package com.offer.service;

import com.offer.dta.OrderParameter;
import com.offer.dta.PageInfoResult;
import com.offer.pojo.PfoExam;
import com.offer.pojo.PfoExercised;
import com.offer.pojo.PfoPaper;
import com.offer.pojo.PfoQuestion;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

@Component
public interface ExamService {

    /*
     * 根据examId查询考试成绩
     * */
    PfoExam queryExamById(Long examId);

    /*
     * 根据paperId查询试卷
     * */
    PfoPaper queryPaperById(Long id);

    /*
     * 多条件分页查询排序所有试卷
     * */
    PageInfoResult<PfoPaper>queryAllPapers(PageInfoResult pageParameter, OrderParameter orderObj);

    /*
    * 根据question_id_list里的id列表查询试题表列
    * */
    PageInfoResult<PfoQuestion> queryQuestionList(PageInfoResult pageParameter,String ids);

    /*
    * 创建试卷
    * */
    int addPaper(PfoPaper paper);

    /*
    * 删除试卷
    * */
    int deletePaperById(Long paperId);

    /*
     * 更新试卷
     * */
    int updatePaper(PfoPaper paper);

    /*
    * 生成考试成绩
    * */
    int addExamInfo(PfoExam exam);

    /*
    * 删除考试成绩
    * */
    int deleteExamInfoById(Long examId);

    /*
    * 多条件分页查询排序所有成绩
    * */
    PageInfoResult<PfoExam>queryAllExamInfo(PageInfoResult pageParameter, PfoExam exam, OrderParameter orderObj);

    /**
     * 判题生成成绩
     * @param sheet
     * @return
     */
    Map<String,Object> generateScore(Map<String,Object> sheet) throws ParseException;

}
