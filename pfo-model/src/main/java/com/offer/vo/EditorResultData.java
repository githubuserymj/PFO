package com.offer.vo;

import java.util.Arrays;

public class EditorResultData {
    private Integer errno; //错误代码，0 表示没有错误。
    private String[] data; //已上传的图片路径

    public EditorResultData() {
    }

    public EditorResultData(String[] data) {
        this.errno = 0;
        this.data = data;
    }

    public Integer getErrno() {
        return errno;
    }

    public void setErrno(Integer errno) {
        this.errno = errno;
    }

    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "EditorResultData{" +
                "errno=" + errno +
                ", data=" + Arrays.toString(data) +
                '}';
    }
}
