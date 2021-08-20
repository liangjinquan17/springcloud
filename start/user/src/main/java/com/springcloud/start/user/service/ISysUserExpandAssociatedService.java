package com.springcloud.start.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.springcloud.start.user.entity.po.SysUserExpandAssociated;
import com.springcloud.start.user.enums.UserExpandEnum;

import java.util.List;

/**
 * <p>
 * 用户属性拓展表 服务类
 * </p>
 *
 * @author liangjinquan
 * @since 2021-07-30
 */
public interface ISysUserExpandAssociatedService extends IService<SysUserExpandAssociated> {

    /**
     * 更新用户拓展信息
     * @param userId                用户id
     * @param applicationIds        拓展信息id集合
     * @param userExpandEnum        拓展类型
     * @param createdBy             创建人
     * @return
     */
    boolean updateUserExpandAssociated(Long userId, List<String> applicationIds, UserExpandEnum userExpandEnum, String createdBy);

    /**
     * 查询用户的拓展信息
     * @param userId                用户id
     * @param service               实现IQueryUserExpandAssociated
     * @param <T>
     * @return
     */
    <T, Service extends IQueryUserExpandAssociated<T>> List<T> queryUserExpandAssociated(Long userId, Service service);
}
