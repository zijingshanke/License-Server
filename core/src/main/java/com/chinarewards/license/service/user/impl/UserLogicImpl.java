package com.chinarewards.license.service.user.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.chinarewards.license.dao.user.RoleDao;
import com.chinarewards.license.dao.user.UserDao;
import com.chinarewards.license.dao.user.UserRoleDao;
import com.chinarewards.license.domain.user.SysUser;
import com.chinarewards.license.domain.user.SysUserRole;
import com.chinarewards.license.model.user.SearchUserInfo;
import com.chinarewards.license.model.user.UserRole;
import com.chinarewards.license.model.user.UserSearchCriteria;
import com.chinarewards.license.model.user.UserSearchResult;
import com.chinarewards.license.model.user.UserSessionVo;
import com.chinarewards.license.model.user.UserStatus;
import com.chinarewards.license.model.user.UserVo;
import com.chinarewards.license.service.user.UserLogic;
import com.chinarewards.license.util.DateUtil;
import com.chinarewards.license.util.MD5;
import com.google.inject.Inject;

/**
 * The implementation of {@link UserLogic}
 * 
 * @author yanxin
 * @since 1.0
 */
public class UserLogicImpl implements UserLogic {

	/**
	 * You should ensure only one user use this name.
	 */
	public static final String DEFAULT_NAME = "_damon_4076124377";

	UserDao userDao;
	RoleDao roleDao;
	UserRoleDao userRoleDao;
	MD5 md5 = new MD5();

	@Inject
	public UserLogicImpl(UserDao userDao, RoleDao roleDao,
			UserRoleDao userRoleDao) {
		this.userDao = userDao;
		this.roleDao = roleDao;
		this.userRoleDao = userRoleDao;
	}

	@Override
	public SysUser createUser(SysUser caller, UserVo user) {
		Date now = DateUtil.getTime();
		SysUser u = new SysUser();
		String password = "";
		try {
			password = md5.MD5(user.getPassword());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		u.setUserName(user.getUsername());
		u.setPassword(password);
		u.setCreatedAt(now);
		u.setCreatedBy(caller);
		u.setLastModifiedAt(now);
		u.setLastModifiedBy(caller);
		u.setStatus(UserStatus.Active);
		userDao.save(u);
		return u;
	}

	@Override
	public SysUser findUserById(String id) {
		return userDao.findById(SysUser.class, id);
	}

	@Override
	public UserSessionVo findUserByNameAndPwd(String userName, String pwd) {
		String password = "";
		try {
			password = md5.MD5(pwd);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SysUser user = userDao.findUserByNameAndPwd(userName, password);
		if (user != null)
			return findUserRolebySysUser(user);
		else
			return null;
	}

	@Override
	public UserSessionVo tokenVaild(String token) {
		SysUser user = userDao.findById(SysUser.class, token);

		if (user != null) {
			user.setLastLoginRole(userDao.findUserLastRoleById(token));
			return findUserRolebySysUser(user);
		} else
			return null;
	}

	private UserSessionVo findUserRolebySysUser(SysUser user) {
		UserSessionVo vo = new UserSessionVo();
		List<SysUserRole> listRole = userRoleDao.findUserRoleByUserId(user
				.getId());
		List<UserRole> userRoles = new ArrayList<UserRole>();
		if (listRole != null && listRole.size() > 0) {
			for (SysUserRole role : listRole) {
				userRoles.add(role.getRole().getName());
			}
		}

		vo.setId(user.getId());
		vo.setUsername(user.getUserName());
		vo.setUserRoles(userRoles);
		vo.setLastLoginRole(user.getLastLoginRole());
		vo.setUserStatus(user.getStatus());

		return vo;
	}

	@Override
	public UserSearchResult searchHrAdminUserPaging(UserSearchCriteria criteria) {

		UserSearchResult result = new UserSearchResult();
		List<SysUser> hrUsers = userDao.searchHrAdminUserByCriteria(criteria);
		List<SearchUserInfo> userInfos = new ArrayList<SearchUserInfo>();

		for (SysUser user : hrUsers) {
			SearchUserInfo info = new SearchUserInfo();
			info.setUser(user);
			userInfos.add(info);
		}
		result.setResult(userInfos);
		result.setTotal(userDao.countHrAdminUserByCriteria(criteria));
		return result;
	}

	@Override
	public String deleteUserById(String id) {
		SysUser user = userDao.findById(SysUser.class, id);
		user.setStatus(UserStatus.Inactive);

		userDao.update(user);
		return "success";
	}

	@Override
	public String updateLastLoginRole(String userId, UserRole role) {
		SysUser user = userDao.findById(SysUser.class, userId);
		user.setLastLoginRole(role);
		userDao.update(user);
		return "success";
	}

	@Override
	public String updateUserPwd(String staffId, String pwd, String byUserId) {
		String password = "";
		try {
			password = md5.MD5(pwd);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SysUser user = userDao.findUserByStaffId(staffId);
		SysUser nowUser = userDao.findUserById(byUserId);
		user.setLastModifiedAt(DateUtil.getTime());
		user.setLastModifiedBy(nowUser);
		user.setPassword(password);
		userDao.update(user);
		return "success";
	}

	@Override
	public void createUserRole(String roleName, List<String> staffIds) {
		if (staffIds != null) {
			for (int i = 0; i < staffIds.size(); i++) {
				String staffId = staffIds.get(i);
				createUserRole(roleName, staffId);
			}
		}
	}

	@Override
	public void deleteUserRole(String roleName, List<String> staffIds) {
		if (staffIds != null) {
			for (int i = 0; i < staffIds.size(); i++) {
				String staffId = staffIds.get(i);
				deleteUserRole(roleName, staffId);
			}
		}
	}

	@Override
	public void createUserRole(String roleName, String staffId) {
		SysUser user = userDao.findUserByStaffId(staffId);
		if (user != null) {
			List<SysUserRole> userRoleList = userRoleDao
					.findUserRoleByUserId(user.getId());

			SysUserRole existUserRole = null;
			if (userRoleList != null && userRoleList.size() > 0) {
				for (int j = 0; j < userRoleList.size(); j++) {
					SysUserRole tempUserRole = userRoleList.get(j);
					if (tempUserRole.getRole().getName()
							.equals(UserRole.valueOf(roleName))) {
						existUserRole = tempUserRole;
						break;
					}
				}
			}
			if (existUserRole == null) {

				SysUserRole userRole = new SysUserRole();
				userRole.setRole(roleDao.findRoleByRoleName(UserRole
						.valueOf(roleName)));
				userRole.setCreatedBy(user);
				userRole.setCreatedAt(DateUtil.getTime());
				userRole.setLastModifiedAt(DateUtil.getTime());
				userRole.setLastModifiedBy(user);
				userRole.setUser(user);
				userRoleDao.createUserRole(userRole);
			}
		}
	}

	@Override
	public void deleteUserRole(String roleName, String staffId) {
		SysUser user = userDao.findUserByStaffId(staffId);
		if (user != null) {
			List<SysUserRole> userRoleList = userRoleDao
					.findUserRoleByUserId(user.getId());

			for (int j = 0; j < userRoleList.size(); j++) {
				SysUserRole tempUserRole = userRoleList.get(j);
				if (tempUserRole.getRole().getName()
						.equals(UserRole.valueOf(roleName))) {
					if (tempUserRole != null) {
						userRoleDao.delete(tempUserRole);
					}
				}
			}
		}

	}

	@Override
	public String updateStaffPwd(String staffId, String oldpwd, String pwd,
			String byUserId) {
		String newpassword = "";
		String oldpassword = "";
		try {
			newpassword = md5.MD5(pwd);
			oldpassword = md5.MD5(oldpwd);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SysUser user = userDao.findUserByStaffId(staffId);

		SysUser nowUser = userDao.findUserById(byUserId);
		user.setLastModifiedAt(DateUtil.getTime());
		user.setLastModifiedBy(nowUser);
		user.setPassword(newpassword);
		if (user.getPassword().trim().equals(oldpassword.trim())) {
			userDao.update(user);
			return "success";
		} else {
			return "faile";
		}

	}

	@Override
	public SysUser findUserByStaffId(String staffId) {

		SysUser user = userDao.findUserByStaffId(staffId);

		return user;
	}

}
