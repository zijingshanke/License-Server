package com.chinarewards.gwt.license.server.user;

import java.util.List;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.slf4j.Logger;

import com.chinarewards.license.domain.user.SysUser;
import com.chinarewards.license.model.user.SearchUserInfo;
import com.chinarewards.license.model.user.UserSearchCriteria;
import com.chinarewards.license.model.user.UserSearchResult;
import com.chinarewards.license.service.user.UserService;
import com.chinarewards.gwt.license.client.user.UserSearchRequest;
import com.chinarewards.gwt.license.client.user.UserSearchResponse;
import com.chinarewards.gwt.license.client.user.model.UserSearchResultVo;
import com.chinarewards.gwt.license.client.user.model.UserSearchVo;
import com.chinarewards.gwt.license.client.user.model.UserVo;
import com.chinarewards.gwt.license.client.util.StringUtil;
import com.chinarewards.gwt.license.server.BaseActionHandler;
import com.chinarewards.gwt.license.server.logger.InjectLogger;
import com.google.inject.Inject;

/**
 * The handler to correspond the UserSearchRequest.
 * 
 * @author yanxin
 * @since 1.0.0 2010-09-26
 */
public class UserSearchActionHandler extends
		BaseActionHandler<UserSearchRequest, UserSearchResponse> {

	@InjectLogger
	Logger logger;
	UserService userService;

	@Inject
	public UserSearchActionHandler(UserService userService) {
		this.userService = userService;
	}

	@Override
	public UserSearchResponse execute(UserSearchRequest request,
			ExecutionContext response) throws DispatchException {

		UserSearchVo searchVo = request.getCriteria();
		UserSearchCriteria criteria = new UserSearchCriteria();
		if (searchVo != null) {
			criteria.setAccountName(StringUtil.trim(searchVo.getAccountName()));
			criteria.setEmail(StringUtil.trim(searchVo.getEmail()));
			criteria.setEnterpriseId(searchVo.getEnterpriseId());
			criteria.setMobile(StringUtil.trim(searchVo.getMobile()));
			criteria.setStatus(searchVo.getStatus());
			criteria.getPaginationDetail().setStart(searchVo.getStart());
			criteria.getPaginationDetail().setLimit(searchVo.getLength());
		}
		UserSearchResult result = userService.searchHrAdminUserPaging(criteria);
		// UserSearchResult result = mockData();
		List<SearchUserInfo> hrUsers = result.getResult();
		int count = result.getTotal();
		logger.debug("The seach result: list size- {}, count-{}", new Object[] {
				hrUsers != null ? hrUsers.size() : 0, count });
		UserSearchResultVo resultVo = new UserSearchResultVo();
		if (hrUsers != null) {
			for (SearchUserInfo userInfo : hrUsers) {
				UserVo vo = adapterUserVo(userInfo.getUser());
				vo.setEnterpriseName(userInfo.getEnterpriseName());
				vo.setBalance(userInfo.getBalance());
				resultVo.getList().add(vo);
			}
		}
		resultVo.setTotal(count);
		return new UserSearchResponse(resultVo);

	}

	private UserVo adapterUserVo(SysUser user) {
		UserVo vo = new UserVo();
		vo.setCreatedAt(user.getCreatedAt());
		vo.setId(user.getId());
		vo.setName(user.getUserName());
		vo.setStatus(user.getStatus() == null ? "" : user.getStatus()
				.getDisplayName());
		return vo;
	}

	@Override
	public Class<UserSearchRequest> getActionType() {
		return UserSearchRequest.class;
	}

	@Override
	public void rollback(UserSearchRequest request,
			UserSearchResponse response, ExecutionContext context)
			throws DispatchException {
	}

}
