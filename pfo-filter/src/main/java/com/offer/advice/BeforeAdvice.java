package com.offer.advice;

import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by YMJ on 2019-10-17.
 */
@Aspect
@Component
//切点方法执行前调用
public class BeforeAdvice {
    @Autowired
    WriteAspectInfo writeAspectInfo;
    @Before("com.offer.cutPoint.CutPoint.AllPointCut()6")
    public void beforeMethod(JoinPoint point){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String nowDateTime = simpleDateFormat.format(date);
        Object[]args = point.getArgs();//获取参数
        StringBuffer stringBuffer = new StringBuffer();
        System.out.println("\n时间："+nowDateTime);
        System.out.println("方法:"+ point.getSignature().getName()+"开始执行...\n参数：");
        stringBuffer.append("\n时间："+nowDateTime+"\n方法："+ point.getSignature().getName()+"开始执行...\n参数：");
        for(Object arg:args){
            System.out.println(arg.toString());
            stringBuffer.append(arg.toString()+"\n");
        }
        writeAspectInfo.writeFile(stringBuffer.toString());
    }
}
