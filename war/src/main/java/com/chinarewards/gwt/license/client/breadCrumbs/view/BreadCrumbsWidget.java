package com.chinarewards.gwt.license.client.breadCrumbs.view;

import java.util.List;

import com.chinarewards.gwt.license.client.breadCrumbs.model.MenuBreadVo;
import com.chinarewards.gwt.license.client.breadCrumbs.presenter.BreadCrumbsPresenter.BreadCrumbsDisplay;
import com.chinarewards.gwt.license.client.widget.Span;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

public class BreadCrumbsWidget extends Composite implements BreadCrumbsDisplay {
	
	@UiField
	Panel titleText;
	
	@UiField
	Anchor goHistory;
	public BreadCrumbsWidget() {
		initWidget(uiBinder.createAndBindUi(this));

	}

	interface ChooseStaffPanelBinder extends
			UiBinder<Widget, BreadCrumbsWidget> {
	}

	private static ChooseStaffPanelBinder uiBinder = GWT
			.create(ChooseStaffPanelBinder.class);

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public void setTitleText(List<MenuBreadVo> menuBreadVo) {
		titleText.clear();
		for (int i = 0; i < menuBreadVo.size(); i++) {
			final MenuBreadVo menu=menuBreadVo.get(i);
			if(menu.getMenuUrl()!=null)
			{
				Anchor a=new Anchor();
				a.setText(menu.getMenuName());
				a.setStyleName("breadcrumbsAnchor");
				a.addClickHandler(new ClickHandler() {
					
					@Override
					public void onClick(ClickEvent event) {
						menu.getMenuUrl().execute();
						
					}
				});
				titleText.add(a);
			}
			else
			{
				Span s=new Span();
				s.setText(menu.getMenuName());
				s.setStyleName("breadcrumbsSpan");
				titleText.add(s);
			}
			if(i!=menuBreadVo.size()-1)
			{
				HTML h=new HTML("-");
				h.setStyleName("breadcrumbsdiv");
				titleText.add(h);
			}
			
		}

	}

	@Override
	public HasClickHandlers getGoHistory() {
		return goHistory;
	}

}
