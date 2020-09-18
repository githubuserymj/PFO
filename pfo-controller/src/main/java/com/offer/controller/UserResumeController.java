package com.offer.controller;

import com.offer.pojo.PfoResume;
import com.offer.service.ResumeService;
import com.offer.util.QiNiuUtil;
import com.offer.vo.ResultData;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by YMJ on 2019-09-11.
 */
@Controller
@RequestMapping("resume")
public class UserResumeController {
    @Autowired
    ResumeService resumeService;

    /**
     * 接收resume参数进行多条件查询
     * @param resume
     * @return
     */
    @RequestMapping("queryResumes")
    @ResponseBody
    public ResultData queryResumes(PfoResume resume){
        ResultData resultData = new ResultData();
        List<PfoResume> resumeList = resumeService.queryResumes(resume);
        if(null == resumeList||resumeList.size() == 0){
            resultData.setCode(3);
            resultData.setMessage("查无数据");
        }else{
            resultData.setCode(0);
            resultData.setData(resumeList);
        }
        return resultData;
    }

    /**
     * 接收内容增加简历
     * @param
     * @return
     */
    @RequestMapping("addResume")
    @ResponseBody
    public ResultData addResume(Long userId, @Param("userResumeTitle") String userResumeTitle, MultipartFile multipartFile) throws IOException {
        ResultData resultData = new ResultData();
        PfoResume resume = new PfoResume();
        if(userId==null||userResumeTitle==null||multipartFile==null){
            resultData.setCode(4);
            resultData.setMessage("参数错误");
            return resultData;
        }
        else{
            resume.setUserId(userId);
            String oldFileName = multipartFile.getOriginalFilename();
            String time = String.valueOf(System.currentTimeMillis());//获取当前系统时间戳
            String newName = time + oldFileName.substring(oldFileName.lastIndexOf("."));//时间戳加文件后缀组成新的文件名
            InputStream inputStream = multipartFile.getInputStream();
            FileInputStream file = (FileInputStream) inputStream;
            String imgPath = "http://"+ QiNiuUtil.uploadToken(file,newName);
            String resumeContext = userResumeTitle+"|"+imgPath;
            resume.setResumeContext(resumeContext);//保存简历标题及图片路径xxx|xxx.jpg格式

            int insertStatus = resumeService.addResume(resume);
            if(insertStatus>0){
                resultData.setCode(0);
            }else{
                resultData.setCode(3);
                resultData.setMessage("简历添加失败");
            }
            return resultData;
        }

    }

    /**
     * 接收参数进行用户简历数据更新
     * @param resume
     * @return
     */
    @RequestMapping("updateResume")
    @ResponseBody
    public ResultData updatePfoUser(PfoResume resume,String resumeImgUrl,MultipartFile multipartFile) throws IOException {
        ResultData resultData = new ResultData();
        if(resume.getResumeId()==null||resume.getResumeContext()==null||multipartFile==null||resume.getUserId()==null){
            resultData.setCode(4);
            resultData.setMessage("参数错误");
            return resultData;
        }else{
            String oldFileName = multipartFile.getOriginalFilename();
            String time = String.valueOf(System.currentTimeMillis());//获取当前系统时间戳
            String newName = time + oldFileName.substring(oldFileName.lastIndexOf("."));//时间戳加文件后缀组成新的文件名
            InputStream inputStream = multipartFile.getInputStream();
            FileInputStream file = (FileInputStream) inputStream;
            String imgPath = "http://"+ QiNiuUtil.uploadToken(file,newName);
            String resumeContext = resume.getResumeContext()+"|"+imgPath;
            resume.setResumeContext(resumeContext);//保存简历标题及图片路径xxx|xxx.jpg格式

            int updateStatus = resumeService.updateResume(resume);
            if(updateStatus>0){
                resultData.setCode(0);
            }else{
                resultData.setCode(3);
                resultData.setMessage("更新失败");
            }
            return resultData;
        }

    }

    /**
     * 接收resumeId进行用户删除
     * @param resumeId
     * @return
     */
    @RequestMapping("deleteResume")
    @ResponseBody
    public ResultData deletePfoUser(Long resumeId){
        ResultData resultData = new ResultData();
        int deleteStatus = resumeService.deleteResume(resumeId);
        if(deleteStatus>0){
            resultData.setCode(0);
        }else{
            resultData.setCode(3);
            resultData.setMessage("删除失败");
        }
        return resultData;
    }
}
