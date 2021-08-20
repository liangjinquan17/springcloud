package com.springcloud.start.sentinel.service.impl;

import com.springcloud.start.sentinel.dao.SentinelFlowControlMapper;
import com.springcloud.start.sentinel.entity.po.SentinelFlowControl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

import com.springcloud.start.sentinel.service.ISentinelFlowControlService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * sentinel流量控制规则 服务实现类
 * </p>
 *
 * @author liangjinquan
 * @since 2021-06-22
 */
@Service
public class SentinelFlowControlServiceImpl extends ServiceImpl<SentinelFlowControlMapper, SentinelFlowControl> implements ISentinelFlowControlService {

	@Override
	public List<SentinelFlowControl> queryAll() {
		return baseMapper.selectList(null);
	}

	@Override
	public List<SentinelFlowControl> queryByProjectName(String projectName) {
		return baseMapper.queryByProjectName(projectName);
	}

}
