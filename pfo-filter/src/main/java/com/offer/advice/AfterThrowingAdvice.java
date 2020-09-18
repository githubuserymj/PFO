package com.offer.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by YMJ on 2019-10-17.
 */
@Aspect
@Component
//切点方法发生异常时调用
public class AfterThrowingAdvice {
    @Autowired
    WriteAspectInfo writeAspectInfo;
    @AfterThrowing("com.offer.cutPoint.CutPoint.AllPointCut()")
    public void beforeMethod(JoinPoint point){
        System.out.println("方法："+point.getSignature().getName()+"执行过程中发生错误...");
        writeAspectInfo.writeFile("方法："+point.getSignature().getName()+"执行过程中发生错误...\n");

    }
}
