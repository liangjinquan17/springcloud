package com.springcloud.start.user.entity.dto;

import com.springcloud.start.user.enums.UserExpandEnum;
import lombok.Data;

import java.util.List;

/**
 * 拓展信息详情
 */
@Data
public class ApplicationDetailDTO {
    private UserExpandEnum userExpandEnum;
    private List<String> ids;
}
