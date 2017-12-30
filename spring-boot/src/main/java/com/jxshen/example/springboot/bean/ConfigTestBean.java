package com.jxshen.example.springboot.bean;

import org.springframework.stereotype.Component;

public class ConfigTestBean {

    private String desc;
    private String remark;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "ConfigTestBean{" +
                "desc='" + desc + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
