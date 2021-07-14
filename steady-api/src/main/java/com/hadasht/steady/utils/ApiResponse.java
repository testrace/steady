package com.hadasht.steady.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.Optional;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class ApiResponse {

	private static final String SUCCESS = "SUCCESS";
	private static final String FAIL = "FAIL";

	private final String code;
	private String message;
	private Object data;

	private ApiResponse(String code, Object data) {
		this.data = data;
		this.code = code;
	}

	private ApiResponse(String code) {
		this.code = code;
	}

	private ApiResponse(String code, String message) {
		this.code = code;
		this.message = message;
	}

	private ApiResponse(String code, Object data, String message) {
		this.data = data;
		this.code = code;
		this.message = message;
	}

	public static ApiResponse success() {
		return new ApiResponse(SUCCESS);
	}

	public static ApiResponse success(Object data) {
		return new ApiResponse(SUCCESS, data);
	}

	public static ApiResponse success(String message, Object data) {
		return new ApiResponse(SUCCESS, data, message);
	}

	public static ApiResponse fail(String message) {
		return new ApiResponse(FAIL, message);
	}

	public static ApiResponse fail(List<ObjectError> allErrors) {
		return fail(allErrors.get(0));
	}

	public static ApiResponse fail(ObjectError error) {
		if (error instanceof FieldError) {
			return fail(error.getDefaultMessage(), ((FieldError) error).getField());
		}
		return fail(error.getDefaultMessage(), error.getCodes());
	}

	public static ApiResponse fail(String message, Object data) {
		return new ApiResponse(FAIL, data, message);
	}

	public static ApiResponse ifPresent(Object data, String message) {
		return Optional.ofNullable(data).map(o -> new ApiResponse(SUCCESS, o)).orElseGet(() -> new ApiResponse(FAIL, message));
	}

	public static ApiResponse ifPresent(boolean ifPresent, Object data, String message) {
		if (ifPresent) {
			return new ApiResponse(SUCCESS, data);
		} else {
			return new ApiResponse(FAIL, message);
		}
	}



}
