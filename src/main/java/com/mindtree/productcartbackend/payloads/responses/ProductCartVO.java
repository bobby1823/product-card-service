package com.mindtree.productcartbackend.payloads.responses;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.mindtree.productcartbackend.payloads.constants.ApiResponseDataFieldFormats;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder(value = { "id", "name", "count", "price", "createdAt", "updatedAt" }, alphabetic = true)
public class ProductCartVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4465879139835187153L;

	@NotNull
	@JsonProperty(value = "id", access = Access.READ_ONLY)
	private Long id;
	
	@NotNull
	@JsonProperty(value = "name", access = Access.READ_ONLY)
	private String name;
	
	@NotNull
	@JsonProperty(value = "count", access = Access.READ_ONLY)
	private int count;
	
	@NotNull
	@JsonProperty(value = "price", access = Access.READ_ONLY)
	private int price;
	
	@NotNull
	@JsonFormat(shape = Shape.STRING, pattern = ApiResponseDataFieldFormats.CREATED_AT_DATE_FORMAT)
	@JsonProperty(value = "createdAt", access = Access.READ_ONLY)
	private Date createdAt;

	@NotNull
	@JsonFormat(shape = Shape.STRING, pattern = ApiResponseDataFieldFormats.UPDATED_AT_DATE_FORMAT)
	@JsonProperty(value = "updatedAt", access = Access.READ_ONLY)
	private Date updatedAt;
}
