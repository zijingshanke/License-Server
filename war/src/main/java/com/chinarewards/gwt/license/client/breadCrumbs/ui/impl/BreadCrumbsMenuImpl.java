package com.chinarewards.gwt.license.client.breadCrumbs.ui.impl;

import java.util.ArrayList;
import java.util.List;

import com.chinarewards.gwt.license.client.breadCrumbs.model.MenuBreadVo;
import com.chinarewards.gwt.license.client.breadCrumbs.ui.BreadCrumbsMenu;
import com.chinarewards.gwt.license.client.core.ui.MenuItem;

public class BreadCrumbsMenuImpl implements BreadCrumbsMenu {

	List<List<MenuBreadVo>> allList = new ArrayList<List<MenuBreadVo>>();
	List<MenuBreadVo> toplist = new ArrayList<MenuBreadVo>();
	List<MenuBreadVo> list = new ArrayList<MenuBreadVo>();
	List<MenuBreadVo> childlist = new ArrayList<MenuBreadVo>();

	@Override
	public void addBreadCrumbsItem(String name, MenuItem menuItem) {
		MenuBreadVo menuBreadVo = new MenuBreadVo();
		menuBreadVo.setMenuName(name);
		menuBreadVo.setMenuUrl(menuItem);
		list.add(menuBreadVo);

	}

	@Override
	public void addBreadCrumbsItemTop(String name, MenuItem menuItem) {
		MenuBreadVo menuBreadVo = new MenuBreadVo();
		menuBreadVo.setMenuName(name);
		menuBreadVo.setMenuUrl(menuItem);
		toplist.add(menuBreadVo);

	}

	@Override
	public List<MenuBreadVo> getBreadCrumbsItem() {
		if (list.size() <= 1) {
			list.addAll(0, toplist);
		}
		List<MenuBreadVo> newList = new ArrayList<MenuBreadVo>();
		List<MenuBreadVo> showList = new ArrayList<MenuBreadVo>();
		for (MenuBreadVo vo : list) {
			newList.add(vo);
			showList.add(vo);
		}
		for (MenuBreadVo vo :childlist) {
			showList.add(vo);
		}
		allList.add(newList);
		return showList;

	}

	@Override
	public void cleanBreadCrumbsItem() {
		list.clear();
		childlist.clear();
	}

	@Override
	public void cleanBreadCrumbsItemTop() {
		toplist.clear();

	}

	@Override
	public void cleanBreadCrumbsItemAll() {
		toplist.clear();
		list.clear();
		allList.clear();
		childlist.clear();

	}

	@Override
	public List<MenuBreadVo> getHistoryBreadCrumbsItem() {
		
		//比较是否重复,去重复,记录需要去掉的index
		List<Integer> indexlt=new ArrayList<Integer>();
		for (int i = 0; i < allList.size()-1; i++) {
			List<MenuBreadVo> menuListA=allList.get(i);
			List<MenuBreadVo> menuListB=allList.get(i+1);
			boolean fal=true;
			if(menuListA.size()==menuListB.size())
			{
				
				for (int j = 0; j < menuListA.size(); j++) {
					MenuBreadVo voA=menuListA.get(j); 
					MenuBreadVo voB=menuListB.get(j); 
					if(voA.getMenuUrl()!=voB.getMenuUrl())
					{
						fal=false;
					}
				}
			}
			if(fal==true)
			{
				indexlt.add(i);
			}
		}
		for (int i = 0; i < indexlt.size(); i++) {
			int index=indexlt.get(i);
			allList.remove(index);
		}
		
		
		List<MenuBreadVo> volist = null;
		if (allList.size() > 1) {
			if(childlist.size()>0)
				volist = allList.get(allList.size() - 1);
			else
				volist = allList.get(allList.size() - 2);
			
			allList.remove(allList.size() - 1);

		}
		else if(childlist.size()>0)
		{
			volist=allList.get(0);
		}
		return volist;
	}

	@Override
	public void setChildName(String name) {
		childlist.clear();
		MenuBreadVo vo = new MenuBreadVo();
		vo.setMenuName(name);
		vo.setMenuUrl(null);
		childlist.add(vo);


	}

	@Override
	public void cleanChildName() {
		childlist.clear();
		
	}
}
