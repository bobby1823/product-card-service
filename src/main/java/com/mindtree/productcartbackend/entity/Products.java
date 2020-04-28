package com.mindtree.productcartbackend.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** 
 * 
 * @author M1034075
 *
 */
@Table(name="products")
@Entity
@EntityListeners(value = { AuditingEntityListener.class })
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Products implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	@Setter(value = AccessLevel.PUBLIC)
	Long productId;
	
	@Column(name = "product_name", nullable = false, unique = false)
	@Setter(value = AccessLevel.PUBLIC)
	String productName;
	
	@Column(name = "product_price", nullable = false, unique = false)
	@Setter(value = AccessLevel.PUBLIC)
	double productPrice;
	
	@OneToMany(mappedBy="products" ,fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH,
			CascadeType.DETACH })
	private Collection<ProductCart> productCart;
	
	@Column(name = "created_at", nullable = false, updatable = false)
	@Temporal(value = TemporalType.TIMESTAMP)
	@CreatedDate
	@Setter(value = AccessLevel.NONE)
	private Date createdAt;

	@Column(name = "updated_at", nullable = false)
	@Temporal(value = TemporalType.TIMESTAMP)
	@LastModifiedDate
	@Setter(value = AccessLevel.NONE)
	private Date updatedAt;
}
