package com.springcloud.start.sentinel.service.impl;

import java.util.List;

import com.springcloud.start.sentinel.dao.SentinelDegradeRuleMapper;
import com.springcloud.start.sentinel.entity.po.SentinelDegradeRule;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springcloud.start.sentinel.service.ISentinelDegradeRuleService;

/**
 * <p>
 * sentinel熔断降级规则 服务实现类
 * </p>
 *
 * @author liangjinquan
 * @since 2021-06-22
 */
@Service
public class SentinelDegradeRuleServiceImpl extends ServiceImpl<SentinelDegradeRuleMapper, SentinelDegradeRule> implements ISentinelDegradeRuleService {

	@Override
	public List<SentinelDegradeRule> queryAll() {
		return baseMapper.selectList(null);
	}

	@Override
	public List<SentinelDegradeRule> queryByProjectName(String projectName) {
		return baseMapper.queryByProjectName(projectName);
	}

	@Override
	public List<SentinelDegradeRule> batchQueryByProjectNames(List<String> projectNames) {
		return baseMapper.batchQueryByProjectNames(projectNames);
	}

}
