package com.offer.controller;

import com.offer.util.javaCompiler.RunInfo;
import com.offer.vo.ResultData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.offer.util.JavaCompilerUtil;
/**
 * Created by YMJ on 2019-09-18.
 */
@Controller
public class JavaCompilerController {
    /**
     * 接受源代码字符串后调用工具类进行编译输出
     * @param originCode
     * @return
     */
    @RequestMapping("getJavaCompiler")
    @ResponseBody
    public ResultData getJavaCompiler(String originCode){
        ResultData resultData = new ResultData();
        if(null != originCode && originCode != ""){
            RunInfo runInfo = JavaCompilerUtil.getRunInfo(originCode);
            if(null != runInfo){
                resultData.setCode(0);
                resultData.setData(runInfo);
            }else{
                resultData.setCode(3);
                resultData.setMessage("编译失败");
            }
        }else{
            resultData.setCode(4);
            resultData.setMessage("参数有误");
        }
        return resultData;
    }
}
