package com.springcloud.sysadmin.organization.config;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.springcloud.start.sentinel.entity.po.SentinelDegradeRule;
import com.springcloud.start.sentinel.entity.po.SentinelFlowControl;
import com.springcloud.start.sentinel.service.ISentinelDegradeRuleService;
import com.springcloud.start.sentinel.service.ISentinelFlowControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.csp.sentinel.datasource.AutoRefreshDataSource;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

/**
 * sentinel配置
 * @author liangjinquan
 *
 */
@Component
public class SentinelConfig {

	@Value("${spring.application.name}")
	private String projectName;
	// 轮询时间
	private final long recommendRefreshMs = 30000;
	
	@Autowired
	private ISentinelFlowControlService sentinelFlowControlService;
	@Autowired
	private ISentinelDegradeRuleService sentinelDegradeRuleService;

	@PostConstruct
	public void init() {
		FlowRuleManager.register2Property(new FlowRoleRefresh(recommendRefreshMs).getProperty());
		DegradeRuleManager.register2Property(new DegradeRuleRefresh(recommendRefreshMs).getProperty());
	}

	/**
	 * 流量控制
	 * @author liangjinquan
	 *
	 */
	class FlowRoleRefresh extends AutoRefreshDataSource<List<SentinelFlowControl>, List<FlowRule>> {
		public FlowRoleRefresh(long recommendRefreshMs) {
			super(new FlowControlConfigParser(), recommendRefreshMs);
		}

		@Override
		public List<SentinelFlowControl> readSource() throws Exception {
			return sentinelFlowControlService.queryByProjectName(projectName);
		}
	}
	
	/**
	 * 熔断降级
	 * @author liangjinquan
	 *
	 */
	class DegradeRuleRefresh extends AutoRefreshDataSource<List<SentinelDegradeRule>, List<DegradeRule>> {

		public DegradeRuleRefresh(long recommendRefreshMs) {
			super(new DegradeRuleConfigParser(), recommendRefreshMs);
		}

		@Override
		public List<SentinelDegradeRule> readSource() throws Exception {
			return sentinelDegradeRuleService.queryByProjectName(projectName);
		}

		
	}

	/**
	 * 流量控制对象转化
	 * @author liangjinquan
	 *
	 */
	class FlowControlConfigParser implements Converter<List<SentinelFlowControl>, List<FlowRule>> {
		@Override
		public List<FlowRule> convert(List<SentinelFlowControl> source) {
			return source.stream().reduce(new ArrayList<FlowRule>(source.size()), (rlist, sentinelFlowControl)  -> {
				FlowRule rule = new FlowRule() {{
					setResource(sentinelFlowControl.getResource());
					setCount(sentinelFlowControl.getCount());
					setGrade(sentinelFlowControl.getGrade());
					setLimitApp(sentinelFlowControl.getLimitapp());
					setStrategy(sentinelFlowControl.getStrategy());
					setControlBehavior(sentinelFlowControl.getControlbehavior());
				}};
				rlist.add(rule);
				return rlist;
			}, (l1, l2) -> null);
		}
	}
	
	/**
	 * 熔断降级对象转化
	 * @author liangjinquan
	 *
	 */
	class DegradeRuleConfigParser implements Converter<List<SentinelDegradeRule>, List<DegradeRule>> {

		@Override
		public List<DegradeRule> convert(List<SentinelDegradeRule> source) {
			return source.stream().reduce(new ArrayList<DegradeRule>(source.size()), (dlist, sentinelDegradeRule) -> {
				DegradeRule dr = new DegradeRule(){{
					setCount(sentinelDegradeRule.getCount());
					setGrade(sentinelDegradeRule.getGrade());
					setMinRequestAmount(sentinelDegradeRule.getMinRequestAmount());
					setSlowRatioThreshold(sentinelDegradeRule.getSlowRatioThreshold().intValue());
					setStatIntervalMs(sentinelDegradeRule.getStatIntervalMs());
					setTimeWindow(sentinelDegradeRule.getTimeWindow());
					setResource(sentinelDegradeRule.getResource());
				}};
				dlist.add(dr);
				return dlist;
			}, (l1, l2) -> null);
		}
	}

	
}
