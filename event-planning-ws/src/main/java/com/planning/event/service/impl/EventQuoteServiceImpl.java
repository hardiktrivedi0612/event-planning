package com.planning.event.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.planning.event.domain.EventQuoteRequest;
import com.planning.event.domain.EventQuoteResponse;
import com.planning.event.exception.EventQuoteNotFoundException;
import com.planning.event.service.EventQuoteService;
import com.planning.event.service.EventQuoteTransactionalService;

/**
 * @author hatrivedi
 * @date Nov 26, 2018
 */
@Service
public class EventQuoteServiceImpl implements EventQuoteService {

	private EventQuoteTransactionalService eventQuoteTransactionalService;

	EventQuoteServiceImpl(EventQuoteTransactionalService eventQuoteTransactionalService) {
		this.eventQuoteTransactionalService = eventQuoteTransactionalService;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EventQuoteResponse createEventQuote(EventQuoteRequest request) {

		EventQuoteResponse response = new EventQuoteResponse();

		// 1. Copy properties to response object
		BeanUtils.copyProperties(request, response);

		// 2. Calculate the quote
		response.setQuotePrice(calculateQuotePrice(request));

		// 3. Save the quote to be retrieved later
		eventQuoteTransactionalService.saveEventQuote(response);

		// 4. Return the response
		return response;

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EventQuoteResponse getEventQuote(String quoteId) throws EventQuoteNotFoundException {
		EventQuoteResponse response = eventQuoteTransactionalService.getEventQuote(quoteId);
		if (response != null) {
			return response;
		}
		throw new EventQuoteNotFoundException(quoteId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<EventQuoteResponse> getAllEventQuotes() {
		return eventQuoteTransactionalService.getAllEventQuotes();
	}

	/**
	 * TODO
	 * 
	 * @author hatrivedi
	 * @date Nov 26, 2018
	 * @param request
	 * @return
	 */
	private BigDecimal calculateQuotePrice(EventQuoteRequest request) {
		BigDecimal quotePrice = BigDecimal.ZERO;

		// 1. Add the head count price to the quote

		// 2. Add the weather condition price if needed to the quote

		// 3. Add the month price to the quote

		// 4. Subtract the event type discount to the quote

		// 5. Return the quote price
		return quotePrice;

	}

}
