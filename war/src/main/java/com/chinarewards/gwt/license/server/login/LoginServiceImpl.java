package com.chinarewards.gwt.license.server.login;

import javax.servlet.http.HttpSession;

import com.chinarewards.license.model.user.UserSessionVo;
import com.chinarewards.license.model.user.UserStatus;
import com.chinarewards.license.service.user.UserService;
import com.chinarewards.gwt.license.client.remote.login.LoginService;
import com.chinarewards.gwt.license.client.support.UserSession;
import com.chinarewards.gwt.license.model.ClientException;
import com.chinarewards.gwt.license.model.user.UserRoleVo;
import com.chinarewards.gwt.license.util.UserRoleTool;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * The server side implementation of the RPC service.
 */
@Singleton
public class LoginServiceImpl extends RemoteServiceServlet implements
		LoginService {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	UserService userService;
	/**
	 * 校验码 KEY
	 */
	protected static final String CODE_SESSION_KEY = com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

	@Inject
	public LoginServiceImpl(UserService userService) {
		this.userService = userService;
	}
	public LoginServiceImpl() {
		
	}
	@Override
	public UserSession authLogin(String username, String password,
			String verifyCode) throws ClientException {
		// check verification code (kaptcha)
		HttpSession session = getThreadLocalRequest().getSession();
		String code = (String) session.getAttribute(CODE_SESSION_KEY);
		if (!verifyCode.equalsIgnoreCase(code)) {
			throw new ClientException("验证码错误");
		}
		
		UserSession resp = new UserSession();
		UserSessionVo u = userService.authenticate(username,password);
		
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
}
