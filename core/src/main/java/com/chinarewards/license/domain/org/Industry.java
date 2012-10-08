package com.chinarewards.license.domain.org;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * Industry
 * <p>
 * 
 * For example, for both enterprises "KFC" and "McDonald", they are all belong
 * to the "Fast Food" industry.
 * <p>
 * 
 * @author yanxin
 * @since 1.0
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue(value = "industry")
public class Industry extends Organization {

	private static final long serialVersionUID = -8896795998357995582L;
}
