package com.planning.event.exception;

/**
 * @author hatrivedi
 * @date Nov 26, 2018
 * @since 2.5
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
