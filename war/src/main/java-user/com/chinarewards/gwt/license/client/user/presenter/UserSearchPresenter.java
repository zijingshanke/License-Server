/**
 * 
 */
package com.chinarewards.gwt.license.client.user.presenter;

import java.util.Map;

import com.chinarewards.gwt.license.client.mvp.Display;
import com.chinarewards.gwt.license.client.mvp.Presenter;
import com.chinarewards.gwt.license.client.user.model.UserVo;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.view.client.AsyncDataProvider;

/**
 * @author yanxin
 * @since 0.0.1 2010-09-25
 */
public interface UserSearchPresenter extends
		Presenter<UserSearchPresenter.UserSearchDisplay> {

	public static interface UserSearchDisplay extends Display {

		public HasValue<String> getAccountName();

		public HasValue<String> getEnterpriseName();

		public HasValue<String> getMobile();

		public HasValue<String> getEmail();

		public String getStatus();

		public HasClickHandlers getSearchHandlers();

		public HasClickHandlers getResetHandlers();

		public HasClickHandlers getAddHandlers();

		public HasClickHandlers getActiveHandlers();

		public HasClickHandlers getLogOffHandlers();
		public HasClickHandlers getDeleteHandlers();

		public HasClickHandlers getResetPwdHandlers();

//		public HasClickHandlers getSearchSubAccount();

		public HasClickHandlers getUpdateHandlers();

		public void setListViewAdapter(
				AsyncDataProvider<UserVo> listViewAdapter);

		/**
		 * clean up the input.
		 */
		public void clean();

		public Map<String, UserVo> getSelectedUsers();

		public HasClickHandlers getChooseHandlers();

		public HasClickHandlers getTextChooseHandlers();

		public String getEnterpriseId();

		public void setEnterpriseInfo(String enterpriseId, String enterpriseName);

		//public void refresh();

		public void initUserStatus(Map<String, String> map);
		void setDataCount(String text);
	}
}
