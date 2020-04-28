package com.mindtree.productcartbackend.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** 
 * 
 * @author M1034075
 *
 */
@Table(name="product_cart")
@Entity
@EntityListeners(value = { AuditingEntityListener.class })
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCart implements Serializable {

	/**
	 * For Serializing the class
	 */
	private static final long serialVersionUID = -9015483053324042562L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	@Setter(value = AccessLevel.PUBLIC)
	@Getter
	private Long cartID;
	
	@Column(name = "name", nullable = false, unique = false)
	@Setter(value = AccessLevel.PUBLIC)
	@Getter
	private String productName;	
	
	@Column(name = "count", nullable = false, unique = false)
	@Setter(value = AccessLevel.PUBLIC)
	@Getter
	private int productCount;
	
	@Column(name = "price", nullable = false, unique = false)
	@Setter(value = AccessLevel.PUBLIC)
	private double price;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH,
			CascadeType.DETACH })
	@JoinColumn(name = "product_id")
	@Setter(value = AccessLevel.PUBLIC)
	@Getter
	private Products products;
	
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH,
			CascadeType.DETACH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@Setter(value = AccessLevel.PUBLIC)
	@Getter
	private Users user;
	
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
