package com.altimetrik.stackoverflow.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
@Getter
public class AuthenticationException extends RuntimeException {

	private static final long serialVersionUID = 414596852911301266L;

	private final String errorMsg;
	private final HttpStatus httpStatus;
	private final String source;
	private final Date date;

	public AuthenticationException(String errorMsg, HttpStatus httpStatus, String source, Date date) {
		this.errorMsg = errorMsg;
		this.httpStatus = httpStatus;
		this.source = source;
		this.date = date;
	}
}
