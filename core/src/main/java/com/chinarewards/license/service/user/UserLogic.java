package com.chinarewards.license.service.user;

import java.util.List;

import com.chinarewards.license.domain.user.SysUser;
import com.chinarewards.license.model.user.UserRole;
import com.chinarewards.license.model.user.UserSearchCriteria;
import com.chinarewards.license.model.user.UserSearchResult;
import com.chinarewards.license.model.user.UserSessionVo;
import com.chinarewards.license.model.user.UserVo;

/**
 * The logic to operate {@link SysUser}.
 * 
 * @author yanxin
 * @since 1.0
 */
public interface UserLogic {


	/**
	 * Create a {@link SysUser}
	 * 
	 * @param caller
	 * @param user
	 * @return
	 */
	public SysUser createUser(SysUser caller, UserVo user);
	
	/**
	 * find User By   Id
	 * @return
	 */
	public SysUser findUserById(String id);
	/**
	 * find User By   name and pwd
	 * @return
	 */
	public UserSessionVo findUserByNameAndPwd(String userName,String pwd);
	
	/**
	 * find User By   Id
	 * @return
	 */
	public UserSessionVo tokenVaild(String token);
	/**
	 * Search user by paging.
	 * 
	 * @param criteria
	 * @return
	 */

	public UserSearchResult searchHrAdminUserPaging(UserSearchCriteria criteria);
	/**
	 * 删除用户..离职
	 * @param id
	 * @return
	 */
	public String deleteUserById(String id);
/**
 * 修改最后一次登录角色
 * @param userId
 * @param role
 * @return
 */
	public String updateLastLoginRole(String userId, UserRole role);
	/**
	 * 重置密码
	 * @param staffId
	 * @param pwd
	 * @return
	 */
	public String updateUserPwd(String staffId,String pwd,String byUserId);

	/**
	 * @param roleName
	 * @param staffId
	 */
	public void createUserRole(String roleName, List<String> staffIds);

	/**
	 * @param roleName
	 * @param staffId
	 */
	public void createUserRole(String roleName, String staffId);

	/**
	 * @param roleName
	 * @param staffIds
	 */
	public void deleteUserRole(String roleName, List<String> staffIds);

	/**
	 * @param roleName
	 * @param staffId
	 */
	public void deleteUserRole(String roleName, String staffId);
	
	public String updateStaffPwd(String staffId,String oldpwd,String pwd,String byUserId);

	/**
	 * @param staffId
	 * @return
	 */
	public SysUser findUserByStaffId(String staffId);
}
