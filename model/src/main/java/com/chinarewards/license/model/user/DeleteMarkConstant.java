package com.chinarewards.license.model.user;

/**
 * 标识一条数据是否是删除了
 * 
 * @author roger
 * 
 * @since 1.0.0 2010-09-19
 */
public enum DeleteMarkConstant {

	/**
	 * 已经删除
	 */
	DELETED,

	/**
	 * 生效的数据(创建CRM的会员)
	 */
	ACTIVING;

	/**
	 * 未创建CRM的会员
	 * 
	 * TODO define this constant.
	 */
	// UNACTIVING;
}
