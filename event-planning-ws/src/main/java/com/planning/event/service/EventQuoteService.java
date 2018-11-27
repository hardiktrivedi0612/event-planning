package com.planning.event.service;

import java.util.List;

import com.planning.event.domain.EventQuoteDetails;
import com.planning.event.domain.EventQuoteRequest;
import com.planning.event.domain.EventQuoteResponse;
import com.planning.event.exception.EventQuoteNotFoundException;
import com.planning.event.exception.ValidationException;

/**
 * Service interface for all service methods related to quote estimation
 * 
 * @author hatrivedi
 * @date Nov 26, 2018
 */
public interface EventQuoteService {

	/**
	 * Method that will perform the following steps:
	 * <p>
	 * 1. Calculate the quote estimate from the given input request
	 * <p>
	 * 2. Save the quote details with input details and quote estimate
	 * <p>
	 * 3. Return the quote estimate and reference id for the quote
	 * 
	 * 
	 * <p>
	 * This will throw a {@link ValidationException} when the input is invalid
	 * 
	 * @author hatrivedi
	 * @date Nov 26, 2018
	 * @param request
	 * @return
	 * @throws ValidationException
	 * @throws EventQuoteNotFoundException
	 */
	public EventQuoteResponse createEventQuote(EventQuoteRequest request)
			throws ValidationException, EventQuoteNotFoundException;

	/**
	 * Method that will fetch the quote details given the quote id provided by
	 * the user.
	 * <p>
	 * This will throw {@link EventQuoteNotFoundException} if there is no quote
	 * estimate for the given reference id
	 * 
	 * @author hatrivedi
	 * @date Nov 26, 2018
	 * @param request
	 * @return
	 */
	public EventQuoteDetails getEventQuote(String quoteId) throws EventQuoteNotFoundException;

	/**
	 * Method that will return all of the quote estimates that have been created
	 * by users
	 * 
	 * @author hatrivedi
	 * @date Nov 26, 2018
	 * @param request
	 * @return
	 */
	public List<EventQuoteDetails> getAllEventQuotes();
}
