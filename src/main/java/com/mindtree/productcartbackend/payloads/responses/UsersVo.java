package com.mindtree.productcartbackend.payloads.responses;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author M1034075
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder(value = { "id", "name", "password" }, alphabetic = true)
public class UsersVo implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonProperty(value = "id", access = Access.READ_ONLY)
	private Long id;
	
	@JsonProperty(value = "name", access = Access.READ_ONLY)
	private String name;
	
	@JsonProperty(value = "password", access = Access.READ_ONLY)
	private String password;

	
}
