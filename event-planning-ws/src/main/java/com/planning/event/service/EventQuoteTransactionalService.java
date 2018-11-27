package com.planning.event.service;

import java.util.List;

import com.planning.event.domain.EventQuoteDetails;

/**
 * Service interface for transactional actions that have to be performed for
 * event quotes
 * <p>
 * This interface is defined so that any type of implementation for the
 * transactional data store can be implemented for this. Eg: Text, persistent or
 * memory
 * 
 * @author hatrivedi
 * @date Nov 26, 2018
 */
public interface EventQuoteTransactionalService {

	/**
	 * Method to save an event quote
	 * 
	 * @author hatrivedi
	 * @date Nov 27, 2018
	 * @param eventQuote
	 * @return
	 */
	public String saveEventQuote(EventQuoteDetails eventQuote);

	/**
	 * Method to fetch an event quote given the quote id
	 * 
	 * @author hatrivedi
	 * @date Nov 27, 2018
	 * @param quoteId
	 * @return
	 */
	public EventQuoteDetails getEventQuote(String quoteId);

	/**
	 * Method to fetch all of the event quotes from the data store
	 * 
	 * @author hatrivedi
	 * @date Nov 27, 2018
	 * @return
	 */
	public List<EventQuoteDetails> getAllEventQuotes();

}
