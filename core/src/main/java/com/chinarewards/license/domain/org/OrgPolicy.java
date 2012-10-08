/**
 * 
 */
package com.chinarewards.license.domain.org;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

/**
 * The policy of the specified {@link Organization}.Maybe the approval policy,
 * maybe others.
 * 
 * @author yanxin
 * @since 1.0
 */
@Entity
public class OrgPolicy implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7983892375425368962L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;

	@ManyToOne(fetch = FetchType.LAZY)
	private Organization owner;

	/**
	 * Why key? It is sad, "key" was a key word in mysql database.
	 */
	private String key2;

	private String value;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the owner
	 */
	public Organization getOwner() {
		return owner;
	}

	/**
	 * @param owner
	 *            the owner to set
	 */
	public void setOwner(Organization owner) {
		this.owner = owner;
	}

	/**
	 * @return the key
	 */
	public String getKey2() {
		return key2;
	}

	/**
	 * @param key
	 *            the key to set
	 */
	public void setKey2(String key) {
		this.key2 = key;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

}
