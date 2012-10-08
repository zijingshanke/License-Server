/**
 * 
 */
package com.chinarewards.gwt.license.client.user;

import net.customware.gwt.dispatch.shared.Action;

import com.chinarewards.gwt.license.client.support.UserSession;
import com.chinarewards.gwt.license.client.user.model.UserSearchVo;

/**
 * An action which perform request to search user.
 * 
 * @author yanxin
 * @since 0.0.1 2010-09-25
 */
public class UserSearchRequest implements Action<UserSearchResponse> {

	private UserSearchVo criteria;
	private UserSession session;

	/**
	 * For serialization
	 */
	public UserSearchRequest() {
	}

	/**
	 * 
	 * @param process
	 * @param session
	 */
	public UserSearchRequest(UserSearchVo criteria, UserSession session) {
		this.criteria = criteria;
		this.session = session;
	}

	/**
	 * @return the criteria
	 */
	public UserSearchVo getCriteria() {
		return criteria;
	}

	/**
	 * @param criteria
	 *            the criteria to set
	 */
	public void setCriteria(UserSearchVo criteria) {
		this.criteria = criteria;
	}

	/**
	 * @return the session
	 */
	public UserSession getSession() {
		return session;
	}

	/**
	 * @param session
	 *            the session to set
	 */
	public void setSession(UserSession session) {
		this.session = session;
	}

}
