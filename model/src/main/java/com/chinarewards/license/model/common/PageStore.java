package com.chinarewards.license.model.common;

import java.util.List;

/**
 * 使用这个对象对要分页的数据模型进行封装
 * 
 * @author roger
 * @since 1.0.0 2010-09-14
 * @param <T>
 */
public class PageStore<T> {
	private List<T> resultList;
	private int resultCount;

	/**
	 * @return the resultList
	 */
	public List<T> getResultList() {
		return resultList;
	}

	/**
	 * @param resultList
	 *            the resultList to set
	 */
	public void setResultList(List<T> resultList) {
		this.resultList = resultList;
	}

	/**
	 * @return the resultCount
	 */
	public int getResultCount() {
		return resultCount;
	}

	/**
	 * @param resultCount
	 *            the resultCount to set
	 */
	public void setResultCount(int resultCount) {
		this.resultCount = resultCount;
	}
}
