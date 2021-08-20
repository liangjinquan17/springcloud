package com.springcloud.start.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springcloud.start.user.entity.po.SysUserExpandAssociated;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户属性拓展表 Mapper 接口
 * </p>
 *
 * @author liangjinquan
 * @since 2021-07-30
 */
public interface SysUserExpandAssociatedMapper extends BaseMapper<SysUserExpandAssociated> {

    /**
     * 根据用户id查询用户拓展详情
     * @param userId        用户id
     * @return
     */
    SysUserExpandAssociated getByUserId(@Param("userId") Long userId);
}
