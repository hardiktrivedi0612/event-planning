package com.planning.event.exception;

/**
 * 
 * Exception which is thrown when the user requests for a quote with a reference
 * id that does not exist in our records
 * 
 * @author hatrivedi
 * @date Nov 26, 2018
 */
public class EventQuoteNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;
	private String quoteId;

	/**
	 * @param quoteId
	 */
	public EventQuoteNotFoundException(String quoteId) {
		super();
		this.setQuoteId(quoteId);
	}

	/**
	 * @return the quoteId
	 */
	public String getQuoteId() {
		return quoteId;
	}

	/**
	 * @param quoteId
	 *            the quoteId to set
	 */
	public void setQuoteId(String quoteId) {
		this.quoteId = quoteId;
	}

}
