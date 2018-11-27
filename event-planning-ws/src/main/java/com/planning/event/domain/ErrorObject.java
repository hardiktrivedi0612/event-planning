package com.planning.event.domain;

/**
 * Domain class for representing the error responses for APIs
 * 
 * @author hatrivedi
 * @date Nov 26, 2018
 */
public class ErrorObject {
	private String code;
	private String message;

	// For less code, we can use Lombok library for generating these
	// getters and setters

	/**
	 * @param code
	 * @param message
	 */
	public ErrorObject(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
