package com.springcloud.core.enums;

/**
 * 状态枚举
 */
public enum StatusEnum {

    USABLE(1, "可用"),
    DISABLED(0, "不可用");

    private Integer status;
    private String description;

    StatusEnum(Integer status, String description) {
        this.status = status;
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
