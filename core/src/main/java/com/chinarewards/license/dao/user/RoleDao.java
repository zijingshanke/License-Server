package com.chinarewards.license.dao.user;

import java.util.List;

import com.chinarewards.license.common.BaseDao;
import com.chinarewards.license.domain.user.SysRole;
import com.chinarewards.license.model.user.UserRole;

public class RoleDao extends BaseDao<SysRole> {


	@SuppressWarnings("unchecked")
	public SysRole findRoleByRoleName(UserRole roleName) {
		
		List<SysRole> rolelt=getEm().createQuery("FROM SysRole u WHERE u.name = :roleName").setParameter("roleName", roleName).getResultList();
		if(rolelt.size()>0)
		return rolelt.get(0);
		else
		return null;
	}
	


	

	
}
