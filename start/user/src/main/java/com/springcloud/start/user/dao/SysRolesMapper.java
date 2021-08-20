package com.springcloud.start.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springcloud.start.user.entity.po.SysRoles;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author liangjinquan
 * @since 2021-07-30
 */
public interface SysRolesMapper extends BaseMapper<SysRoles> {

    /**
     * 通过id集合查询角色列表
     * @param ids
     * @return
     */
    List<SysRoles> getByIds(@Param("ids") List<Long> ids);
}
