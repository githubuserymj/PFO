package com.offer.advice;

import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by YMJ on 2019-10-17.
 */
@Aspect
@Component
//切点方法执行后调用
public class AfterAdvice {
    @Autowired
    WriteAspectInfo writeAspectInfo;
    @After("com.offer.cutPoint.CutPoint.AllPointCut()")
    public void afterMethod(JoinPoint point){
        System.out.println("方法："+point.getSignature().getName()+"执行完成...\n");
        writeAspectInfo.writeFile("方法："+point.getSignature().getName()+"执行完成...\n");

    }
}
