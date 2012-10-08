package com.chinarewards.gwt.license.server.user;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.slf4j.Logger;

import com.chinarewards.license.service.user.UserService;
import com.chinarewards.gwt.license.client.user.DeleteUserRequest;
import com.chinarewards.gwt.license.client.user.DeleteUserResponse;
import com.chinarewards.gwt.license.client.user.model.UserVo;
import com.chinarewards.gwt.license.server.BaseActionHandler;
import com.chinarewards.gwt.license.server.logger.InjectLogger;
import com.google.inject.Inject;

/**
 * The handler to correspond the UserSearchRequest.
 * 
 * @author nicho
 * @since 2012年1月13日 15:43:58
 */
public class DeleteUserActionHandler extends
		BaseActionHandler<DeleteUserRequest, DeleteUserResponse> {

	@InjectLogger
	Logger logger;
	UserService userService;

	@Inject
	public DeleteUserActionHandler(UserService userService) {
		this.userService = userService;
	}

	@Override
	public DeleteUserResponse execute(DeleteUserRequest request,
			ExecutionContext response) throws DispatchException {
		Map<String, UserVo> users = request.getUserIds();
		Collection<UserVo> c = users.values();
		Iterator<UserVo> it = c.iterator();
		while (it.hasNext()) {
			userService.deleteUserById(it.next().getId());
		}
		return new DeleteUserResponse("");

	}

	@Override
	public Class<DeleteUserRequest> getActionType() {
		return DeleteUserRequest.class;
	}

	@Override
	public void rollback(DeleteUserRequest request,
			DeleteUserResponse response, ExecutionContext context)
			throws DispatchException {
	}

}
