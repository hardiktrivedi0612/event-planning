package com.planning.event.exception;

import java.util.List;

import com.planning.event.domain.ErrorObject;

/**
 * Exception that will be thrown for any input validations that fail
 * 
 * @author hatrivedi
 * @date Nov 26, 2018
 */
public class ValidationException extends Exception {
	private static final long serialVersionUID = 1L;
	private List<ErrorObject> errors;

	/**
	 * @param errors
	 */
	public ValidationException(List<ErrorObject> errors) {
		super();
		this.errors = errors;
	}

	public List<ErrorObject> getErrors() {
		return errors;
	}

	public void setErrors(List<ErrorObject> errors) {
		this.errors = errors;
	}

}
