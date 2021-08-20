package com.springcloud.start.user.dao;

import com.springcloud.start.user.entity.po.SysAuthority;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 权限表 Mapper 接口
 * </p>
 *
 * @author liangjinquan
 * @since 2021-07-30
 */
public interface SysAuthorityMapper extends BaseMapper<SysAuthority> {

    /**
     * 查询权限列表
     * @param ids           权限id集合
     * @return
     */
    List<SysAuthority> getByIds(@Param("ids") List<Long> ids);
}
