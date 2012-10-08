/**
 * 
 */
package com.chinarewards.gwt.license.client.user;

import java.util.Map;

import com.chinarewards.gwt.license.client.user.model.UserVo;

import net.customware.gwt.dispatch.shared.Action;

/**
 * An action which perform request to delete user.
 * 
 * @author nichi
 * @since 2012年1月13日 15:35:50
 */
public class DeleteUserRequest implements Action<DeleteUserResponse> {

	private Map<String,UserVo> userIds;



	public Map<String, UserVo> getUserIds() {
		return userIds;
	}

	public void setUserIds(Map<String, UserVo> userIds) {
		this.userIds = userIds;
	}

	/**
	 * For serialization
	 */
	public DeleteUserRequest() {
	}

	/**
	 * 
	 * @param process
	 * @param session
	 */
	public DeleteUserRequest(Map<String,UserVo> userIds) {
		this.userIds = userIds;
	}



}
