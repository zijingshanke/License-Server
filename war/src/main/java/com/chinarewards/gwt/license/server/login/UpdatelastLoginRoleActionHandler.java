package com.chinarewards.gwt.license.server.login;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import com.chinarewards.license.model.user.UserRole;
import com.chinarewards.license.service.user.UserService;
import com.chinarewards.gwt.license.client.login.request.LastLoginRoleRequest;
import com.chinarewards.gwt.license.client.login.request.LastLoginRoleResponse;
import com.chinarewards.gwt.license.server.BaseActionHandler;
import com.google.inject.Inject;

public class UpdatelastLoginRoleActionHandler extends
		BaseActionHandler<LastLoginRoleRequest, LastLoginRoleResponse> {

	UserService userService;

	@Inject
	public UpdatelastLoginRoleActionHandler(UserService userService) {
		this.userService = userService;
	}

	@Override
	public Class<LastLoginRoleRequest> getActionType() {
		return LastLoginRoleRequest.class;
	}

	@Override
	public LastLoginRoleResponse execute(LastLoginRoleRequest action,
			ExecutionContext context) throws DispatchException {
		String fal = userService.updateLastLoginRole(action.getUserId(),
				UserRole.valueOf(action.getRole().toString()));
		return new LastLoginRoleResponse(fal);
	}

	@Override
	public void rollback(LastLoginRoleRequest action,
			LastLoginRoleResponse result, ExecutionContext context)
			throws DispatchException {

	}

}
