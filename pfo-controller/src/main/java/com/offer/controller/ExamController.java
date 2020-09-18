package com.offer.controller;

import com.offer.dta.OrderParameter;
import com.offer.dta.PageInfoResult;
import com.offer.pojo.PfoExam;
import com.offer.pojo.PfoPaper;
import com.offer.pojo.PfoQuestion;
import com.offer.service.ExamService;
import com.offer.vo.ResultData;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("exam")
public class ExamController {
    @Autowired
    ExamService examService;

    /**
     * 通过成绩单Id查询试卷和成绩信息
     * @param examId
     * @return
     */
    @RequestMapping("queryExam")
    @ResponseBody
    public ResultData queryExam(Long examId){
        ResultData resultData = new ResultData();
        /*Long id = 1L;*/

        PfoExam pfoExam = examService.queryExamById(examId);

        if(null == pfoExam){
            resultData.setCode(3);
            resultData.setMessage("查无数据");
        }else {
            resultData.setCode(0);
            resultData.setData(pfoExam);
        }

        return resultData;
    }

    /**
     * 通过试卷id查试卷信息
     * @param model
     * @param paperId
     * @return
     */
    @RequestMapping("/queryPaperById")
    public String queryPaperById(Model model, Long paperId) {
        ResultData resultData = new ResultData();

        PfoPaper paperInfo = examService.queryPaperById(paperId);

        if(null == paperInfo){
            //跳转到错误页面
            resultData.setCode(3);
            resultData.setMessage("查无数据");
            return "/pfo/errorpage/500.html";
        }else {
            model.addAttribute("paperInfo",paperInfo);
            return "/exam/paper_detail.jsp";
        }
    }

    /**
     * 通过questionId列表获取试题表列一题一页(跳转请求)
     * @param model
     * @param pageParameter
     * @param paper
     * @return
     */
    @RequestMapping("queryPaperQuestions")
    public String queryPaperQuestions(Model model,PageInfoResult pageParameter,PfoPaper paper){
        pageParameter.setPageSize(1);
        PageInfoResult<PfoQuestion> question = examService.queryQuestionList(pageParameter,paper.getQuestionListId());
        model.addAttribute("question",question);
        model.addAttribute("ids",paper.getQuestionListId());
        model.addAttribute("paperId",paper.getPaperId());
        return "/exam/question.jsp";
    }

    /**
     * 通过questionId列表获取试题表列一题一页(异步请求)
     * @param pageParameter
     * @param ids
     * @return
     */
    @RequestMapping("nextQuestion")
    @ResponseBody
    public PageInfoResult<PfoQuestion> nextQuestion(@Param("pageParameter") PageInfoResult pageParameter,@Param("ids") String ids){
        pageParameter.setPageSize(1);
        return examService.queryQuestionList(pageParameter,ids);
    }

    /**
     * 创建试卷
     * @param paper
     * @return resultData
     */
    @RequestMapping(value = "addPaper",method = RequestMethod.POST)
    @ResponseBody
    public ResultData addPaper(@RequestBody PfoPaper paper){
        ResultData resultData = new ResultData();

        /*paper.setPaperName("携程2020Java工程师秋招笔试");
        paper.setCreateTime(new Date());
        paper.setQuestionListId("2,3,6,7");*/

        int affectRow = examService.addPaper(paper);

        if(affectRow == 0){
            resultData.setCode(2);
            resultData.setMessage("添加失败");
        }else {
            resultData.setCode(0);
            resultData.setMessage("添加成功");
        }

        return resultData;
    }

    /**
     * 多条件分页查询排序全部试卷
     * @param pageParameter 分页参数
     * @param orderObj 排序和条件参数
     * @return PageInfoResult<PfoPaper>
     */
    @RequestMapping("queryAllPapers")
    @ResponseBody
    public PageInfoResult<PfoPaper>queryAllPapers(@Param("pageParameter")PageInfoResult pageParameter, @Param("orderObj")OrderParameter orderObj){
        pageParameter.setPageSize(10);

        //按创建时间降序
        /*orderObj.setOrderKey("ct");
        orderObj.setOrderType("desc");*/
        return examService.queryAllPapers(pageParameter,orderObj);
    }

    /*
     * 删除试卷
     * */
    @RequestMapping("deletePaper")
    @ResponseBody
    public ResultData deletePaperById(Long paperId){
        ResultData resultData = new ResultData();

        int affectRow = examService.deletePaperById(paperId);

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
     * 更新试卷
     * */
    @RequestMapping("updatePaper")
    @ResponseBody
    public ResultData updatePaperById(PfoPaper paper){

        /*图片存储
        if(null != multipartFile){
            System.out.println("进入");
            String oldName = multipartFile.getOriginalFilename();
            String newName = UUID.randomUUID() + oldName.substring(oldName.lastIndexOf("."));
            File file = new File(newName);
            multipartFile.transferTo(file);
            bookinfo.setBookImg(ConstantValue.BASE_IMG_PATH+newName);
            System.out.println("通过");
        }*/
        //测试代码
        /*paper.setPaperId(3L);
        paper.setPaperName("华为2020Java工程师秋招");
        paper.setCreateTime(new Date());
        paper.setQuestionListId("2,6");*/
        int affectRow = examService.updatePaper(paper);
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
     * 生成考试成绩
     * */
    @RequestMapping("addExam")
    @ResponseBody
    public ResultData addExam(PfoExam exam){
        ResultData resultData = new ResultData();

        int affectRow = examService.addExamInfo(exam);

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
     * 删除考试成绩
     * */
    @RequestMapping("deleteExam")
    @ResponseBody
    public ResultData deleteExamById(Long examId){
        ResultData resultData = new ResultData();

        int affectRow = examService.deleteExamInfoById(examId);

        if(affectRow == 0){
            resultData.setCode(2);
            resultData.setMessage("删除失败");
        }else {
            resultData.setCode(0);
            resultData.setMessage("删除成功");
        }

        return resultData;
    }

    /**
     * 分页多条件查询排序所有成绩
     * @param pageParameter
     * @param exam
     * @param orderObj
     * @return
     */
    @RequestMapping("queryAllExams")
    @ResponseBody
    public PageInfoResult<PfoExam>queryAllExams(@Param("pageParameter") PageInfoResult pageParameter, @Param("exam") PfoExam exam, @Param("orderObj") OrderParameter orderObj){
        //按成绩升序
        orderObj.setOrderKey("s");
        orderObj.setOrderType("desc");
        return examService.queryAllExamInfo(pageParameter,exam,orderObj);
    }

    /**
     * 判题自动生成成绩
     * @param sheet
     * @return
     * @throws ParseException
     */
    @RequestMapping(value = "finishExam",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    @ResponseBody
    public Map<String, Object> submitPaper(@RequestBody Map<String,Object> sheet) throws ParseException {

        return examService.generateScore(sheet);
    }

    /**
     * 答案解析
     * @param model
     * @param examId
     * @param correctNum
     * @param questionNum
     * @param consumingTime
     * @return
     */
    @RequestMapping("answerAnalytic")
    public String queryanswerAnalytic(Model model,@Param("examId") Long examId,
    @Param("correctNum") int correctNum,@Param("questionNum") int questionNum,
    @Param("consumingTime") String consumingTime){
        PfoExam exam = examService.queryExamById(examId);

        model.addAttribute("questionNum",questionNum);
        model.addAttribute("exam",exam);
        model.addAttribute("examId",examId);
        model.addAttribute("correctNum",correctNum);
        model.addAttribute("consumingTime",consumingTime);
        return "/exam/answerAnalytic.jsp";
    }
}
