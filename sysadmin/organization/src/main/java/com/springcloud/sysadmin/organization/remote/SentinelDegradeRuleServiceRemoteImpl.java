package com.springcloud.sysadmin.organization.remote;

import com.springcloud.start.sentinel.entity.po.SentinelDegradeRule;
import com.springcloud.start.sentinel.service.ISentinelDegradeRuleService;
import com.springcloud.sysadmin.organizationremote.entity.DTO.SentinelDegradeRuleDTO;
import com.springcloud.sysadmin.organizationremote.remote.ISentinelDegradeRuleServiceRemote;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author liangjinquan
 */
@DubboService(version = "1.0.1")
public class SentinelDegradeRuleServiceRemoteImpl implements ISentinelDegradeRuleServiceRemote {

    @Autowired
    private ISentinelDegradeRuleService sentinelDegradeRuleService;

    @Override
    public List<SentinelDegradeRuleDTO> queryByProjectName(String projectName) {
        System.out.println(sentinelDegradeRuleService);
        List<SentinelDegradeRule> list = sentinelDegradeRuleService.queryByProjectName(projectName);
        if (null != list && list.size() > 0) {
            return copyList(list);
        }
        return null;
    }

    private List<SentinelDegradeRuleDTO> copyList(List<SentinelDegradeRule> list) {
        return list.stream().map(sentinelDegradeRule -> copySentinelDegradeRule(sentinelDegradeRule)).collect(Collectors.toList());
    }

    private SentinelDegradeRuleDTO copySentinelDegradeRule(SentinelDegradeRule sentinelDegradeRule) {
        SentinelDegradeRuleDTO dto = new SentinelDegradeRuleDTO();
        dto.setId(sentinelDegradeRule.getId());
        dto.setProjectName(sentinelDegradeRule.getProjectName());
        dto.setResource(sentinelDegradeRule.getResource());
        dto.setCount(sentinelDegradeRule.getCount());
        dto.setGrade(sentinelDegradeRule.getGrade());
        dto.setTimeWindow(sentinelDegradeRule.getTimeWindow());
        dto.setMinRequestAmount(sentinelDegradeRule.getMinRequestAmount());
        dto.setStatIntervalMs(sentinelDegradeRule.getStatIntervalMs());
        dto.setSlowRatioThreshold(sentinelDegradeRule.getSlowRatioThreshold());
        return dto;
    }
}
