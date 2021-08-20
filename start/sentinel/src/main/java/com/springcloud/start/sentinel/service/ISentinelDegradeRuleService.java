package com.springcloud.start.sentinel.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.springcloud.start.sentinel.entity.po.SentinelDegradeRule;

/**
 * <p>
 * sentinel熔断降级规则 服务类
 * </p>
 *
 * @author liangjinquan
 * @since 2021-06-22
 */
public interface ISentinelDegradeRuleService extends IService<SentinelDegradeRule> {

	/**
	 * 查询所有流量控制规则
	 * @return
	 */
	List<SentinelDegradeRule> queryAll();
	
	/**
	 * 根据服务名称查询熔断降级规则
	 * @param projectName
	 * @return
	 */
	List<SentinelDegradeRule> queryByProjectName(String projectName);
	
	/**
	 * 根据服务名称批量查询熔断降级规则
	 * @param projectNames
	 * @return
	 */
	List<SentinelDegradeRule> batchQueryByProjectNames(List<String> projectNames);
}
