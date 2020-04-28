package com.mindtree.productcartbackend.controller.errorHandling;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = { "timestamp", "status", "error", "exception", "message", "stacktrace",
		"path" }, allowGetters = true)
public class ErrorResponse {

	private Long timestamp;

	private Integer status;

	private String error;

	private String exception;

	private Object message;

	private String stacktrace;

	private String path;
}
