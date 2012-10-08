package com.chinarewards.gwt.license.client.core.impl;

import com.chinarewards.gwt.license.model.user.UserRoleVo;

/**
 * 
 * 
 * 
 * @author cyril
 * @since 0.2.0
 */
public class HardCodedMemoryMenuRoleStore extends InMemoryMenuRoleStore {

	/**
	 * 
	 */
	public HardCodedMemoryMenuRoleStore() {
		super();

		initialize();
	}

	protected void initialize() {

		initMenuForCorpAdmin();

	}


	/**
	 * Initialize menu for corporation admin.
	 */
	protected void initMenuForCorpAdmin() {

		this.addMenusToRole(UserRoleVo.CORP_ADMIN.name(), new String[] {});

	}



}
