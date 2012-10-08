
package com.chinarewards.gwt.license.client.license.request;

import net.customware.gwt.dispatch.shared.Action;

/**
 * @author yanrui
 */
public class SearchLicenseByIdRequest implements
		Action<SearchLicenseByIdResponse> {

	private String id;

	public SearchLicenseByIdRequest() {
	}

	public SearchLicenseByIdRequest(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
