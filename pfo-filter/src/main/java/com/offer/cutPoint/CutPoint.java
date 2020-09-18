package com.offer.cutPoint;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Created by YMJ on 2019-10-17.
 */
@Component
//定义切点的地方（切点:连接点与通知关联的地方）
public class CutPoint {
    @Pointcut("execution(public * com.offer.service.*.*(..))")//切入每一个Service方法中
    public void AllPointCut(){
    }
}
