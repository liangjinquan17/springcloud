package com.springcloud.start.sentinel.dao;

import java.util.List;

import com.springcloud.start.sentinel.entity.po.SentinelDegradeRule;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * sentinel熔断降级规则 Mapper 接口
 * </p>
 *
 * @author liangjinquan
 * @since 2021-06-22
 */
public interface SentinelDegradeRuleMapper extends BaseMapper<SentinelDegradeRule> {

	/**
	 * 根据服务名称查询熔断降级规则
	 * @param projectName
	 * @return
	 */
	List<SentinelDegradeRule> queryByProjectName(@Param("projectName")String projectName);
	
	/**
	 * 根据服务名称查询熔断降级规则
	 * @param projectNames
	 * @return
	 */
	List<SentinelDegradeRule> batchQueryByProjectNames(@Param("projectNames")List<String> projectNames);
}
