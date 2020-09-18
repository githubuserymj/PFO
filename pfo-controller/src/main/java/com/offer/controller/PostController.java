package com.offer.controller;

import com.offer.dta.PageInfoResult;
import com.offer.pojo.*;
import com.offer.service.ItemRepository;
import com.offer.service.PostService;
import com.offer.vo.ResultData;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("post")
public class PostController {
    //获取当前日期
    Date date = new Date();
    @Autowired
    PostService postService;

    /**
     * 展示所有帖子
     * @param resultData
     * @return
     */
    @RequestMapping("showAllPosts")
    @ResponseBody
    //显示数据
    public ResultData queryAllPosts(@RequestAttribute(value = "resultData",required = false) ResultData resultData){
        if(null == resultData) {
            resultData = new ResultData();
        } else {
            // 删除失败
            if(resultData.getCode() == 4) {
                return resultData;
            }
        }
        List<PfoPost> postList = postService.queryAllPosts();
        if (null == postList || postList.size() ==0){
            resultData.setCode(3);
            resultData.setMessage("查无数据");
        }else{
            resultData.setCode(0);
           // System.out.println("发帖时间："+postList.get(0).getDeliverTime());
            resultData.setData(postList);
        }
        Iterator<PfoPost> iterator = postList.iterator();
        while (iterator.hasNext()) {
            PfoPost pfoPost = iterator.next();
           // System.out.println("+++++++++++"+pfoPost.getPostTitle());
            itemRepository.save(pfoPost);
        }

        return resultData;
    }

    @RequestMapping("ccc")
    @ResponseBody
    public ResultData queryByScanner(String str) {
        /*deleteIndex();
        createIndex();
        queryAllPosts(null);*/
        List<Long> postIds = new ArrayList<Long>();
        List<PfoPostRequired> postRequiredList = new ArrayList<>();
        String queryString = "美团工作";//搜索关键字
        QueryStringQueryBuilder builder = new QueryStringQueryBuilder(str);
        Iterable<PfoPost> searchResult = itemRepository.search(builder);
        Iterator<PfoPost> iterator = searchResult.iterator();
        while (iterator.hasNext()) {
            PfoPost pfoPosts = iterator.next();
            //System.out.println("==========="+pfoPosts.getPostId());
            postIds.add(pfoPosts.getPostId());
        }
        for (int i = 0; i < postIds.size(); i++) {
            //System.out.println(postIds.get(i));
            List<PfoPostRequired> postRequireds = postService.queryRequiredByPostId(postIds.get(i));
            //System.out.println("tagList数组大小"+postRequireds.get(0).getTagList().size());
            if(postRequireds!=null && postRequireds.size()>0){
                postRequiredList.add(postRequireds.get(0));
            }

        }
        ResultData resultData = new ResultData();

        if (null == postRequiredList || postRequiredList.size() ==0){
            resultData.setCode(3);
            resultData.setMessage("查无数据");
        }else{
            resultData.setCode(0);
            resultData.setMessage("查询成功");
            // System.out.println("发帖时间："+postList.get(0).getDeliverTime());
            resultData.setData(postRequiredList);
        }
        return resultData;


        //System.out.println("这是哈哈哈哈哈0"+postRequiredList.get(0).getPostTitle());
    }

    /**
     * 根据ID删除帖子
     * @param postId
     * @return
     */
    @RequestMapping("deleteById")
    //@ResponseBody
    public ResultData deleteById(Long postId){
       // System.out.println(date);
        int affectedRow = postService.deleteById(postId);
        ResultData resultData = new ResultData();
        if(affectedRow > 0){
            resultData.setCode(0);
            resultData.setMessage("删除帖子成功");
        } else {
            resultData.setCode(4);
            //resultData.setData(postService.queryAllPosts());
            resultData.setMessage("删除失败");
        }
        //System.out.println(resultData);
        //return "/../hello";
        //return "forward:showAllPosts";
        return resultData;
    }


    /**
     * 根据ID修改帖子信息
     * @param pfoPost
     * @return
     */
    @RequestMapping("updateById")
    @ResponseBody
    public ResultData updateById(PfoPost pfoPost){
        //获取系统当前时间，插入到对象数组
        date = new Date();
        pfoPost.setDeliverTime(date);
        pfoPost.setViewCount(0);
        pfoPost.setCommentCount(0);
        pfoPost.setoCoin(0);
        pfoPost.setFavorCount(0);
        int affectedRow = postService.updateById(pfoPost);
        //System.out.println("=================="+pfoPost.getPostTitle());
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
     * 添加帖子
     * @param pfoPost
     * @return
     */
    @RequestMapping("addPost")
    @ResponseBody
    public ResultData addPost(PfoPost pfoPost){
        //获取系统当前时间，插入到对象数组
        date=new Date();
        //System.out.println("++++++++++++++++++++++"+date);
        pfoPost.setDeliverTime(date);
        //System.out.println("++++++++++++++++++++++"+pfoPost.getDeliverTime());
        pfoPost.setViewCount(0);
        pfoPost.setCommentCount(0);
        pfoPost.setoCoin(0);
        pfoPost.setFavorCount(0);
        //将富文本编辑器中的内容主体转换为十六进制数据转义表示
       /* System.out.println("======="+pfoPost.getPostBody());
        String temp = HtmlUtils.htmlEscapeHex(pfoPost.getPostBody());
        pfoPost.setPostBody(temp);
        System.out.println("======="+temp);*/
        int affectedRow = postService.addPost(pfoPost);
        ResultData resultData = new ResultData();
        //System.out.println("帖子ID回填:"+pfoPost.getPostId());
        if (affectedRow > 0){
            resultData.setCode(0);
            resultData.setMessage("添加成功");
            resultData.setData(pfoPost.getPostId());
        }else{
            resultData.setCode(6);
            resultData.setMessage("添加失败");
        }
        return resultData;
    }

    /**
     * 添加帖子与标签的关联记录
     * @param pfoPostTag
     * @return
     */
    @RequestMapping("addPostTags")
    @ResponseBody
    public ResultData addPostTags(PfoPostTag pfoPostTag){
        int affectedRow = postService.addPostTags(pfoPostTag);
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

    /**
     * 根据帖子id查询它对应的所有标签
     * @param postId
     * @return
     */
    @RequestMapping("queryAllTagsByPostId")
    @ResponseBody
    public ResultData queryAllTagsByPostId(Long postId){
        ResultData resultData = new ResultData();
        List<PfoTag> tagList = postService.queryAllTagsByPostId(postId);
        if (null == tagList || tagList.size() ==0){
            resultData.setCode(3);
            resultData.setMessage("查无数据");
        }else{
            resultData.setCode(0);
            resultData.setMessage("查询成功");
           // System.out.println("标签名："+tagList.get(0).getTagName());
            resultData.setData(tagList);
        }
        return resultData;
    }

    /**
     * 根据标签ID查询它的所有帖子
     * @param tagId
     * @return
     */
    @RequestMapping("queryAllPostsByTagId")
    @ResponseBody
    public ResultData queryAllPostsByTagId(Long tagId){
        ResultData resultData = new ResultData();
        List<PfoPost> postList = postService.queryAllPostsByTagId(tagId);
        if (null == postList || postList.size() ==0){
            resultData.setCode(3);
            resultData.setMessage("查无数据");
        }else{
            resultData.setCode(0);
            resultData.setMessage("查询成功");
          //  System.out.println("帖子内容："+postList.get(0).getPostBody());
            resultData.setData(postList);
        }
        return resultData;
    }

    /**
     * 查询所有帖子的所有标签(去重)
     * @return
     */
    @RequestMapping("queryAllTagsWithPosts")
    @ResponseBody
    public ResultData queryAllTagsWithPosts(){
        ResultData resultData = new ResultData();
        List<PfoTag> tagList = postService.queryAllTagsWithPosts();
        if (null == tagList || tagList.size() ==0){
            resultData.setCode(3);
            resultData.setMessage("查无数据");
        }else{
            resultData.setCode(0);
            resultData.setMessage("查询成功");
           // System.out.println("标签名："+tagList.get(0).getTagName());
            resultData.setData(tagList);
        }
        return resultData;
    }


    /**
     * 根据用户ID查询她发布的所有帖子
     * @param userId
     * @return
     */
    @RequestMapping("queryAllPostsByUserId")
    @ResponseBody
    public ResultData queryAllPostsByUserId(Long userId){
        ResultData resultData = new ResultData();
        List<PfoPost> postList = postService.queryAllPostsByUserId(userId);
        if (null == postList || postList.size() ==0){
            resultData.setCode(3);
            resultData.setMessage("查无数据");
        }else{
            resultData.setCode(0);
            resultData.setMessage("查询成功");
           // System.out.println("帖子内容："+postList.get(0).getPostBody());
            resultData.setData(postList);
        }
        return resultData;
    }

    /**
     * 根据主题ID查询所有帖子
     * @param topicId
     * @return
     */
    @RequestMapping("queryAllPostsByTopicId")
    @ResponseBody
    public ResultData queryAllPostsByTopicId(Integer topicId){
        ResultData resultData = new ResultData();
        List<PfoPost> postList = postService.queryAllPostsByTopicId(topicId);
        if (null == postList || postList.size() ==0){
            resultData.setCode(3);
            resultData.setMessage("查无数据");
        }else{
            resultData.setCode(0);
            resultData.setMessage("查询成功");
         //   System.out.println("帖子内容："+postList.get(0).getPostBody());
            resultData.setData(postList);
        }
        return resultData;
    }

    /**
     * 分页展示所有帖子信息
     * @param pageParameter
     * @param model
     * @return
     */
    @RequestMapping("pageInfo")
    @ResponseBody
    public PageInfoResult<PfoPost> queryByPage(PageInfoResult pageParameter, Model model){
        PageInfoResult<PfoPost> page = postService.queryAllPosts(pageParameter);
        model.addAttribute("pageInfo",page);
        return page;
    }

    /**
     * 自定义的查询字段
     * @return
     */
    @RequestMapping("queryByRequired")
    @ResponseBody
    public ResultData queryByRequired(){
        ResultData resultData = new ResultData();
        List<PfoPostRequired> postList = postService.queryByRequired(null);
        if (null == postList || postList.size() ==0){
            resultData.setCode(3);
            resultData.setMessage("查无数据");
        }else{
            resultData.setCode(0);
            resultData.setMessage("查询成功");
           // System.out.println("帖子标题："+postList.get(0).getPostTitle());
            resultData.setData(postList);
        }
        return resultData;
    }

    /**
     * 根据postId查询需要字段
     * @param postId
     * @return
     */
    @RequestMapping("queryRequiredByPostId")
    @ResponseBody
    public ResultData queryRequiredByPostId(Long postId){
        ResultData resultData = new ResultData();
        List<PfoPostRequired> postList = postService.queryRequiredByPostId(postId);
        if (null == postList || postList.size() ==0){
            resultData.setCode(3);
            resultData.setMessage("查无数据");
        }else{
            resultData.setCode(0);
            resultData.setMessage("查询成功");
            // System.out.println("帖子标题："+postList.get(0).getPostTitle());
            resultData.setData(postList);
        }
        return resultData;
    }

    /**
     * 自定义查询排序(最新发布，最多评论，最多回复)
     * @param example
     * @param order
     * @return
     */
    @RequestMapping("queryByRequiredOrder")
    @ResponseBody
    public ResultData queryByRequiredOrder(PfoPostExample example,Integer order){
        ResultData resultData = new ResultData();
        List<PfoPostRequired> postList = postService.queryByRequiredOrder(example,order);
        if (null == postList || postList.size() ==0){
            resultData.setCode(3);
            resultData.setMessage("查无数据");
        }else{
            resultData.setCode(0);
            resultData.setMessage("查询成功");
           // System.out.println("发布时间+++++++++++："+postList.get(0).getDeliverTime());
            resultData.setData(postList);
        }
        return resultData;
    }

    /**
     * 自定义分页展示所有帖子信息
     * @param pageParameter
     * @param model
     * @return
     */
    @RequestMapping("pageInfoRequired")
    @ResponseBody
    public PageInfoResult<PfoPostRequired> queryByPageRequired(PageInfoResult pageParameter, Model model) throws ParseException {
        PageInfoResult<PfoPostRequired> page = postService.queryAllPostsRequired(pageParameter);
        model.addAttribute("pageInfoRequired",page);
        //System.out.println("啊手动阀手动阀手动阀数组大小"+page.getDataList().get(0).getTagList().size());
        return page;
    }

    /**
     * 自定义分页展示所有帖子信息
     * @param pageParameter
     * @param model
     * @return
     */
    @RequestMapping("pageInfoRequiredOrder")
    @ResponseBody
    public PageInfoResult<PfoPostRequired> queryByPageRequiredOrder(PageInfoResult pageParameter,Integer order, Model model) throws ParseException {
        PageInfoResult<PfoPostRequired> page = postService.queryAllPostsRequiredOrder(pageParameter,order);
        model.addAttribute("pageInfoRequired",page);
        return page;
    }



    /**
     * 根据帖子id查询帖子信息
     * @param postId
     * @return
     */
    @RequestMapping("queryPostByPostId")
    @ResponseBody
    public ResultData queryPostByPostId(Long postId){
        ResultData resultData = new ResultData();
        List<PfoPost> postList = postService.queryPostByPostId(postId);
        if (null == postList || postList.size() ==0){
            resultData.setCode(3);
            resultData.setMessage("查无数据");
        }else{
            resultData.setCode(0);
            resultData.setMessage("查询成功");
           // System.out.println("帖子内容："+postList.get(0).getPostBody());
            resultData.setData(postList);
        }
        return resultData;
    }


    /**
     * 根据标签id查询需要字段
     * @param tagId
     * @return
     */
    @RequestMapping("queryRequiredByTagId")
    @ResponseBody
    public ResultData queryRequiredByTagId(Long tagId){
        ResultData resultData = new ResultData();
        List<PfoPostRequired> postRequireds = postService.queryRequiredByTagId(tagId);
        if (null == postRequireds || postRequireds.size() ==0){
            resultData.setCode(3);
            resultData.setMessage("查无数据");
        }else{
            resultData.setCode(0);
            resultData.setMessage("查询成功");
          //  System.out.println("帖子内容："+postRequireds.get(0).getPostTitle());
            resultData.setData(postRequireds);
        }
        return resultData;
    }


    /**
     * 根据主题id显示需要字段
     * @param topicId
     * @return
     */
    @RequestMapping("queryRequiredByTopicId")
    @ResponseBody
    public ResultData queryRequiredByTopicId(Integer topicId){
        ResultData resultData = new ResultData();
        List<PfoPostRequired> postRequireds = postService.queryRequiredByTopicId(topicId);
        if (null == postRequireds || postRequireds.size() ==0){
            resultData.setCode(3);
            resultData.setMessage("查无数据");
        }else{
            resultData.setCode(0);
            resultData.setMessage("查询成功");
           // System.out.println("帖子内容："+postRequireds.get(0).getPostTitle());
            resultData.setData(postRequireds);
        }
        return resultData;
    }


    /**
     * 根据标签id显示所有需要信息分页展示
     * @param pageParameter
     * @param tagId
     * @param model
     * @return
     * @throws ParseException
     */
    @RequestMapping("queryRequiredByTagIdOrder")
    @ResponseBody
    public PageInfoResult<PfoPostRequired> queryRequiredByTagIdOrder(PageInfoResult pageParameter,Long tagId, Model model) throws ParseException {
        PageInfoResult<PfoPostRequired> page = postService.queryRequiredByTagIdOrder(pageParameter,tagId);
        model.addAttribute("pageInfoRequired",page);
        return page;
    }


    /**
     * 根据主题id显示所有需要信息分页展示
     * @param pageParameter
     * @param topicId
     * @param model
     * @return
     * @throws ParseException
     */
    @RequestMapping("queryRequiredByTopicIdOrder")
    @ResponseBody
    public PageInfoResult<PfoPostRequired> queryRequiredByTopicIdOrder(PageInfoResult pageParameter,Integer topicId, Model model) throws ParseException {
        PageInfoResult<PfoPostRequired> page = postService.queryRequiredByTopicIdOrder(pageParameter,topicId);
        model.addAttribute("pageInfoRequired",page);
        return page;
    }

//断点
    /**
     *创建索引
     */
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @RequestMapping("aaa")
    @ResponseBody
    public void createIndex() {
        elasticsearchTemplate.createIndex(PfoPost.class);
    }

    /**
     * 删除索引
     */
    @RequestMapping("bbb")
    @ResponseBody
    public void deleteIndex() {
        elasticsearchTemplate.deleteIndex(PfoPost.class);
    }

    @Autowired
    private ItemRepository itemRepository;
    /**
     * 根据用户输入数据进行帖子内容模糊查询
     * @param scanner
     * @return
     */
    @RequestMapping("queryRequiredByScanner")
    @ResponseBody
    public ResultData queryRequiredByScanner(String scanner){
        ResultData resultData = new ResultData();
        List<PfoPostRequired> postRequireds = postService.queryRequiredByScanner(scanner);
        if (null == postRequireds || postRequireds.size() ==0){
            resultData.setCode(3);
            resultData.setMessage("查无数据");
        }else{
            resultData.setCode(0);
            resultData.setMessage("查询成功");
          //  System.out.println("帖子内容："+postRequireds.get(0).getPostTitle());
            resultData.setData(postRequireds);
        }
        return resultData;
    }


    /**
     * 根据用户输入数据进行帖子内容模糊查询分页
     * @param pageParameter
     * @param scanner
     * @param model
     * @return
     * @throws ParseException
     */
    @RequestMapping("queryRequiredByScannerOrder")
    @ResponseBody
    public PageInfoResult<PfoPostRequired> queryRequiredByScannerOrder(PageInfoResult pageParameter,String  scanner, Model model) throws ParseException {
        PageInfoResult<PfoPostRequired> page = postService.queryRequiredByScannerOrder(pageParameter,scanner);
        model.addAttribute("pageInfoRequired",page);
        return page;
    }



    /**
     * 根据ID将帖子浏览量+1
     * @param postId
     * @return
     */
    @RequestMapping("updateViewCountByPostId")
    @ResponseBody
    public ResultData updateViewCountByPostId(Long postId){
        int affectedRow = postService.updateViewCountByPostId(postId);
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
     * 根据试题ID将帖子浏览量+1
     * @param questionId
     * @return
     */
    @RequestMapping("updateViewCountByQuestionId")
    @ResponseBody
    public ResultData updateViewCountByQuestionId(Long questionId){
        int affectedRow = postService.updateViewCountByQuestionId(questionId);
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
     * 根据帖子ID将帖子评论量+1
     * @param postId
     * @return
     */
    @RequestMapping("updateCommentCountByPostId")
    @ResponseBody
    public ResultData updateCommentCountByPostId(Long postId){
        int affectedRow = postService.updateCommentCountByPostId(postId);
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
     * 根据试题ID将帖子评论量+1
     * @param QuestionId
     * @return
     */
    @RequestMapping("updateCommentCountByQuestionId")
    @ResponseBody
    public ResultData updateCommentCountByQuestionId(Long QuestionId){
        int affectedRow = postService.updateCommentCountByQuestionId(QuestionId);
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
     * 根据帖子ID将点赞数+1
     * @param postId
     * @return
     */
    @RequestMapping("updateFavorCountByPostIdUp")
    @ResponseBody
    public ResultData updateFavorCountByPostIdUp(Long postId){
        int affectedRow = postService.updateFavorCountByPostIdUp(postId);
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
     * 根据帖子ID将点赞数-1
     * @param postId
     * @return
     */
    @RequestMapping("updateFavorCountByPostIdDown")
    @ResponseBody
    public ResultData updateFavorCountByPostIdDown(Long postId){
        int affectedRow = postService.updateFavorCountByPostIdDown(postId);
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
     * 根据帖子id删除帖子所有标签
     * @param postId
     * @return
     */
    @RequestMapping("deletePostTagsByPostId")
    @ResponseBody
    public ResultData deletePostTagsByPostId(Long postId){
        // System.out.println(date);
        int affectedRow = postService.deletePostTagsByPostId(postId);
        ResultData resultData = new ResultData();
        if(affectedRow > 0){
            resultData.setCode(0);
            resultData.setMessage("删除成功");
            deleteById(postId);
        } else {
            resultData.setCode(4);
            //resultData.setData(postService.queryAllPosts());
            resultData.setMessage("删除失败");
        }
        return resultData;
    }


}
