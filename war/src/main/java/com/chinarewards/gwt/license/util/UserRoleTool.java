package com.chinarewards.gwt.license.util;

import java.util.ArrayList;
import java.util.List;

import com.chinarewards.license.model.user.UserRole;
import com.chinarewards.gwt.license.model.user.UserRoleVo;

public class UserRoleTool {
	public static UserRoleVo adapt(UserRole role) {
		String s = role.toString();
		return UserRoleVo.valueOf(s);
	}
	public static UserRole adapt(UserRoleVo role) {
		String s = role.toString();
		return UserRole.valueOf(s);
	}
	
	public static UserRoleVo[] adaptToRoleVo(List<UserRole> roles) {
		if (roles == null)
			return null;
		List<UserRoleVo> cli = new ArrayList<UserRoleVo>();
		for (UserRole role : roles) {
			UserRoleVo c = adapt(role);
			cli.add(c);
		}
		return cli.toArray(new UserRoleVo[0]);
	}
	
	public static UserRole[] adaptToRole(UserRoleVo[] userRoleVos) {
		if (userRoleVos == null)
			return null;
		List<UserRole> cli = new ArrayList<UserRole>();
		for (UserRoleVo role : userRoleVos) {
			UserRole c = adapt(role);
			cli.add(c);
		}
		return cli.toArray(new UserRole[0]);
	}
}
