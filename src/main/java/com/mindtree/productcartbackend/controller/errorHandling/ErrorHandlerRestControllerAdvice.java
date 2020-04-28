package com.mindtree.productcartbackend.controller.errorHandling;

import java.time.Instant;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.google.common.base.Throwables;
import com.mindtree.productcartbackend.exceptions.BadRequestException;
import com.mindtree.productcartbackend.exceptions.DatabaseConflictException;
import com.mindtree.productcartbackend.exceptions.MicroservicesCoordinationException;
import com.mindtree.productcartbackend.exceptions.ProductCartBackendApplicationException;
import com.mindtree.productcartbackend.exceptions.RequestedResourceNotFoundException;
import com.mindtree.productcartbackend.exceptions.ThirdPartyApiConflictException;
import com.mindtree.productcartbackend.exceptions.ThirdPartyNoAuthException;

import lombok.extern.log4j.Log4j2;

@RestControllerAdvice
@Log4j2
public class ErrorHandlerRestControllerAdvice {

	@ExceptionHandler(value = { HttpRequestMethodNotSupportedException.class })
	@ResponseStatus(code = HttpStatus.METHOD_NOT_ALLOWED)
	public ErrorResponse handleApiHttpRequestMethodNotSupportedException(HttpServletRequest request,
			BadRequestException exception) {
		ErrorResponse errorResponse = new ErrorResponse(Instant.now().toEpochMilli(),
				HttpStatus.METHOD_NOT_ALLOWED.value(), HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase(),
				BadRequestException.class.getName(), exception.getMessage(),
				Throwables.getStackTraceAsString(exception), request.getRequestURI());
		log.error(errorResponse);
		return errorResponse;
	}

	@ExceptionHandler(value = { HttpMediaTypeNotSupportedException.class })
	@ResponseStatus(code = HttpStatus.UNSUPPORTED_MEDIA_TYPE)
	public ErrorResponse handleApiHttpMediaTypeNotSupportedException(HttpServletRequest request,
			HttpMediaTypeNotSupportedException exception) {
		ErrorResponse errorResponse = new ErrorResponse(Instant.now().toEpochMilli(),
				HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(), HttpStatus.UNSUPPORTED_MEDIA_TYPE.getReasonPhrase(),
				BadRequestException.class.getName(), exception.getMessage(),
				Throwables.getStackTraceAsString(exception), request.getRequestURI());
		log.error(errorResponse);
		return errorResponse;
	}

	@ExceptionHandler(value = { BadRequestException.class })
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ErrorResponse handleApiBadRequestException(HttpServletRequest request, BadRequestException exception) {
		ErrorResponse errorResponse = new ErrorResponse(Instant.now().toEpochMilli(), HttpStatus.BAD_REQUEST.value(),
				HttpStatus.BAD_REQUEST.getReasonPhrase(), BadRequestException.class.getName(), exception.getMessage(),
				Throwables.getStackTraceAsString(exception), request.getRequestURI());
		log.error(errorResponse);
		return errorResponse;
	}

	@ExceptionHandler(value = { UnsatisfiedServletRequestParameterException.class })
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ErrorResponse handleApiUnsatisfiedServletRequestParameterException(HttpServletRequest request,
			UnsatisfiedServletRequestParameterException exception) {
		String unsatisfiedParameterGroup = Arrays.asList(exception.getParamConditions()).toString();
		String unsatisfiedParameterConditionGroups = exception.getParamConditionGroups().stream()
				.map(group -> Arrays.asList(group).toString()).collect(Collectors.toList()).toString();
		exception.getParamConditionGroups();
		ErrorResponse errorResponse = new ErrorResponse(Instant.now().toEpochMilli(), HttpStatus.BAD_REQUEST.value(),
				HttpStatus.BAD_REQUEST.getReasonPhrase(), BadRequestException.class.getName(),
				String.format("%s. Unsatisfied parameter condition group : %s. All parameter condition groups : %s",
						exception.getMessage(), unsatisfiedParameterGroup, unsatisfiedParameterConditionGroups),
				Throwables.getStackTraceAsString(exception), request.getRequestURI());
		log.error(errorResponse);
		return errorResponse;
	}

	@ExceptionHandler(value = { MethodArgumentTypeMismatchException.class })
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ErrorResponse handleApiMethodArgumentTypeMismatchException(HttpServletRequest request,
			MethodArgumentTypeMismatchException exception) {
		ErrorResponse errorResponse = new ErrorResponse(Instant.now().toEpochMilli(), HttpStatus.BAD_REQUEST.value(),
				HttpStatus.BAD_REQUEST.getReasonPhrase(), BadRequestException.class.getName(),
				String.format("Failed to convert %s : %s to required type %s for param %s",
						exception.getValue().getClass().getName(), exception.getValue(),
						exception.getRequiredType().getName(), exception.getName()),
				Throwables.getStackTraceAsString(exception), request.getRequestURI());
		log.error(errorResponse);
		return errorResponse;
	}

	@ExceptionHandler(value = { HttpMessageNotReadableException.class })
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ErrorResponse handleApiHttpMessageNotReadableException(HttpServletRequest request,
			HttpMessageNotReadableException exception) {
		ErrorResponse errorResponse = new ErrorResponse(Instant.now().toEpochMilli(), HttpStatus.BAD_REQUEST.value(),
				HttpStatus.BAD_REQUEST.getReasonPhrase(), BadRequestException.class.getName(),
				exception.getRootCause().getMessage(), Throwables.getStackTraceAsString(exception),
				request.getRequestURI());
		log.error(errorResponse);
		return errorResponse;
	}

	@ExceptionHandler(value = { MethodArgumentNotValidException.class })
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ErrorResponse handleApiMethodArgumentNotValidException(HttpServletRequest request,
			MethodArgumentNotValidException exception) {
		ErrorResponse errorResponse = new ErrorResponse(Instant.now().toEpochMilli(), HttpStatus.BAD_REQUEST.value(),
				HttpStatus.BAD_REQUEST.getReasonPhrase(), BadRequestException.class.getName(), exception.getMessage(),
				Throwables.getStackTraceAsString(exception), request.getRequestURI());
		log.error(errorResponse);
		return errorResponse;
	}

	@ExceptionHandler(value = { ConstraintViolationException.class })
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ErrorResponse handleApiConstraintViolationException(HttpServletRequest request,
			ConstraintViolationException exception) {
		Collection<String> violationMessages = exception.getConstraintViolations().stream()
				.map(violation -> violation.getMessage()).collect(Collectors.toList());
		ErrorResponse errorResponse = new ErrorResponse(Instant.now().toEpochMilli(), HttpStatus.BAD_REQUEST.value(),
				HttpStatus.BAD_REQUEST.getReasonPhrase(), BadRequestException.class.getName(), violationMessages,
				Throwables.getStackTraceAsString(exception), request.getRequestURI());
		log.error(errorResponse);
		return errorResponse;
	}

	@ExceptionHandler(value = { DatabaseConflictException.class })
	@ResponseStatus(code = HttpStatus.CONFLICT)
	public ErrorResponse handleApiDatabaseConflictException(HttpServletRequest request,
			DatabaseConflictException exception) {
		ErrorResponse errorResponse = new ErrorResponse(Instant.now().toEpochMilli(), HttpStatus.CONFLICT.value(),
				HttpStatus.CONFLICT.getReasonPhrase(), DatabaseConflictException.class.getName(),
				exception.getMessage(), Throwables.getStackTraceAsString(exception), request.getRequestURI());
		log.error(errorResponse);
		return errorResponse;
	}

	@ExceptionHandler(value = { ThirdPartyApiConflictException.class })
	@ResponseStatus(code = HttpStatus.CONFLICT)
	public ErrorResponse handleApiThirdPartyApiConflictException(HttpServletRequest request,
			ThirdPartyApiConflictException exception) {
		ErrorResponse errorResponse = new ErrorResponse(Instant.now().toEpochMilli(), HttpStatus.CONFLICT.value(),
				HttpStatus.CONFLICT.getReasonPhrase(), ThirdPartyApiConflictException.class.getName(),
				exception.getMessage(), Throwables.getStackTraceAsString(exception), request.getRequestURI());
		log.error(errorResponse);
		return errorResponse;
	}

	@ExceptionHandler(value = { DataIntegrityViolationException.class })
	@ResponseStatus(code = HttpStatus.CONFLICT)
	public ErrorResponse handleApiDataIntegrityViolationException(HttpServletRequest request,
			DataIntegrityViolationException exception) {
		ErrorResponse errorResponse = new ErrorResponse(Instant.now().toEpochMilli(), HttpStatus.CONFLICT.value(),
				HttpStatus.CONFLICT.getReasonPhrase(), DatabaseConflictException.class.getName(),
				exception.getRootCause().getMessage(), Throwables.getStackTraceAsString(exception),
				request.getRequestURI());
		log.error(errorResponse);
		return errorResponse;
	}

	@ExceptionHandler(value = { RequestedResourceNotFoundException.class })
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ErrorResponse handleApiRequestedResourceNotFoundException(HttpServletRequest request,
			RequestedResourceNotFoundException exception) {
		ErrorResponse errorResponse = new ErrorResponse(Instant.now().toEpochMilli(), HttpStatus.NOT_FOUND.value(),
				HttpStatus.NOT_FOUND.getReasonPhrase(), exception.getClass().getName(),
				String.format("%s not found with %s : %s", exception.getResourceName(), exception.getFieldName(),
						exception.getFieldValue()),
				Throwables.getStackTraceAsString(exception), request.getRequestURI());
		log.error(errorResponse);
		return errorResponse;
	}

	@ExceptionHandler(value = { ThirdPartyNoAuthException.class })
	@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
	public ErrorResponse handleApiThirdPartyNoAuthException(HttpServletRequest request,
			ThirdPartyNoAuthException exception) {
		ErrorResponse errorResponse = new ErrorResponse(Instant.now().toEpochMilli(), HttpStatus.NOT_FOUND.value(),
				HttpStatus.NOT_FOUND.getReasonPhrase(), exception.getClass().getName(), exception.getMessage(),
				Throwables.getStackTraceAsString(exception), request.getRequestURI());
		log.error(errorResponse);
		return errorResponse;
	}

	@ExceptionHandler(value = { MicroservicesCoordinationException.class })
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorResponse handleApiMicroservicesCoordinationException(HttpServletRequest request,
			MicroservicesCoordinationException exception) {
		ErrorResponse errorResponse = new ErrorResponse(Instant.now().toEpochMilli(),
				HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
				exception.getClass().getName(), exception.getMessage(), Throwables.getStackTraceAsString(exception),
				request.getRequestURI());
		log.error(errorResponse);
		return errorResponse;
	}

	@ExceptionHandler(value = { ProductCartBackendApplicationException.class })
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorResponse handleApiAppException(HttpServletRequest request, ProductCartBackendApplicationException exception) {
		ErrorResponse errorResponse = new ErrorResponse(Instant.now().toEpochMilli(),
				HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
				exception.getClass().getName(), exception.getMessage(), Throwables.getStackTraceAsString(exception),
				request.getRequestURI());
		log.error(errorResponse);
		return errorResponse;
	}

	@ExceptionHandler(value = { RuntimeException.class })
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorResponse handleApiRuntimeException(HttpServletRequest request, RuntimeException exception) {
		ErrorResponse errorResponse = new ErrorResponse(Instant.now().toEpochMilli(),
				HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
				exception.getClass().getName(), exception.getMessage(), Throwables.getStackTraceAsString(exception),
				request.getRequestURI());
		log.error(errorResponse);
		return errorResponse;
	}

	@ExceptionHandler(value = { Throwable.class })
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorResponse handleApiThrowable(HttpServletRequest request, Throwable exception) {
		ErrorResponse errorResponse = new ErrorResponse(Instant.now().toEpochMilli(),
				HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
				exception.getClass().getName(), exception.getMessage(), Throwables.getStackTraceAsString(exception),
				request.getRequestURI());
		log.error(errorResponse);
		return errorResponse;
	}
}

