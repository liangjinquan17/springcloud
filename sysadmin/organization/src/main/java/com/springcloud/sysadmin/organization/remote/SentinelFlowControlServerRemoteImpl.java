package com.springcloud.sysadmin.organization.remote;

import com.alibaba.csp.sentinel.util.function.Supplier;
import com.springcloud.start.sentinel.entity.po.SentinelFlowControl;
import com.springcloud.start.sentinel.service.ISentinelFlowControlService;
import com.springcloud.sysadmin.organizationremote.entity.DTO.SentinelFlowControlDTO;
import com.springcloud.sysadmin.organizationremote.remote.ISentinelFlowControlServerRemote;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author liangjinquan
 */
@DubboService(version = "1.0.1")
public class SentinelFlowControlServerRemoteImpl implements ISentinelFlowControlServerRemote {

	@Autowired
	private ISentinelFlowControlService sentinelFlowControlService;

	@Override
	public List<SentinelFlowControlDTO> queryFlowControlByProjectName(String projectName) {
		return copyList(() -> sentinelFlowControlService.queryByProjectName(projectName));
	}

	/**
	 * List<SentinelFlowControl> 转 List<SentinelFlowControlDTO>
	 * 
	 * @param supplier
	 * @return
	 */
	private List<SentinelFlowControlDTO> copyList(Supplier<List<SentinelFlowControl>> supplier) {
		List<SentinelFlowControl> list = supplier.get();
		if (null != list && list.size() > 0) {
			return list.stream().map(sentinelFlowControl -> copyProperties(sentinelFlowControl)).collect(Collectors.toList());
		}
		return null;
	}

	/**
	 * SentinelFlowControl 转 SentinelFlowControlDTO
	 * 
	 * @param sentinelFlowControl
	 * @return
	 */
	private SentinelFlowControlDTO copyProperties(SentinelFlowControl sentinelFlowControl) {
		if (null == sentinelFlowControl) {
			return null;
		}
		SentinelFlowControlDTO dto = new SentinelFlowControlDTO();
		dto.setId(sentinelFlowControl.getId());
		dto.setProjectName(sentinelFlowControl.getProjectName());
		dto.setResource(sentinelFlowControl.getResource());
		dto.setCount(sentinelFlowControl.getCount());
		dto.setGrade(sentinelFlowControl.getGrade());
		dto.setLimitapp(sentinelFlowControl.getLimitapp());
		dto.setStrategy(sentinelFlowControl.getStrategy());
		dto.setControlbehavior(sentinelFlowControl.getControlbehavior());
		return dto;
	}

}
