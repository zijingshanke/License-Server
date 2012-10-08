package com.chinarewards.gwt.certificate.client.certificate.model;

import java.io.Serializable;
import java.util.Date;

import com.chinarewards.gwt.certificate.client.certificate.model.CertificateCriteria.CertificateStatus;
import com.chinarewards.gwt.license.util.StringUtil;

public class CertificateVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String name; // 礼品名
	private String summary; // 礼品概述
	private String dispatchcycle;// 发货周期
	private String explains; // 兑换说明
	private String notes; // 注意事项
	private String type; // 礼品类型
	private String brand;// 品牌
	private String photo; // 图片
	private String source; //  供货方式
	private int integral; // 积分
	private int stock; // 库存
	private String business; // 供应商名称
	private String address; // 供应商地址
	private String tell; // 供应商电话
	private String servicetell;// 服务电话
	private CertificateStatus status; // 状态（上下架）
	private boolean deleted; // 删除状态
	private Date indate; // 有效截止期

	public static String TYPE_1 = "1";// 实物
	public static String TYPE_2 = "2";// 虚拟

	public CertificateVo() {
	}

	public String getTypeText() {
		if (type != null) {
			if (type.equals(TYPE_1)) {
				return "实物";
			} else if (type.equals(TYPE_2)) {
				return "虚拟";
			} else {
				return "未定义";
			}
		}
		return "";
	}
	
	public String getSourceText() {
		if (source != null) {
			if (StringUtil.trim(source).equals("inner")) {
				return "内部直接提供";
			}
			if (StringUtil.trim(source).equals("outter")) {
				return "外部货品公司提供";
			}
		}
		return "";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExplains() {
		return explains;
	}

	public void setExplains(String explains) {
		this.explains = explains;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getBusiness() {
		return business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}

	public String getTell() {
		return tell;
	}

	public void setTell(String tell) {
		this.tell = tell;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public CertificateStatus getStatus() {
		return status;
	}

	public void setStatus(CertificateStatus status) {
		this.status = status;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public Date getIndate() {
		return indate;
	}

	public void setIndate(Date indate) {
		this.indate = indate;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getIntegral() {
		return integral;
	}

	public void setIntegral(int integral) {
		this.integral = integral;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getDispatchcycle() {
		return dispatchcycle;
	}

	public void setDispatchcycle(String dispatchcycle) {
		this.dispatchcycle = dispatchcycle;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getServicetell() {
		return servicetell;
	}

	public void setServicetell(String servicetell) {
		this.servicetell = servicetell;
	}
	
	

}
