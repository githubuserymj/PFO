package com.offer.util;

/**
 * Created by YMJ on 2019-09-18.
 */
import com.offer.util.javaCompiler.CustomCallable;
import com.offer.util.javaCompiler.RunInfo;
//import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Java编译工具类
 */
public class JavaCompilerUtil {
    //这里用一个线程是因为防止System.out输出内容错乱
    private static ExecutorService pool = Executors.newFixedThreadPool(1);

    /**
     * 向getRunInfo方法传入源代码即可
     * @param javaSourceCode
     * @return 编译结果
     */
    public static RunInfo getRunInfo(String javaSourceCode) {
        RunInfo runInfo;
        CustomCallable compilerAndRun = new CustomCallable(javaSourceCode);
        Future<RunInfo> future = pool.submit(compilerAndRun);
        //方案1
        try {
            runInfo = future.get();
        } catch (Exception e) {
            e.printStackTrace();
            //代码编译或者运行超时
            runInfo = new RunInfo();
            runInfo.setTimeOut(true);
        }
        return runInfo;
    }

}