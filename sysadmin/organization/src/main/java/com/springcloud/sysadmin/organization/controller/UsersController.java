package com.springcloud.sysadmin.organization.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springcloud.core.entity.vo.Result;
import com.springcloud.start.user.entity.po.SysUsers;
import com.springcloud.start.user.storage.UserStorageService;
import com.springcloud.sysadmin.organization.business.SysUsersBusiness;
import com.springcloud.sysadmin.organization.entity.dto.UserDetailDTO;
import com.springcloud.sysadmin.organization.entity.param.BindUserAuthorityParam;
import com.springcloud.sysadmin.organization.entity.param.BindUserRoleParam;
import com.springcloud.sysadmin.organization.entity.param.UpdateUserPasswordParam;
import com.springcloud.sysadmin.organization.entity.param.UserAddParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author liangjinquan
 * @since 2021-06-06
 */
@RestController
@RequestMapping("/users")
public class UsersController {
	
	@Autowired
	private SysUsersBusiness sysUsersBusiness;
	@Autowired
	private UserStorageService userStorageService;
	
	/**
	 * 新增用户
	 * @param userParam
	 * @return
	 */
	@PostMapping("saveUser")
	public Result<Boolean> saveUser(@RequestBody UserAddParam userParam){
		SysUsers user = new SysUsers() {{
			setId(IdWorker.getId());
			setUsername(userParam.getUserName());
			setName(userParam.getName());
			setPassword(userParam.getPassword());
			setAuthorities(userParam.getAuthorities());
			setEnabled(true);
			setAccountNonExpired(true);
			setCredentialsNonExpired(true);
			setAccountNonLocked(true);
			setCreatedTime(new Date());
		}};
		
		return sysUsersBusiness.saveUser(user) ? Result.success(true) : Result.fail(false);
	}

	/**
	 * 修改用户密码
	 * @param param
	 * @return
	 */
	@PostMapping("updatePassword")
	public Result<Boolean> updatePassword(@RequestBody UpdateUserPasswordParam param){
		//todo 判断登录用户角色，如果是管理员，无需旧密码即可修改。否则需要提供旧密码
		return sysUsersBusiness.updatePassword(param.getUsername(), param.getNewPassword(), null) ? Result.success(true) : Result.success(false);
	}

	/**
	 * 分页查询用户信息
	 * @param current		第几页
	 * @param size			每页显示多萧条
	 * @param username		模糊搜索用户名
	 * @return
	 */
	@GetMapping("queryPage")
	public Result<IPage<SysUsers>> queryPage(long current, long size, String username){
		IPage<SysUsers> page = new Page<SysUsers>(current, size);
		return Result.success(userStorageService.getByPage(page, username));
	}

	/**
	 * 查询用户详情
	 * @param username	用户名
	 * @return
	 */
	@GetMapping("getUserDetail")
	public Result<UserDetailDTO> getUserDetail(String username){
		return Result.success(sysUsersBusiness.getUserDetail(username));
	}

	/**
	 * 绑定用户角色
	 * @param param
	 * @return
	 */
	@PostMapping("bindUserRole")
	public Result<Boolean> bindUserRole(@RequestBody BindUserRoleParam param){
		return Result.success(userStorageService.bindUserRole(param.getUserId(), param.getRoleIds(), null));
	}

	/**
	 * 绑定用户权限
	 * @param param
	 * @return
	 */
	@PostMapping("bindUserAuthority")
	public Result<Boolean> bindUserAuthority(@RequestBody BindUserAuthorityParam param){
		return Result.success(userStorageService.bindUserAuthority(param.getUserId(), param.getAuthorityIds(), null));
	}
}

