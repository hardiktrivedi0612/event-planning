package com.planning.event.service;

import java.util.List;

import com.planning.event.domain.EventQuoteResponse;

/**
 * TODO
 * 
 * @author hatrivedi
 * @date Nov 26, 2018
 */
public interface EventQuoteTransactionalService {

	public String saveEventQuote(EventQuoteResponse eventQuote);

	public EventQuoteResponse getEventQuote(String quoteId);

	public List<EventQuoteResponse> getAllEventQuotes();

}
