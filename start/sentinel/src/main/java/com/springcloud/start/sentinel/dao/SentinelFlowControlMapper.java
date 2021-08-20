package com.springcloud.start.sentinel.dao;

import java.util.List;

import com.springcloud.start.sentinel.entity.po.SentinelFlowControl;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * sentinel流量控制规则 Mapper 接口
 * </p>
 *
 * @author liangjinquan
 * @since 2021-06-22
 */
public interface SentinelFlowControlMapper extends BaseMapper<SentinelFlowControl> {

	/**
	 * 根据服务名称查询流量控制规则
	 * @param projectName
	 * @return
	 */
	List<SentinelFlowControl> queryByProjectName(@Param("projectName")String projectName);
}
