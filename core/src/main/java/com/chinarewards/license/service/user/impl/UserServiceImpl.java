package com.chinarewards.license.service.user.impl;

import java.util.List;

import com.chinarewards.license.domain.user.SysUser;
import com.chinarewards.license.model.user.UserRole;
import com.chinarewards.license.model.user.UserSearchCriteria;
import com.chinarewards.license.model.user.UserSearchResult;
import com.chinarewards.license.model.user.UserSessionVo;
import com.chinarewards.license.service.user.UserLogic;
import com.chinarewards.license.service.user.UserService;
import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

@Transactional
public class UserServiceImpl implements UserService {
	UserLogic userLogic;

	@Inject
	public UserServiceImpl(UserLogic userLogic) {
		this.userLogic = userLogic;
	}

	@Override
	public UserSessionVo authenticate(String userName, String pwd) {
		return userLogic.findUserByNameAndPwd(userName, pwd);
	}

	@Override
	public UserSessionVo tokenVaild(String token) {
		return userLogic.tokenVaild(token);
	}

	@Override
	public UserSearchResult searchHrAdminUserPaging(UserSearchCriteria criteria) {
		return userLogic.searchHrAdminUserPaging(criteria);
	}

	public SysUser findUserById(String id) {
		return userLogic.findUserById(id);
	}

	@Override
	public String deleteUserById(String id) {
		return userLogic.deleteUserById(id);
	}

	@Override
	public String updateLastLoginRole(String userId, UserRole role) {
		return userLogic.updateLastLoginRole(userId, role);
	}

	@Override
	public String updateUserPwd(String staffId,String pwd,String byUserId) {
		return userLogic.updateUserPwd(staffId, pwd,byUserId);
	}
	
	@Override
	public void createUserRole(String roleName,List<String> staffIds){
		userLogic.createUserRole(roleName, staffIds);
	}
	
	@Override
	public void createUserRole(String roleName, String staffId){
		userLogic.createUserRole(roleName, staffId);
	}

	@Override
	public void deleteUserRole(String roleName, List<String> staffIds) {
		userLogic.deleteUserRole(roleName, staffIds);
	}


	@Override
	public void deleteUserRole(String roleName, String staffId) {
		userLogic.deleteUserRole(roleName, staffId);
	}
	@Override
	public String updateStaffPwd(String staffId,String oldpwd,String pwd,String byUserId) {
		return userLogic.updateStaffPwd(staffId,oldpwd, pwd,byUserId);
	}
	
	@Override
	public SysUser findUserByStaffId(String staffId){
		return userLogic.findUserByStaffId(staffId);
	}
}
