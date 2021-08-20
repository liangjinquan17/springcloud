package com.springcloud.start.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "startgateway")
public class Properties {
    private String name;
    private String age;
    private String version;
    private String remark;
}
