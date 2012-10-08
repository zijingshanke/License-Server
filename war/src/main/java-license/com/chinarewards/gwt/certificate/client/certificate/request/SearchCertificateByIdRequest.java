
package com.chinarewards.gwt.certificate.client.certificate.request;

import net.customware.gwt.dispatch.shared.Action;

/**
 * @author yanrui
 */
public class SearchCertificateByIdRequest implements
		Action<SearchCertificateByIdResponse> {

	private String id;

	public SearchCertificateByIdRequest() {
	}

	public SearchCertificateByIdRequest(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
