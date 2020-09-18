package com.offer.controller;

import com.offer.pojo.PfoTag;
import com.offer.service.TagService;
import com.offer.vo.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("tag")
public class TagController {

    @Autowired
    TagService tagService;

    /**
     * 展示所有标签信息
     * @param resultData
     * @return
     */
    @RequestMapping("showAllTags")
    @ResponseBody
    //显示数据
    public ResultData queryAllTags(@RequestAttribute(value = "resultData",required = false) ResultData resultData){
        if(null == resultData) {
            resultData = new ResultData();
        } else {
            // 删除失败
            if(resultData.getCode() == 4) {
                return resultData;
            }
        }
        List<PfoTag> tagList = tagService.queryAllTags();
        if (null == tagList || tagList.size() ==0){
            resultData.setCode(3);
            resultData.setMessage("查无数据");
        }else{
            resultData.setCode(0);
            System.out.println("标签名："+tagList.get(0).getTagName());
            resultData.setData(tagList);
        }
        return resultData;
    }

    /**
     * 根据ID删除标签信息
     * @param model
     * @param tagId
     * @return
     */
    @RequestMapping("deleteById")
    //@ResponseBody
    public String deleteById(Model model, Long tagId){
        int affectedRow = tagService.deleteById(tagId);
        ResultData resultData = new ResultData();
        if (affectedRow > 0){
            resultData.setCode(0);
        }else{
            resultData.setCode(4);
            resultData.setData(tagService.queryAllTags());
            resultData.setMessage("删除失败");
        }
        model.addAttribute("resultData", resultData);
        System.out.println(resultData);
        return "forward:showAllTags";
    }

    /**
     * 修改标签
     * @param pfoTag
     * @return
     */
    @RequestMapping("updateById")
    @ResponseBody
    public ResultData updateById(PfoTag pfoTag){
        int affectedRow = tagService.updateById(pfoTag);
        ResultData resultData = new ResultData();
        if (affectedRow > 0) {
            resultData.setCode(0);
            resultData.setMessage("修改成功");
        } else {
            resultData.setCode(5);
            resultData.setMessage("修改失败");
        }
        return resultData;
    }

    /**
     * 添加标签
     * @param pfoTag
     * @return
     */
    @RequestMapping("addTag")
    @ResponseBody
    public ResultData addTag(PfoTag pfoTag){
        int affectedRow = tagService.addTag(pfoTag);
        ResultData resultData = new ResultData();
        if (affectedRow > 0){
            resultData.setCode(0);
            resultData.setMessage("添加成功");
        }else{
            resultData.setCode(6);
            resultData.setMessage("添加失败");
        }
        return resultData;
    }
}
