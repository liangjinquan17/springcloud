package com.springcloud.sysadmin.organizationremote.remote;

import java.util.List;

import com.springcloud.sysadmin.organizationremote.entity.DTO.SentinelDegradeRuleDTO;
import com.springcloud.sysadmin.organizationremote.entity.DTO.SentinelFlowControlDTO;

/**
 * sentinel流量控制规则服务接口
 * @author liangjinquan
 *
 */
public interface ISentinelFlowControlServerRemote {
	
	/**
	 * 根据服务名称查询流量控制规则
	 * @param projectName
	 * @return
	 */
	List<SentinelFlowControlDTO> queryFlowControlByProjectName(String projectName);
}
