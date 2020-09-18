package com.offer.dta;

import java.util.List;

public class OrderParameter {
    /*排序标识(不允许出现数据库字段名称)*/
    private String orderKey;
    /*排序类型(升序(asc)、降序(desc))，默认升序*/
    private String orderType = "asc";
    /*条件List*/
    private List<String> conditions;

    public String getOrderKey() {
        return orderKey;
    }

    public void setOrderKey(String orderKey) {
        this.orderKey = orderKey;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public List<String> getConditions() {
        return conditions;
    }

    public void setConditions(List<String> conditions) {
        this.conditions = conditions;
    }
}
