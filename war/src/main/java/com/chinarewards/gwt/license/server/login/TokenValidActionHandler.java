package com.chinarewards.gwt.license.server.login;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import com.chinarewards.license.model.user.UserSessionVo;
import com.chinarewards.license.service.user.UserService;
import com.chinarewards.gwt.license.client.login.request.TokenValidRequest;
import com.chinarewards.gwt.license.client.login.request.TokenValidResponse;
import com.chinarewards.gwt.license.model.user.UserRoleVo;
import com.chinarewards.gwt.license.server.BaseActionHandler;
import com.chinarewards.gwt.license.util.UserRoleTool;
import com.google.inject.Inject;

public class TokenValidActionHandler extends
		BaseActionHandler<TokenValidRequest, TokenValidResponse> {

	UserService userService;
	
	@Inject
	public TokenValidActionHandler(UserService userService)
	{
		this.userService=userService;
	}
	@Override
	public Class<TokenValidRequest> getActionType() {
		return TokenValidRequest.class;
	}

	@Override
	public TokenValidResponse execute(TokenValidRequest action, ExecutionContext context)
			throws DispatchException {
		TokenValidResponse tokenRep=new TokenValidResponse();
		UserSessionVo userSessionVo=userService.tokenVaild(action.getToken());
		if(userSessionVo !=null){
		tokenRep.setCorporationId(userSessionVo.getCorporationId());
		tokenRep.setLoginName(userSessionVo.getUsername());
		tokenRep.setToken(userSessionVo.getId());
		tokenRep.setUserRoles(UserRoleTool.adaptToRoleVo(userSessionVo.getUserRoles()));
		tokenRep.setDepartmentId(userSessionVo.getDepartmentId());
		tokenRep.setStaffId(userSessionVo.getStaffId());
		tokenRep.setCorporationName(userSessionVo.getCorporationName());
		tokenRep.setPhoto(userSessionVo.getPhoto());
		if(userSessionVo.getLastLoginRole()!=null)
		{
			tokenRep.setLastLoginRole(UserRoleVo.valueOf(userSessionVo.getLastLoginRole().toString()));
		}

		}
		return tokenRep;
	}

	@Override
	public void rollback(TokenValidRequest action, TokenValidResponse result,
			ExecutionContext context) throws DispatchException {

	}

}
