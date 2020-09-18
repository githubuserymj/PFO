package com.offer.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.offer.dta.OrderParameter;
import com.offer.dta.PageInfoResult;
import com.offer.dta.SpecialPracticeKeys;
import com.offer.mapper.PfoExercisedMapper;
import com.offer.mapper.PfoQuestionMapper;
import com.offer.mapper.PfoQuestionTagMapper;
import com.offer.pojo.PfoExercised;
import com.offer.pojo.PfoQuestion;
import com.offer.pojo.PfoTag;
import com.offer.service.QuestionService;
import com.offer.util.ReadExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    PfoQuestionMapper pfoQuestionMapper;
    @Autowired
    PfoQuestionTagMapper pfoQuestionTagMapper;
    @Autowired
    PfoExercisedMapper pfoExercisedMapper;

    /*
     * 查询全部试题
     * */
    @Override
    public List<PfoQuestion> queryAllQuestion() {
        return pfoQuestionMapper.selectByExample(null);
    }

    /*
     * 分页查询全部试题
     * */
    @Override
    public PageInfoResult<PfoQuestion> queryAllQuestions(PageInfoResult pageParameter) {
        PageHelper.startPage(pageParameter.getCurrPage(),pageParameter.getPageSize());

        List<PfoQuestion> questionList = pfoQuestionMapper.selectByExample(null);
        PageInfo<PfoQuestion> pageInfo = new PageInfo<>(questionList);

        List<PfoQuestion> questions = pageInfo.getList();
        pageParameter.setTotalSize(pageInfo.getTotal());
        pageParameter.setDataList(questions);
        return pageParameter;
    }

    /**
     * 多条件分页查询排序所有专项练习试题
     * @param pageParameter
     * @param question
     * @param orderObj
     * @return
     */
    @Override
    public PageInfoResult<PfoQuestion> queryAllQuestionsByConditions(PageInfoResult pageParameter, PfoQuestion question, OrderParameter orderObj) {
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
                    //可根据题型、试题难度、分值进行多条件排序
                    if ("type".equals(keys[i])) {
                        sortmap.put("question_type", types[i]);
                    } else if ("level".equals(keys[i])) {
                        sortmap.put("question_level", types[i]);
                    } else if ("score".equals(keys[i])) {
                        sortmap.put("weigh", types[i]);
                    }
                }
                for (Map.Entry<String, String> entry : sortmap.entrySet()) {
                    String key = "pfo_question." + entry.getKey();
                    orderMap.put(key, entry.getValue());
                }
            }
        }

        List<PfoQuestion> questions = pfoQuestionMapper.queryAllQuestionsByConditions(question,orderMap);
        PageInfo<PfoQuestion> pageInfo = new PageInfo<>(questions);

        List<PfoQuestion> questionList = pageInfo.getList();
        pageParameter.setTotalSize(pageInfo.getTotal());
        pageParameter.setDataList(questionList);
        return pageParameter;
    }

    /**
     * 读取excel中的数据,生成list
     * @param file
     * @return
     */
    @Override
    public String readExcelFile(MultipartFile file) {
        String result ="";
        //创建处理EXCEL的类
        ReadExcel readExcel=new ReadExcel();
        // 解析excel，获取上传的事件单
        List<PfoQuestion> questionList = readExcel.getExcelInfo(file);
        //至此已经将excel中的数据转换到list里面了,接下来就可以操作list,可以进行保存到数据库,或者其他操作,
        if(questionList != null && !questionList.isEmpty()){
            Iterator<PfoQuestion> questionIterator = questionList.iterator();
            while (questionIterator.hasNext()){
                PfoQuestion question = questionIterator.next();
                System.out.println(question);
                addQuestion(question);
            }
            result = "上传成功";
        }else{
            result = "上传失败";
        }
        return result;
    }

    /*
     * 添加试题
     * */
    @Override
    public int addQuestion(PfoQuestion pfoQuestion) {
        return pfoQuestionMapper.insert(pfoQuestion);
    }

    /*
     * 删除试题
     * */
    @Override
    public int deleteQuestionById(Long questionId) {
        return pfoQuestionMapper.deleteByPrimaryKey(questionId);
    }

    /*
     * 更新试题
     * */
    @Override
    public int updateQuestion(PfoQuestion pfoQuestion) {
        return pfoQuestionMapper.updateByPrimaryKeySelective(pfoQuestion);
    }

    /*
     * 根据questionId查询试题
     * */
    @Override
    public PfoQuestion queryQuestionById(Long questionId) {
        return pfoQuestionMapper.selectByPrimaryKey(questionId);
    }

    /**
     * 查询所有试题标签
     * @return
     */
    @Override
    public List<PfoTag> queryAllQuestionTags() {
        return pfoQuestionTagMapper.queryAllQuestionTags();
    }

    /*
     * 根据试题id查询标签
     * */
    @Override
    public List<PfoTag> queryTagsByQuestionId(Long questionId) {
        return pfoQuestionTagMapper.queryTagsByQuestionId(questionId);
    }

    /*
     * 根据标签id查询试题表列
     * */
    @Override
    public List<PfoQuestion> queryQuestionsByTagId(Long tagId) {
        return pfoQuestionTagMapper.queryQuestionsByTagId(tagId);
    }

    /*
     * 增添做题记录
     * */
    @Override
    public int addExercised(PfoExercised exercised) {
        return pfoExercisedMapper.insert(exercised);
    }

    /*
     * 分页查询全部做题记录
     * */
    @Override
    public PageInfoResult<PfoExercised> queryAllExercised(PageInfoResult pageParameter) {
        PageHelper.startPage(pageParameter.getCurrPage(),pageParameter.getPageSize());

        List<PfoExercised> exercisedList = pfoExercisedMapper.queryAllExercisedWithQuestions();
        PageInfo<PfoExercised> pageInfo = new PageInfo<>(exercisedList);

        List<PfoExercised> exerciseds = pageInfo.getList();
        pageParameter.setTotalSize(pageInfo.getTotal());
        pageParameter.setDataList(exerciseds);
        return pageParameter;
    }

    /*
     * 多条件分页查询排序所有做题记录
     * */
    @Override
    public PageInfoResult<PfoExercised> queryAllExercised(PageInfoResult pageParameter, PfoExercised exercised, OrderParameter orderObj) {
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
                    //可根据状态、完成时间进行多条件排序
                    if ("sta".equals(keys[i])) {
                        sortmap.put("state", types[i]);
                    } else if ("ft".equals(keys[i])) {
                        sortmap.put("finsih_time", types[i]);
                    }
                }
                for (Map.Entry<String, String> entry : sortmap.entrySet()) {
                    String key = "pfo_exercised." + entry.getKey();
                    orderMap.put(key, entry.getValue());
                }
            }
        }

        List<PfoExercised> exerciseds = pfoExercisedMapper.queryAllExercised(exercised,orderMap);

        PageInfo<PfoExercised> pageInfo = new PageInfo<>(exerciseds);

        List<PfoExercised> paperList = pageInfo.getList();
        pageParameter.setTotalSize(pageInfo.getTotal());
        pageParameter.setDataList(paperList);
        return pageParameter;
    }

    /*
     * 删除做题记录
     * */
    @Override
    public int deleteExercisedById(Long exercisedId) {
        return pfoExercisedMapper.deleteByPrimaryKey(exercisedId);
    }


}
