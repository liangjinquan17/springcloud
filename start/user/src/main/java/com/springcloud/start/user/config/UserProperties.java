package com.springcloud.start.user.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "startuser")
public class UserProperties {
    private String name;
    private String age;
    private String version;
    private String remark;
}
