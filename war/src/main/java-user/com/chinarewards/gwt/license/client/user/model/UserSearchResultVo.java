/**
 * 
 */
package com.chinarewards.gwt.license.client.user.model;

import java.util.ArrayList;
import java.util.List;

import com.chinarewards.gwt.license.model.PagedList;

/**
 * Search result with pagination information.
 * 
 * @author yanxin
 * @since 0.0.1 2010-09-25
 */
public class UserSearchResultVo implements PagedList<UserVo> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6995266682561766594L;
	private List<UserVo> list = new ArrayList<UserVo>();
	private int total;

	/**
	 * @return the list
	 */
	public List<UserVo> getList() {
		return list;
	}

	/**
	 * @param list
	 *            the list to set
	 */
	public void setList(List<UserVo> list) {
		this.list = list;
	}

	/**
	 * @return the total
	 */
	public int getTotal() {
		return total;
	}

	/**
	 * @param total
	 *            the total to set
	 */
	public void setTotal(int total) {
		this.total = total;
	}
}
