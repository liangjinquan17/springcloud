package com.springcloud.start.sentinel.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "startsentinel")
public class Properties {
    private String name;
    private String age;
    private String version;
    private String remark;
}
