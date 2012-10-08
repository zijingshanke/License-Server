package com.chinarewards.gwt.license.server;

import net.customware.gwt.dispatch.server.guice.ActionHandlerModule;
import com.chinarewards.gwt.license.client.license.request.DeleteLicenseRequest;
import com.chinarewards.gwt.license.client.license.request.EditLicenseRequest;
import com.chinarewards.gwt.license.client.license.request.SearchLicenseByIdRequest;
import com.chinarewards.gwt.license.client.license.request.SearchLicenseRequest;
import com.chinarewards.gwt.license.client.login.request.LastLoginRoleRequest;
import com.chinarewards.gwt.license.client.login.request.LoginRequest;
import com.chinarewards.gwt.license.client.login.request.TokenValidRequest;
import com.chinarewards.gwt.license.server.license.DeleteLicenseHandler;
import com.chinarewards.gwt.license.server.license.EditLicenseHandler;
import com.chinarewards.gwt.license.server.license.SearchLicenseByIdHandler;
import com.chinarewards.gwt.license.server.license.SearchLicenseHandler;
import com.chinarewards.gwt.license.server.login.LoginActionHandler;
import com.chinarewards.gwt.license.server.login.TokenValidActionHandler;
import com.chinarewards.gwt.license.server.login.UpdatelastLoginRoleActionHandler;

/**
 * 
 * @author yanrui
 * 
 */
public class ActionModule extends ActionHandlerModule {

	@Override
	protected void configureHandlers() {
		// login module
		bindHandler(LoginRequest.class, LoginActionHandler.class);
		// 记录最后一次登录role
		bindHandler(LastLoginRoleRequest.class,
				UpdatelastLoginRoleActionHandler.class);

		// 登录验证token
		bindHandler(TokenValidRequest.class, TokenValidActionHandler.class);

		// 授权列表
		bindHandler(SearchLicenseRequest.class, SearchLicenseHandler.class);
		bindHandler(SearchLicenseByIdRequest.class,
				SearchLicenseByIdHandler.class);

		// 授权证书编辑
		bindHandler(EditLicenseRequest.class, EditLicenseHandler.class);
		// 授权证书删除
		bindHandler(DeleteLicenseRequest.class, DeleteLicenseHandler.class);

		// // 证书Key列表
		// bindHandler(SearchCertificateRequest.class,
		// SearchCertificateHandler.class);
		// bindHandler(SearchCertificateByIdRequest.class,
		// SearchCertificateByIdHandler.class);
		// bindHandler(EditCertificateRequest.class,
		// EditCertificateHandler.class);
		// bindHandler(DeleteCertificateRequest.class,
		// DeleteCertificateHandler.class);
	}
}
