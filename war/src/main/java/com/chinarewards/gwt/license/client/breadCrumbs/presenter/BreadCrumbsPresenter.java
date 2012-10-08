package com.chinarewards.gwt.license.client.breadCrumbs.presenter;

import java.util.List;

import com.chinarewards.gwt.license.client.breadCrumbs.model.MenuBreadVo;
import com.chinarewards.gwt.license.client.mvp.Display;
import com.chinarewards.gwt.license.client.mvp.Presenter;
import com.google.gwt.event.dom.client.HasClickHandlers;

public interface BreadCrumbsPresenter extends	Presenter<BreadCrumbsPresenter.BreadCrumbsDisplay> {

	void setChildName(String name);
	void cleanChildName();
	/**
	 * 加载List页面,调用
	 */
	void loadListPage();
	/**
	 * 加载其他子页面,调用,传入子菜单的名称
	 */
	void loadChildPage(String menuName);
	/**
	 * 返回上一页
	 */
	void getGoHistory();
	public static interface BreadCrumbsDisplay extends Display {

		void setTitleText(List<MenuBreadVo> menuBreadVo);
		HasClickHandlers getGoHistory();
	}
}
