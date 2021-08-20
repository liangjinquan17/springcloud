package com.springcloud.start.sentinel.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.springcloud.start.sentinel.entity.po.SentinelFlowControl;

/**
 * <p>
 * sentinel流量控制规则 服务类
 * </p>
 *
 * @author liangjinquan
 * @since 2021-06-22
 */
public interface ISentinelFlowControlService extends IService<SentinelFlowControl> {
	
	/**
	 * 查询所有流量控制规则
	 * @return
	 */
	List<SentinelFlowControl> queryAll();
	
	/**
	 * 根据服务名称查询流量控制规则
	 * @param projectName
	 * @return
	 */
	List<SentinelFlowControl> queryByProjectName(String projectName);

}
