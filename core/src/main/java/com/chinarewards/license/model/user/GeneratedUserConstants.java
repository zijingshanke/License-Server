/**
 * 
 */
package com.chinarewards.license.model.user;

/**
 * @author nicho	
 * @since 2012年2月15日 17:38:10
 */
public enum  GeneratedUserConstants {


	Success("成功"), Failure("失败"),UsernamePresence("用户存在"),UsernameRepeat("用户名重复");

	private GeneratedUserConstants(String displayName) {
		this.displayName = displayName;
	}

	String displayName;

	public String getDisplayName() {
		return this.displayName;
	}
	
}
