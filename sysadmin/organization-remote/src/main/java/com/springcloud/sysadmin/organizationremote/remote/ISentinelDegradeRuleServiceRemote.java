package com.springcloud.sysadmin.organizationremote.remote;

import com.springcloud.sysadmin.organizationremote.entity.DTO.SentinelDegradeRuleDTO;

import java.util.List;


/**
 * sentinel熔断降级规则 服务类
 * @author liangjinquan
 */
public interface ISentinelDegradeRuleServiceRemote {

    /**
     * 根据服务名称查询熔断降级规则
     * @param projectName
     * @return
     */
    List<SentinelDegradeRuleDTO> queryByProjectName(String projectName);
}
