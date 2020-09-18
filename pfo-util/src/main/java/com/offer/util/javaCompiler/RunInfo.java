package com.offer.util.javaCompiler;

/**
 * Created by YMJ on 2019-09-18.
 */
public class RunInfo {
    //超时标志：true 超时 false：未超时
    private Boolean timeOut;
    //编译耗时(单位ms)
    private Long compilerTakeTime;
    //编译信息
    private String compilerMessage;
    //编译成功标志：true 编译成功 false 编译失败
    private Boolean compilerSuccess;
    //运行耗时(单位ms)
    private Long runTakeTime;
    //运行结果
    private String runResult;
    //运行状态标志：true 运行成功 false 运行失败
    private Boolean runSuccess;

    public Boolean getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(Boolean timeOut) {
        this.timeOut = timeOut;
    }

    public Long getCompilerTakeTime() {
        return compilerTakeTime;
    }

    public void setCompilerTakeTime(Long compilerTakeTime) {
        this.compilerTakeTime = compilerTakeTime;
    }

    public String getCompilerMessage() {
        return compilerMessage;
    }

    public void setCompilerMessage(String compilerMessage) {
        this.compilerMessage = compilerMessage;
    }

    public Boolean getCompilerSuccess() {
        return compilerSuccess;
    }

    public void setCompilerSuccess(Boolean compilerSuccess) {
        this.compilerSuccess = compilerSuccess;
    }

    public Long getRunTakeTime() {
        return runTakeTime;
    }

    public void setRunTakeTime(Long runTakeTime) {
        this.runTakeTime = runTakeTime;
    }

    public String getRunResult() {
        return runResult;
    }

    public void setRunResult(String runResult) {
        this.runResult = runResult;
    }

    public Boolean getRunSuccess() {
        return runSuccess;
    }

    public void setRunSuccess(Boolean runSuccess) {
        this.runSuccess = runSuccess;
    }

    @Override
    public String toString() {
        return "RunInfo{" +
                "timeOut=" + timeOut +
                ", compilerTakeTime=" + compilerTakeTime +
                ", compilerMessage='" + compilerMessage + '\'' +
                ", compilerSuccess=" + compilerSuccess +
                ", runTakeTime=" + runTakeTime +
                ", runResult='" + runResult + '\'' +
                ", runSuccess=" + runSuccess +
                '}';
    }
}
