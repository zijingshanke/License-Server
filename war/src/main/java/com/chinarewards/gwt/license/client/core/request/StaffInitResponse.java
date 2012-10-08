package com.chinarewards.gwt.license.client.core.request;

import net.customware.gwt.dispatch.shared.Result;

public class StaffInitResponse implements Result {

	String photo;
	String name;
	String sex;
	String station;
	String deptName;
	int integral;
	
	
	
	public String getPhoto() {
		return photo;
	}



	public void setPhoto(String photo) {
		this.photo = photo;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getSex() {
		return sex;
	}



	public void setSex(String sex) {
		this.sex = sex;
	}



	public String getStation() {
		return station;
	}



	public void setStation(String station) {
		this.station = station;
	}



	public String getDeptName() {
		return deptName;
	}



	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}



	public int getIntegral() {
		return integral;
	}



	public void setIntegral(int integral) {
		this.integral = integral;
	}



	public StaffInitResponse() {

	}

	

}
