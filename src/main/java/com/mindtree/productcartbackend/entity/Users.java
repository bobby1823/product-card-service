package com.mindtree.productcartbackend.entity;

import java.io.Serializable;
import java.util.Collection;

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

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name="users")
@Entity
@EntityListeners(value = { AuditingEntityListener.class })
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users implements Serializable {

	/**
	 * For Serializing the class
	 */
	private static final long serialVersionUID = -605228647911511428L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id", nullable = false, unique = true)
	@Setter(value = AccessLevel.PUBLIC)
	@Getter(value = AccessLevel.PUBLIC)
	private Long id;

	@Column(name = "username", nullable = false, unique = false)
	@Setter(value = AccessLevel.PUBLIC)
	@Getter(value = AccessLevel.PUBLIC)
	private String username;
	
	@Column(name = "password", nullable = false, unique = false)
	@Setter(value = AccessLevel.PUBLIC)
	@Getter(value = AccessLevel.PUBLIC)
	private String password;
	
	@OneToMany(mappedBy="user" ,fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH,
			CascadeType.DETACH, CascadeType.REMOVE })
	private Collection<ProductCart> productCart;
}
