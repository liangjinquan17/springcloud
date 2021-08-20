package com.springcloud.start.user.entity.dto;

import com.springcloud.start.user.enums.UserExpandEnum;
import lombok.Data;

import java.util.Date;
import java.util.List;


/**
 * 用户拓展信息
 */
@Data
public class UserExpandAssociatedDTO {

    /**
     * 主键id
     */
    private Long id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 拓展类型(二进制转十进制)来源 UserExpandEnum的id
     */
    private Integer expand;

    /**
     * 动态拓展信息
     */
    private List<KeyVal> application;

    /**
     * 可用状态：1=可用 2=不可用
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新时间
     */
    private Date updatedTime;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 更新人更新人
     */
    private String updatedBy;

    @Data
    class KeyVal{

        /**
         * 用户类型
         */
        private UserExpandEnum userExpandEnum;

        /**
         * 用户类型主键id集合
         */
        private List<Long> ids;
    }
}
