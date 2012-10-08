package com.chinarewards.gwt.license.server.login;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import com.chinarewards.license.model.user.UserSessionVo;
import com.chinarewards.license.model.user.UserStatus;
import com.chinarewards.license.service.user.UserService;
import com.chinarewards.gwt.license.client.login.request.LoginRequest;
import com.chinarewards.gwt.license.client.login.request.LoginResponse;
import com.chinarewards.gwt.license.model.ClientException;
import com.chinarewards.gwt.license.model.user.UserRoleVo;
import com.chinarewards.gwt.license.server.BaseActionHandler;
import com.chinarewards.gwt.license.util.UserRoleTool;
import com.google.inject.Inject;

public class LoginActionHandler extends
		BaseActionHandler<LoginRequest, LoginResponse> {
	UserService userService;

	@Inject
	public LoginActionHandler(UserService userService) {
		this.userService = userService;
	}

	@Override
	public Class<LoginRequest> getActionType() {
		return LoginRequest.class;
	}

	@Override
	public LoginResponse execute(LoginRequest action, ExecutionContext context)
			throws DispatchException {

		LoginResponse resp = new LoginResponse();
		UserSessionVo u = userService.authenticate(action.getUserName(),
				action.getPwd());
		
		if (u != null) {
			if(u.getUserRoles().size()<=0)
			{
				throw new ClientException("用户无角色!");
			}
			if(u.getUserStatus()==UserStatus.Inactive)
			{
				throw new ClientException("您已离职,拒绝进入!");
			}
			resp.setCorporationId(u.getCorporationId());
			resp.setCorporationName(u.getCorporationName());
			resp.setPhoto(u.getPhoto());
			resp.setLoginName(u.getUsername());
			resp.setToken(u.getId());
			resp.setDepartmentId(u.getDepartmentId());
			resp.setUserRoles(UserRoleTool.adaptToRoleVo(u.getUserRoles()));
			resp.setStaffId(u.getStaffId());
			if(u.getLastLoginRole()!=null)
			{
				resp.setLastLoginRole(UserRoleVo.valueOf(u.getLastLoginRole().toString()));
			}

		} else {
			throw new ClientException("用户名或密码错误!");
		}
		
		return resp;

	}

	@Override
	public void rollback(LoginRequest action, LoginResponse result,
			ExecutionContext context) throws DispatchException {

	}


}
