package com.planning.event.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.planning.event.domain.EventQuoteRequest;
import com.planning.event.domain.EventQuoteResponse;
import com.planning.event.exception.EventQuoteNotFoundException;
import com.planning.event.exception.ValidationException;
import com.planning.event.service.EventQuoteService;
import com.planning.event.service.EventQuoteTransactionalService;
import com.planning.event.util.ValidationUtil;

/**
 * @author hatrivedi
 * @date Nov 26, 2018
 */
@Service
public class EventQuoteServiceImpl implements EventQuoteService {

	private ValidationUtil validationUtil;
	private EventQuoteTransactionalService eventQuoteTransactionalService;
	private HeadCountQuoteCalculationServiceImpl headCountQuoteCalculationServiceImpl;
	private WeatherConditionQuoteCalculationServiceImpl weatherConditionQuoteCalculationServiceImpl;
	private MonthConditionQuoteCalculationServiceImpl monthConditionQuoteCalculationServiceImpl;
	private EventTypeQuoteCalculationServiceImpl eventTypeQuoteCalculationServiceImpl;
	private ApplicationContext applicationContext;

	EventQuoteServiceImpl(ValidationUtil validationUtil, EventQuoteTransactionalService eventQuoteTransactionalService,
			HeadCountQuoteCalculationServiceImpl headCountQuoteCalculationServiceImpl,
			WeatherConditionQuoteCalculationServiceImpl weatherConditionQuoteCalculationServiceImpl,
			MonthConditionQuoteCalculationServiceImpl monthConditionQuoteCalculationServiceImpl,
			EventTypeQuoteCalculationServiceImpl eventTypeQuoteCalculationServiceImpl,
			ApplicationContext applicationContext) {
		this.validationUtil = validationUtil;
		this.eventQuoteTransactionalService = eventQuoteTransactionalService;
		this.headCountQuoteCalculationServiceImpl = headCountQuoteCalculationServiceImpl;
		this.weatherConditionQuoteCalculationServiceImpl = weatherConditionQuoteCalculationServiceImpl;
		this.monthConditionQuoteCalculationServiceImpl = monthConditionQuoteCalculationServiceImpl;
		this.eventTypeQuoteCalculationServiceImpl = eventTypeQuoteCalculationServiceImpl;
		this.applicationContext = applicationContext;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws ValidationException
	 */
	@Override
	public EventQuoteResponse createEventQuote(EventQuoteRequest request) throws ValidationException {

		EventQuoteResponse response = new EventQuoteResponse();

		// 1. Validate the input
		validationUtil.validateEventQuoteRequest(request);

		// 2. Copy properties to response object
		BeanUtils.copyProperties(request, response);

		// 3. Calculate the quote
		response.setQuotePrice(calculateQuotePrice(request));

		// 4. Save the quote to be retrieved later
		eventQuoteTransactionalService.saveEventQuote(response);

		// 5. Return the response
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

		// for(QuoteCalculationService service :
		// applicationContext.getBeansOfType(QuoteCalculationService.class).values())
		// {
		// quotePrice = quotePrice.add(service.calculateQuotePrice(request));
		// }

		// 1. Add the head count price to the quote
		quotePrice = quotePrice.add(headCountQuoteCalculationServiceImpl.calculateQuotePrice(request));

		// 2. Add the weather condition price if needed to the quote
		quotePrice = quotePrice.add(weatherConditionQuoteCalculationServiceImpl.calculateQuotePrice(request));

		// 3. Add the month price to the quote
		quotePrice = quotePrice.add(monthConditionQuoteCalculationServiceImpl.calculateQuotePrice(request));

		// 4. Subtract the event type discount to the quote
		quotePrice = quotePrice.add(eventTypeQuoteCalculationServiceImpl.calculateQuotePrice(request));

		// 5. Return the quote price
		return quotePrice;

	}

}
