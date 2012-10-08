package com.chinarewards.license.dao.user;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import com.chinarewards.license.common.BaseDao;
import com.chinarewards.license.domain.user.SysUser;
import com.chinarewards.license.model.user.UserRole;
import com.chinarewards.license.model.user.UserSearchCriteria;
import com.chinarewards.license.model.user.UserStatus;
import com.chinarewards.license.util.StringUtil;

public class UserDao extends BaseDao<SysUser> {

	@SuppressWarnings("unchecked")
	public List<SysUser> findUserByUserName(String userName) {
		return getEm()
				.createQuery("FROM SysUser u WHERE u.userName = :userName")
				.setParameter("userName", userName).getResultList();
	}
	
	public String createUser(SysUser user) {
		this.save(user);	
		return user.getId();
	}

	public UserRole findUserLastRoleById(String id) {
		UserRole user = (UserRole) getEm().createQuery("SELECT u.lastLoginRole FROM SysUser u WHERE u.id = :id").setParameter("id", id).getSingleResult();
		return user;
	}
	public SysUser findUserById(String id) {
		SysUser user = (SysUser) getEm().createQuery("FROM SysUser u WHERE u.id = :id").setParameter("id", id).getSingleResult();
		return user;
	}
	@SuppressWarnings("unchecked")
	public SysUser findUserByNameAndPwd(String userName,String pwd) {
		List<SysUser> userList = getEm().createQuery("FROM SysUser u WHERE u.userName = :userName and u.password=:password").setParameter("userName", userName).setParameter("password", pwd).getResultList();
		SysUser user=null;
		if(userList.size()>0)
			user=userList.get(0);
		
		return user;
	}
	@SuppressWarnings("unchecked")
	public SysUser findUserByStaffId(String staffId) {
		List<SysUser> userList = getEm().createQuery("FROM SysUser u WHERE u.staff.id = :staffId ").setParameter("staffId", staffId).getResultList();
		SysUser user=null;
		if(userList.size()>0)
			user=userList.get(0);
		
		return user;
	}
	
	@SuppressWarnings("unchecked")
	public List<SysUser> searchHrAdminUserByCriteria(UserSearchCriteria criteria) {
		Query q = buildQueryToSearchHrAdminUserPaging(criteria, SEARCH);
		if (null != criteria.getPaginationDetail()) {
			if (criteria.getPaginationDetail().getLimit() != 0) {
				q.setFirstResult(criteria.getPaginationDetail().getStart());
				q.setMaxResults(criteria.getPaginationDetail().getLimit());
			}
		}
		return q.getResultList();
	}
	
	/**
	 * Returns the count about HrAdmin user. The UserType must be {@code
	 * UserType#HR_ADMIN}.
	 * 
	 * @param criteria
	 * @return
	 */
	public int countHrAdminUserByCriteria(UserSearchCriteria criteria) {
		Query q = buildQueryToSearchHrAdminUserPaging(criteria, COUNT);
		return Integer.parseInt(q.getSingleResult().toString());
	}
	/**
	 * Ignore case and fuzzy query when searching.
	 * 
	 * @param criteria
	 * @param method
	 * @return
	 */
	private Query buildQueryToSearchHrAdminUserPaging(UserSearchCriteria criteria,
			String method) {
		StringBuffer eql = new StringBuffer("");
		if (SEARCH.equals(method)) {
			eql.append("FROM SysUser u WHERE u.corporation is not null ");
		} else {
			eql.append("SELECT COUNT(u) FROM SysUser u WHERE u.corporation is not null ");
		}
		Map<String, Object> param = new HashMap<String, Object>();
		if (!StringUtil.isEmptyString(criteria.getAccountName())) {
			eql.append(" AND UPPER(u.userName) like :accountName");
			param.put("accountName", "%"
					+ criteria.getAccountName().toUpperCase() + "%");
		}

		if (!StringUtil.isEmptyString(criteria.getEmail())) {
			eql.append(" AND UPPER(u.staff.email) like :email");
			param.put("email", "%" + criteria.getEmail().toUpperCase() + "%");
		}

		if (!StringUtil.isEmptyString(criteria.getEnterpriseId())) {
			eql.append(" AND u.corporation.id = :enterpriseId");
			param.put("enterpriseId", criteria.getEnterpriseId());
		}
		
		if (!StringUtil.isEmptyString(criteria.getCorporationId())) {
			eql.append(" AND u.corporation.id = :corporationId");
			param.put("corporationId", criteria.getCorporationId());
		}

		if (!StringUtil.isEmptyString(criteria.getMobile())) {
			eql.append(" AND UPPER(u.staff.phone) like :mobile");
			param.put("mobile", "%" + criteria.getMobile().toUpperCase() + "%");
		}

		if (!StringUtil.isEmptyString(criteria.getStatus())) {
			eql.append(" AND u.status = :status");
			param.put("status", UserStatus.valueOf(criteria.getStatus()));
		}

		// eql.append(" AND u.userType = :userType");
		// param.put("userType", UserType.HR_ADMIN);
		Query q = getEm().createQuery(eql.toString());
		Iterator<Map.Entry<String, Object>> it = param.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Object> entry = it.next();
			q.setParameter(entry.getKey(), entry.getValue());
		}

		return q;
	}
}
