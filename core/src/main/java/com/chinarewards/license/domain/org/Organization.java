package com.chinarewards.license.domain.org;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import com.chinarewards.license.domain.user.SysUser;

/**
 * Abstract representation of an individual, a department, or else.
 * 
 * @author yanxin
 * @since 1.0
 */
@Entity
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING, name = "ORG_TYPE")
public class Organization implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5199696929828602117L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;

	/**
	 * The name of {@link Corporation} or {@link Department} or others who
	 * extends {@link Organization}.
	 */
	private String name;

	/**
	 * The description.
	 */
	private String description;
	
	private String deleteMarkConstant;

	private Date createdAt;

	private Date lastModifiedAt;

	@ManyToOne
	private SysUser createdBy;

	@ManyToOne
	private SysUser lastModifiedBy;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getLastModifiedAt() {
		return lastModifiedAt;
	}

	public void setLastModifiedAt(Date lastModifiedAt) {
		this.lastModifiedAt = lastModifiedAt;
	}

	public SysUser getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(SysUser createdBy) {
		this.createdBy = createdBy;
	}

	public SysUser getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(SysUser lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public String getDeleteMarkConstant() {
		return deleteMarkConstant;
	}

	public void setDeleteMarkConstant(String deleteMarkConstant) {
		this.deleteMarkConstant = deleteMarkConstant;
	}
	
	
}
