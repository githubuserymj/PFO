package com.offer.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by YMJ on 2019-10-17.
 */
@Aspect
@Component
//切点方法正常返回后调用
public class AfterReturningAdvice {
    @Autowired
    WriteAspectInfo writeAspectInfo;
    @AfterReturning("com.offer.cutPoint.CutPoint.AllPointCut()")
    public void beforeMethod(JoinPoint point){
        System.out.println("方法："+point.getSignature().getName()+"正常执行完成...");
        writeAspectInfo.writeFile("方法："+point.getSignature().getName()+"正常执行完成...\n");

    }
}
