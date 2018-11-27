package com.planning.event.service;

import java.util.List;

import com.planning.event.domain.EventQuoteRequest;
import com.planning.event.domain.EventQuoteResponse;
import com.planning.event.exception.EventQuoteNotFoundException;
import com.planning.event.exception.ValidationException;

/**
 * TODO
 * 
 * @author hatrivedi
 * @date Nov 26, 2018
 */
public interface EventQuoteService {

	/**
	 * TODO
	 * 
	 * @author hatrivedi
	 * @date Nov 26, 2018
	 * @param request
	 * @return
	 * @throws ValidationException 
	 */
	public EventQuoteResponse createEventQuote(EventQuoteRequest request) throws ValidationException;

	/**
	 * TODO
	 * 
	 * @author hatrivedi
	 * @date Nov 26, 2018
	 * @param request
	 * @return
	 */
	public EventQuoteResponse getEventQuote(String quoteId) throws EventQuoteNotFoundException;

	/**
	 * TODO
	 * 
	 * @author hatrivedi
	 * @date Nov 26, 2018
	 * @param request
	 * @return
	 */
	public List<EventQuoteResponse> getAllEventQuotes();
}
