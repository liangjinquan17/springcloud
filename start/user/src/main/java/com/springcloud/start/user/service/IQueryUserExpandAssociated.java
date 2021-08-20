package com.springcloud.start.user.service;

import com.springcloud.start.user.enums.UserExpandEnum;

import java.util.List;

public interface IQueryUserExpandAssociated<T> {

    /**
     * 通过id查询集合
     * @param ids
     * @return
     */
    List<T> getByIds(List<String> ids);

    /**
     * 用于鉴别类型
     * @return
     */
    UserExpandEnum getUserExpandEnum();
}
