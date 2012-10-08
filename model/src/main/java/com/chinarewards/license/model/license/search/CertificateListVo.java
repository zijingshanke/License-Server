package com.chinarewards.license.model.license.search;

import java.io.Serializable;
import java.util.Date;

import com.chinarewards.license.model.common.PaginationDetail;
import com.chinarewards.license.model.common.SortingDetail;

public class CertificateListVo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The paging detail, contains the info it requires.
	 */
	private PaginationDetail paginationDetail;

	/**
	 * Sorting detail.
	 */
	private SortingDetail sortingDetail;
	private String id;
    private String name;       //礼品名
    private String explains;   //说明
    private String  type;      //礼品类型
    private String source;     //来源
    private String business;   //供应商
    private String address;    //地址
    private String tell;       //电话
    private int    stock;         //库存
    private String photo;      //图片
    private CertificateStatus status;    //状态（上下架）
    private boolean deleted;   //删除状态
    private Date    indate ;      //有效截止期
    private Date    recorddate;   //录入时间
    private String  recorduser; //录入人
    private Date    updatetime;  //修改时间
    private int    integral;  //积分
	
	public int getIntegral() {
		return integral;
	}

	public void setIntegral(int integral) {
		this.integral = integral;
	}

	public CertificateListVo() {
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

	public Date getRecorddate() {
		return recorddate;
	}

	public void setRecorddate(Date recorddate) {
		this.recorddate = recorddate;
	}

	public String getRecorduser() {
		return recorduser;
	}

	public void setRecorduser(String recorduser) {
		this.recorduser = recorduser;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
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

	public PaginationDetail getPaginationDetail() {
		return paginationDetail;
	}

	public void setPaginationDetail(PaginationDetail paginationDetail) {
		this.paginationDetail = paginationDetail;
	}

	public SortingDetail getSortingDetail() {
		return sortingDetail;
	}

	public void setSortingDetail(SortingDetail sortingDetail) {
		this.sortingDetail = sortingDetail;
	}

}