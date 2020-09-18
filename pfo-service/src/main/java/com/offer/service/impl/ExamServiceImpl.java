package com.offer.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.offer.dta.OrderParameter;
import com.offer.dta.PageInfoResult;
import com.offer.mapper.PfoExamMapper;
import com.offer.mapper.PfoExercisedMapper;
import com.offer.mapper.PfoPaperMapper;
import com.offer.mapper.PfoQuestionMapper;
import com.offer.pojo.*;
import com.offer.service.ExamService;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ExamServiceImpl implements ExamService {
    @Autowired
    PfoExamMapper pfoExamMapper;

    @Autowired
    PfoPaperMapper pfoPaperMapper;

    @Autowired
    PfoQuestionMapper pfoQuestionMapper;

    @Autowired
    PfoExercisedMapper pfoExercisedMapper;
    /*
     * 根据考试成绩表的id关联查询试卷信息与考试成绩
     * @prarm id
     * @result PfoExam
     * */
    @Override
    public PfoExam queryExamById(Long examId) {
        System.out.println(examId);
        return pfoExamMapper.selectExamById(examId);
    }

    /*
     * 通过试卷id查试卷信息
     * 试卷内的试题通过queryQuestionList方法查询
     * */
    @Override
    public PfoPaper queryPaperById(Long paperId) {
        return pfoPaperMapper.selectByPrimaryKey(paperId);
    }

    /*
     * 多条件查询排序分页全部试卷
     * */
    @Override
    public PageInfoResult<PfoPaper> queryAllPapers(PageInfoResult pageParameter, OrderParameter orderObj) {
        PageHelper.startPage(pageParameter.getCurrPage(), pageParameter.getPageSize());

        /*数据库字段名为了安全性不能暴露在前端
         * 自定义标识在后端转换存入map中
         * 字段排序的传入顺序让前段拼接完传入
         * */
        Map<String, String> orderMap = new HashMap<>();
        if (orderObj != null) {
            Map<String, String> sortmap = new HashMap<>();
            if(orderObj.getOrderKey() != null){
                String[] keys = orderObj.getOrderKey().split(",");
                String[] types = orderObj.getOrderType().split(",");
                for (int i = 0; i < keys.length; i++) {
                    //可根据创建时间、开放时间、关闭时间进行多条件排序
                    if ("ct".equals(keys[i])) {
                        sortmap.put("create_time", types[i]);
                    } else if ("ot".equals(keys[i])) {
                        sortmap.put("open_time", types[i]);
                    } else if ("et".equals(keys[i])) {
                        sortmap.put("close_time", types[i]);
                    }
                }
                for (Map.Entry<String, String> entry : sortmap.entrySet()) {
                    String key = "pfo_paper." + entry.getKey();
                    orderMap.put(key, entry.getValue());
                }
            }
        }

        List<PfoPaper> papers = pfoPaperMapper.queryAllPapers(orderObj.getConditions(), orderMap);

        PageInfo<PfoPaper> pageInfo = new PageInfo<>(papers);
        List<PfoPaper> paperList = pageInfo.getList();
        pageParameter.setTotalSize(pageInfo.getTotal());
        pageParameter.setDataList(paperList);
        return pageParameter;
    }

    /*
     * 通过questionId列表获取试题表列一题一页
     * */
    @Override
    public PageInfoResult<PfoQuestion> queryQuestionList(PageInfoResult pageParameter, String ids) {
        PageHelper.startPage(pageParameter.getCurrPage(), pageParameter.getPageSize());

        List<Long> idList = new ArrayList<>();
        String idStr[] = ids.split(",");
        Long id = 0L;
        for (int i = 0; i < idStr.length; i++) {
            id = Long.parseLong(idStr[i]);
            idList.add(id);
        }

        List<PfoQuestion> questionList = pfoQuestionMapper.selectQuestionsByIds(idList);
        PageInfo<PfoQuestion> pageInfo = new PageInfo<>(questionList);

        List<PfoQuestion> questions = pageInfo.getList();
        pageParameter.setTotalSize(pageInfo.getTotal());
        pageParameter.setDataList(questions);
        return pageParameter;
    }

    /*
     * 创建试卷
     * */
    @Override
    public int addPaper(PfoPaper paper) {
        return pfoPaperMapper.insertSelective(paper);
    }

    /*
     * 删除试卷
     * */
    @Override
    public int deletePaperById(Long paperId) {
        return pfoPaperMapper.deleteByPrimaryKey(paperId);
    }

    /*
     * 更新试卷
     * */
    @Override
    public int updatePaper(PfoPaper paper) {
        return pfoPaperMapper.updateByPrimaryKeySelective(paper);
    }

    /*
     * 生成考试成绩
     * */
    @Override
    public int addExamInfo(PfoExam exam) {
        return pfoExamMapper.insert(exam);
    }

    /*
     * 删除考试成绩
     * */
    @Override
    public int deleteExamInfoById(Long examId) {
        return pfoExamMapper.deleteByPrimaryKey(examId);
    }

    /*
     * 多条件分页查询排序所有成绩
     * */
    @Override
    public PageInfoResult<PfoExam> queryAllExamInfo(PageInfoResult pageParameter, PfoExam exam, OrderParameter orderObj) {
        PageHelper.startPage(pageParameter.getCurrPage(), pageParameter.getPageSize());
        /*数据库字段名为了安全性不能暴露在前端
         * 自定义标识在后端转换存入map中
         * 字段排序的传入顺序让前段拼接完传入
         * */

        Map<String, String> orderMap = new HashMap<>();
        if (orderObj != null) {
            Map<String, String> sortmap = new HashMap<>();
            if (orderObj.getOrderKey() != null) {
                String[] keys = orderObj.getOrderKey().split(",");
                String[] types = orderObj.getOrderType().split(",");
                for (int i = 0; i < keys.length; i++) {
                    //可根据分数、开始时间、结束时间、考试用时进行多条件排序
                    if ("s".equals(keys[i])) {
                        sortmap.put("score", types[i]);
                    } else if ("st".equals(keys[i])) {
                        sortmap.put("start_time", types[i]);
                    } else if ("ts".equals(keys[i])) {
                        sortmap.put("total_time", types[i]);
                    } else if ("ft".equals(keys[i])) {
                        sortmap.put("finish_time", types[i]);
                    }
                }
            }
            for (Map.Entry<String, String> entry : sortmap.entrySet()) {
                String key = "pfo_exam." + entry.getKey();
                orderMap.put(key, entry.getValue());
            }
        }

        List<PfoExam> exams = pfoExamMapper.queryAllExamsWithPaperInfo(exam, orderMap);

        PageInfo<PfoExam> pageInfo = new PageInfo<>(exams);

        List<PfoExam> examList = pageInfo.getList();
        pageParameter.setTotalSize(pageInfo.getTotal());
        pageParameter.setDataList(examList);
        return pageParameter;
    }

    /**
     * 判题并生成成绩和做题记录
     * @param sheet
     * @return
     */
    @Override
    public Map<String,Object> generateScore(Map<String, Object> sheet){
        Long paperId = ((Integer)sheet.get("paperId")).longValue();
        Long userId = ((Integer)sheet.get("userId")).longValue();
        String ids = queryPaperById(paperId).getQuestionListId();
        String[] idList = ids.split(",");
        int questionNum = idList.length;
        Map<String,Object> resultMap = new HashMap<>();
        int qCount = 0;
        int score = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm:ss");
        Date st = null;
        Date ft = null;
        Date tt = null;
        try {
            st = sdf.parse((String)sheet.get("startTime"));
            ft = sdf.parse((String)sheet.get("finishTime"));
            tt = sdfTime.parse((String)sheet.get("totalTime"));
        } catch (ParseException e) {
            e.printStackTrace();
        }


        Map<Integer,String> answers = new HashMap<>();

        for (Map.Entry<String, Object> entry : sheet.entrySet()) {
            //System.out.println(entry.getKey() + ":" + entry.getValue());
            if(entry.getKey().contains("Q")){
                String qid = entry.getKey().replace("Q","");
                answers.put(Integer.parseInt(qid),entry.getValue().toString());
                qCount++;

            }

        }

        PfoQuestion question = new PfoQuestion();
        PfoExercised exercised = new PfoExercised();

        int n = 0;
        int correctNum = 0;
        for (Map.Entry<Integer, String> entry : answers.entrySet()) {
            question = pfoQuestionMapper.selectByPrimaryKey(Long.valueOf(entry.getKey()));
            if(question.getAnswer().equals(entry.getValue())){
                correctNum++;
                score += question.getWeigh();
                //状态：1：做对，2：收藏，3：做错
                exercised.setState(1);
            }else {
                //状态：1：做对，2：收藏，3：做错
                exercised.setState(3);
            }

            exercised.setQuestionId(question.getQuestionId());
            exercised.setUserId(userId);
            exercised.setFinsihTime(ft);
            exercised.setUserAnswer(entry.getValue());
            pfoExercisedMapper.insertSelective(exercised);

            n++;
        }

        PfoExam exam = new PfoExam();
        exam.setUserId(userId);
        exam.setPaperId(paperId);
        exam.setStartTime(st);
        exam.setFinishTime(ft);
        exam.setTotalTime(tt);
        exam.setScore(score);

        addExamInfo(exam);

        resultMap.put("examId",queryExamById(exam.getExamId()).getExamId());
        resultMap.put("correctNum",correctNum);
        resultMap.put("questionNum",questionNum);
        resultMap.put("consumingTime",sheet.get("totalTime"));

        return resultMap;
    }
}
