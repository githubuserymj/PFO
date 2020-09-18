package com.offer.controller;

import com.offer.dta.OrderParameter;
import com.offer.dta.PageInfoResult;
import com.offer.dta.SpecialPracticeKeys;
import com.offer.pojo.PfoExercised;
import com.offer.pojo.PfoQuestion;
import com.offer.pojo.PfoTag;
import com.offer.service.QuestionService;
import com.offer.vo.ResultData;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("question")
public class QuestionController {
    @Autowired
    QuestionService questionService;

    /*
     * 查询全部试题
     *
     * 注解@RequestAttribute可以被用于访问
     * 由过滤器或拦截器创建的、
     * 预先存在的请求属性
     * */
    @RequestMapping("showAllQuestions")
    @ResponseBody
    public ResultData queryAllQuestions(@RequestAttribute(value= "resultData", required = false) ResultData resultData){
        if(null == resultData) {
            resultData = new ResultData();
        } else {
            if(resultData.getCode() == 3) {
                return resultData;
            }
        }

        List<PfoQuestion> questionList = questionService.queryAllQuestion();

        if(null == questionList || questionList.size() == 0){
            resultData.setCode(3);
            resultData.setMessage("查无数据");
        }else {
            resultData.setCode(0);
            resultData.setData(questionList);
        }

        return resultData;
    }

    /*
     * 分页查询全部试题
     * */
    @RequestMapping("queryAllQuestions")
    @ResponseBody
    public PageInfoResult<PfoQuestion>queryAllQuestions(PageInfoResult pageParameter){
        return questionService.queryAllQuestions(pageParameter);
    }

    /*
     * 多条件分页查询排序所有专项练习题
     * */
    @RequestMapping("queryAllQuestionsByConditions")
    @ResponseBody
    public PageInfoResult<PfoQuestion> queryAllQuestionsByConditions(PageInfoResult pageParameter, PfoQuestion question, OrderParameter orderObj){
        /*按分值升序排序*/
        if(null == orderObj.getOrderKey()){
            orderObj.setOrderKey("weigh");
            orderObj.setOrderType("asc");
        }
        return questionService.queryAllQuestionsByConditions(pageParameter,question,orderObj);
    }

    /**
     * 读取excel中的数据,生成list
     * @param file
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/uploadExcel",method = RequestMethod.POST)
    @ResponseBody
    public String upload(@RequestParam(value="file",required = false) MultipartFile file, HttpServletRequest request, HttpServletResponse response){
        System.out.println(file.getOriginalFilename());
        String result = questionService.readExcelFile(file);
        return result;
    }

    /*
     * 添加试题
     * */
    @RequestMapping("addQuestion")
    @ResponseBody
    public ResultData addQuestion(PfoQuestion question){
        ResultData resultData = new ResultData();

        question.setQuestionContent("content:{\"title\":\"下面属于事务的特性的是？\",\"options\":[{\"alias\":\"A\",\"text\":\"原子性\"},{\"alias\":\"B\",\"text\":\"公开性\"}，{\"alias\":\"C\",\"text\":\"独立性\"}，{\"alias\":\"D\",\"text\":\"私有性\"}]}");
        question.setQuestionType("singleChoice");
        question.setQuestionSubject("Java");
        question.setQuestionLevel("基础");
        question.setStandardTime(1);
        question.setAnswer("A");
        question.setAnswerAnalysis("事务具有原子性");
        question.setWeigh(2);
        int affectRow = questionService.addQuestion(question);

        if(affectRow == 0){
            resultData.setCode(2);
            resultData.setMessage("添加失败");
        }else {
            resultData.setCode(0);
            resultData.setMessage("添加成功");
        }

        return resultData;
    }

    /*
     * 删除试题
     * */
    @RequestMapping("deleteQuestion")
    @ResponseBody
    public ResultData deleteQuestionById(Long questionId){
        ResultData resultData = new ResultData();

        questionId = 4L;
        int affectRow = questionService.deleteQuestionById(questionId);

        if(affectRow == 0){
            resultData.setCode(2);
            resultData.setMessage("删除失败");
        }else {
            resultData.setCode(0);
            resultData.setMessage("删除成功");
        }

        return resultData;
    }

    /*
     * 更新试题
     * */
    @RequestMapping("updateQuestion")
    @ResponseBody
    public ResultData updateById(PfoQuestion pfoQuestion){

        /*测试代码
        pfoQuestion.setQuestionId(5L);
        pfoQuestion.setWeigh(2);
        pfoQuestion.setQuestionLevel("基础");*/
        int affectRow = questionService.updateQuestion(pfoQuestion);
        ResultData resultData = new ResultData();
        if(affectRow > 0){
            resultData.setCode(0);
            resultData.setMessage("修改成功");
        }else {
            resultData.setCode(5);
            resultData.setMessage("修改失败");
        }
        return resultData;
    }

    /*
     * 根据questionId查询试题
     * */
    @RequestMapping("queryQuestionById")
    @ResponseBody
    public ResultData selectById(@Param("questionId") Long questionId){
        ResultData resultData = new ResultData();
        PfoQuestion question = questionService.queryQuestionById(questionId);

        if(null == question){
            resultData.setCode(3);
            resultData.setMessage("查无数据");
        }else {
            resultData.setCode(0);
            resultData.setData(question);
        }

        return resultData;
    }

    /**
     * 查询所有试题标签
     * @return
     */
    @RequestMapping("queryAllQuestionTags")
    @ResponseBody
    public ResultData queryAllQuestionTags(){
        ResultData resultData = new ResultData();
        List<PfoTag> tags = questionService.queryAllQuestionTags();

        if(null == tags || tags.size() == 0){
            resultData.setCode(3);
            resultData.setMessage("查无数据");
        }else {
            resultData.setCode(0);
            resultData.setData(tags);
        }
        return resultData;
    }

    /*
     * 根据questionId查询标签
     * */
    @RequestMapping("queryTagsByQuestionId")
    @ResponseBody
    public ResultData queryTagsByQuestionId(@Param("questionId")Long questionId){
        ResultData resultData = new ResultData();
        List<PfoTag> tags = questionService.queryTagsByQuestionId(questionId);

        if(null == tags || tags.size() == 0){
            resultData.setCode(3);
            resultData.setMessage("查无数据");
        }else {
            resultData.setCode(0);
            resultData.setData(tags);
        }
        return resultData;
    }

    /*
     * 根据标签Id查询试题
     * */
    @RequestMapping("queryQuestionsBytagId")
    @ResponseBody
    public ResultData queryQuestionsByTagId(@Param("tagId")Long tagId){
        ResultData resultData = new ResultData();
        List<PfoQuestion> questions = questionService.queryQuestionsByTagId(tagId);

        if(null == questions || questions.size() == 0){
            resultData.setCode(3);
            resultData.setMessage("查无数据");
        }else {
            resultData.setCode(0);
            resultData.setData(questions);
        }

        return resultData;
    }

    /*
    * 增添做题记录
    * */
    @RequestMapping("addExercised")
    @ResponseBody
    public ResultData addExercised(PfoExercised exercised){
        ResultData resultData = new ResultData();
        //测试数据
        /*exercised.setQuestionId(2L);
        exercised.setUserId(1L);
        exercised.setState(1);
        exercised.setFinsihTime(new Date());
        exercised.setUserAnswer("啊啊啊啊呀呀呀呀呀呀");*/

        int affectRow = questionService.addExercised(exercised);

        if(affectRow == 0){
            resultData.setCode(2);
            resultData.setMessage("添加失败");
        }else {
            resultData.setCode(0);
            resultData.setMessage("添加成功");
        }

        return resultData;
    }

    /*
     * 删除做题记录
     * */
    @RequestMapping("deleteExercised")
    @ResponseBody
    public ResultData deleteExercisedById(Long exercisedId){
        ResultData resultData = new ResultData();

        int affectRow = questionService.deleteExercisedById(exercisedId);

        if(affectRow == 0){
            resultData.setCode(2);
            resultData.setMessage("删除失败");
        }else {
            resultData.setCode(0);
            resultData.setMessage("删除成功");
        }

        return resultData;
    }

    /*
     * 分页查询全部做题记录
     * */
    @RequestMapping("queryAllExercised")
    @ResponseBody
    public PageInfoResult<PfoExercised>queryAllExercised(PageInfoResult pageParameter){
        return questionService.queryAllExercised(pageParameter);
    }

    /**
     * 多条件分页查询排序所有做题记录
     * 状态：1：做对，2：收藏，3：做错
     * 列：url：。。。/?state=1（做对）
     * @param pageParameter
     * @param exercised
     * @param orderObj
     * @return
     */
    @RequestMapping("queryAllExercisedByCondition")
    @ResponseBody
    public PageInfoResult<PfoExercised>queryAllExercisedByCondition(@Param("pageParameter") PageInfoResult pageParameter,@Param("exercised")PfoExercised exercised,@Param("orderObj")OrderParameter orderObj){
        orderObj.setOrderKey("ft");
        orderObj.setOrderType("desc");
        return questionService.queryAllExercised(pageParameter,exercised,orderObj);
    }
}
