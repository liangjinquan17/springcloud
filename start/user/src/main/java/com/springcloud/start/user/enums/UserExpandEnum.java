package com.springcloud.start.user.enums;

/**
 * 用户拓展信息枚举类
 */
public enum UserExpandEnum {

    USER_ROLE(1, "用户角色"),
    USER_AUTHORITY(2, "用户权限");

    UserExpandEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
