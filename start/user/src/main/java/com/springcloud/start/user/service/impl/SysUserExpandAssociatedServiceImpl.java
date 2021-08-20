package com.springcloud.start.user.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.springcloud.start.user.entity.dto.ApplicationDetailDTO;
import com.springcloud.start.user.entity.po.SysUserExpandAssociated;
import com.springcloud.start.user.dao.SysUserExpandAssociatedMapper;
import com.springcloud.start.user.enums.UserExpandEnum;
import com.springcloud.start.user.service.IQueryUserExpandAssociated;
import com.springcloud.start.user.service.ISysUserExpandAssociatedService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户属性拓展表 服务实现类
 * </p>
 *
 * @author liangjinquan
 * @since 2021-07-30
 */
@Service
public class SysUserExpandAssociatedServiceImpl extends ServiceImpl<SysUserExpandAssociatedMapper, SysUserExpandAssociated> implements ISysUserExpandAssociatedService {

    @Override
    public boolean updateUserExpandAssociated(Long userId, List<String> applicationIds, UserExpandEnum userExpandEnum, String createdBy) {
        // 根据拓展类型更新拓展id集合
        SysUserExpandAssociated sysUserExpandAssociated = baseMapper.getByUserId(userId);
        if(null == sysUserExpandAssociated){
            sysUserExpandAssociated = new SysUserExpandAssociated();
            sysUserExpandAssociated.setId(IdWorker.getId());
            sysUserExpandAssociated.setUserId(userId);
            sysUserExpandAssociated.setExpand(userExpandEnum.getId());
            sysUserExpandAssociated.setStatus(1);
            sysUserExpandAssociated.setCreatedTime(new Date());
            sysUserExpandAssociated.setCreatedBy(createdBy);
            sysUserExpandAssociated.setUpdatedTime(new Date());
            sysUserExpandAssociated.setUpdatedBy(createdBy);
            updateApplication(sysUserExpandAssociated, userExpandEnum, applicationIds);
            return baseMapper.insert(sysUserExpandAssociated) > 0 ;
        }else{
            updateApplication(sysUserExpandAssociated, userExpandEnum, applicationIds);
            sysUserExpandAssociated.setUpdatedTime(new Date());
            sysUserExpandAssociated.setUpdatedBy(createdBy);
            return baseMapper.updateById(sysUserExpandAssociated) > 0;
        }
    }

    @Override
    public <T, Service extends IQueryUserExpandAssociated<T>> List<T> queryUserExpandAssociated(Long userId, Service service) {
        // userId 换 applicationIds
        SysUserExpandAssociated sysUserExpandAssociated = baseMapper.getByUserId(userId);
        if(null == sysUserExpandAssociated){
            return null;
        }else{
            ApplicationDetailDTO applicationDetail = getApplicationDetail(sysUserExpandAssociated, service.getUserExpandEnum());
            if(null == applicationDetail){
                return null;
            }else{
                return service.getByIds(applicationDetail.getIds());
            }
        }
    }

    /**
     * 添加拓展信息
     * @param userExpandEnum            用户拓展信息枚举类
     * @param applicationIds            拓展属性主键集合
     * @return
     */
    private void addApplication(SysUserExpandAssociated sysUserExpandAssociated, UserExpandEnum userExpandEnum, List<String> applicationIds){
        if(null == applicationIds || applicationIds.size() <= 0){
            return;
        }
        // 1. 判断总拓展信息是否为空，为空直接添加
        // 2. 如果总拓展信息不为空，判断指定拓展信息是否为空，为空直接添加；不为空则新旧拓展主键相加去重。
        List<ApplicationDetailDTO> list = parseApplication(sysUserExpandAssociated);
        if(list == null){
            ApplicationDetailDTO detail = new ApplicationDetailDTO();
            detail.setUserExpandEnum(userExpandEnum);
            detail.setIds(applicationIds);
            list = new ArrayList<ApplicationDetailDTO>(1);
            list.add(detail);
        }else{
            ApplicationDetailDTO detail = list.stream().filter(detail1 -> detail1.getUserExpandEnum().equals(userExpandEnum)).findFirst().orElse(null);
            if(null == detail){
                detail = new ApplicationDetailDTO();
                detail.setUserExpandEnum(userExpandEnum);
                detail.setIds(applicationIds);
                list.add(detail);
            }else{
                for(ApplicationDetailDTO tmp : list){
                    if(tmp.getUserExpandEnum().equals(userExpandEnum)){
                        List<String> ids = detail.getIds();
                        ids.addAll(applicationIds);
                        ids = ids.stream().distinct().collect(Collectors.toList());
                        tmp.setIds(ids);
                    }
                }
            }
        }
        sysUserExpandAssociated.setApplication(JSONArray.toJSONString(list));
    }

    /**
     * 更新拓展信息
     * 以最新的拓展属性主键为准
     * @param userExpandEnum            用户拓展信息枚举类
     * @param applicationIds            拓展属性主键集合
     * @return
     */
    private void updateApplication(SysUserExpandAssociated sysUserExpandAssociated, UserExpandEnum userExpandEnum, List<String> applicationIds){
        if(null == applicationIds || applicationIds.size() <= 0){
            return;
        }
        // 1. 判断总拓展信息是否为空，为空直接添加
        // 2. 如果总拓展信息不为空，判断指定拓展信息是否为空，为空直接添加；不为空则直接替换
        List<ApplicationDetailDTO> list = parseApplication(sysUserExpandAssociated);
        if(list == null){
            ApplicationDetailDTO detail = new ApplicationDetailDTO();
            detail.setUserExpandEnum(userExpandEnum);
            detail.setIds(applicationIds);
            list = new ArrayList<ApplicationDetailDTO>(1);
            list.add(detail);
        }else{
            ApplicationDetailDTO detail = list.stream().filter(detail1 -> detail1.getUserExpandEnum().equals(userExpandEnum)).findFirst().orElse(null);
            if(null == detail){
                detail = new ApplicationDetailDTO();
                detail.setUserExpandEnum(userExpandEnum);
                detail.setIds(applicationIds);
                list.add(detail);
            }else{
                for(ApplicationDetailDTO tmp : list){
                    if(tmp.getUserExpandEnum().equals(userExpandEnum)){
                        tmp.setIds(applicationIds);
                    }
                }
            }
        }
        sysUserExpandAssociated.setApplication(JSONArray.toJSONString(list));
    }

    /**
     * 删除指定用户拓展信息
     * @param userExpandEnum            用户拓展信息枚举类
     */
    private void deleteApplication(SysUserExpandAssociated sysUserExpandAssociated, UserExpandEnum userExpandEnum){
        List<ApplicationDetailDTO> list = parseApplication(sysUserExpandAssociated);
        if(list != null){
            ApplicationDetailDTO applicationDetail = list.stream().filter(detail -> detail.getUserExpandEnum().equals(userExpandEnum)).findFirst().orElse(null);
            if(applicationDetail != null){
                list.remove(applicationDetail);
                sysUserExpandAssociated.setApplication(JSONArray.toJSONString(list));
            }
        }
    }

    /**
     * 清空所有拓展信息
     */
    public void emptyApplication(SysUserExpandAssociated sysUserExpandAssociated){
        sysUserExpandAssociated.setApplication(null);
    }

    /**
     * 查询用户所有拓展信息
     * @return
     */
    private List<ApplicationDetailDTO> getAllApplicationDetail(SysUserExpandAssociated sysUserExpandAssociated){
        if(sysUserExpandAssociated.getApplication() != null && !"".equals(sysUserExpandAssociated.getApplication())){
            return parseApplication(sysUserExpandAssociated);
        }else{
            return null;
        }
    }

    /**
     * 查询指定拓展信息
     * @param userExpandEnum            用户拓展信息枚举类
     * @return
     */
    private ApplicationDetailDTO getApplicationDetail(SysUserExpandAssociated sysUserExpandAssociated, UserExpandEnum userExpandEnum){
        if(sysUserExpandAssociated.getApplication() != null && !"".equals(sysUserExpandAssociated.getApplication())){
            return parseApplication(sysUserExpandAssociated).stream().filter(detail -> detail.getUserExpandEnum().equals(userExpandEnum)).findFirst().orElse(null);
        }else{
            return null;
        }
    }

    /**
     * 格式化拓展信息
     * string --> DTO
     * @return
     */
    private List<ApplicationDetailDTO> parseApplication(SysUserExpandAssociated sysUserExpandAssociated){
        if(null != sysUserExpandAssociated.getApplication() && !"".equals(sysUserExpandAssociated.getApplication())){
            return JSONArray.parseArray(sysUserExpandAssociated.getApplication(), ApplicationDetailDTO.class);
        }
        return null;
    }
}
