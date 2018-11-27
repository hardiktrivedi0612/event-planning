package com.planning.event.service;

import java.util.List;

import com.planning.event.domain.EventQuoteDetails;

/**
 * TODO
 * 
 * @author hatrivedi
 * @date Nov 26, 2018
 */
public interface EventQuoteTransactionalService {

	public String saveEventQuote(EventQuoteDetails eventQuote);

	public EventQuoteDetails getEventQuote(String quoteId);

	public List<EventQuoteDetails> getAllEventQuotes();

}
