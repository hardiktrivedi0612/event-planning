package com.planning.event.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.planning.event.domain.EventQuoteDetails;
import com.planning.event.domain.EventQuoteRequest;
import com.planning.event.domain.EventQuoteResponse;
import com.planning.event.exception.EventQuoteNotFoundException;
import com.planning.event.exception.ValidationException;
import com.planning.event.service.EventQuoteService;
import com.planning.event.service.EventQuoteTransactionalService;
import com.planning.event.service.QuoteCalculationService;
import com.planning.event.util.ValidationUtil;

/**
 * Service implementation class for {@link EventQuoteService}
 * 
 * @author hatrivedi
 * @date Nov 26, 2018
 */
@Service
public class EventQuoteServiceImpl implements EventQuoteService {

	private static final Logger logger = LoggerFactory.getLogger(EventQuoteServiceImpl.class);

	@Autowired
	private ValidationUtil validationUtil;

	@Autowired
	private EventQuoteTransactionalService eventQuoteTransactionalService;

	@Autowired
	private ApplicationContext applicationContext;

	/**
	 * {@inheritDoc}
	 * 
	 * @throws ValidationException
	 * @throws EventQuoteNotFoundException
	 */
	@Override
	public EventQuoteResponse createEventQuote(EventQuoteRequest request)
			throws ValidationException, EventQuoteNotFoundException {

		EventQuoteDetails detailResponse = new EventQuoteDetails();

		// 1. Validate the input
		validationUtil.validateEventQuoteRequest(request);

		// 2. Copy properties to response object
		BeanUtils.copyProperties(request, detailResponse);

		// 3. Calculate the quote
		detailResponse.setQuotePrice(calculateQuotePrice(request));

		// 4. Save the quote to be retrieved later
		eventQuoteTransactionalService.saveEventQuote(detailResponse);

		// 5. Create the response
		EventQuoteResponse response = new EventQuoteResponse();
		response.setQuoteId(detailResponse.getQuoteId());
		response.setQuotePrice(detailResponse.getQuotePrice());
		response.createResourceLink();

		// 6. Return the response
		return response;

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EventQuoteDetails getEventQuote(String quoteId) throws EventQuoteNotFoundException {
		EventQuoteDetails response = eventQuoteTransactionalService.getEventQuote(quoteId);
		if (response != null) {
			return response;
		}
		logger.info("Invalid quote id : {} sent in the request!", quoteId);
		throw new EventQuoteNotFoundException(quoteId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<EventQuoteDetails> getAllEventQuotes() {
		return eventQuoteTransactionalService.getAllEventQuotes();
	}

	/**
	 * Method that will iterate through all of the implementations of
	 * {@link QuoteCalculationService} and calculate the final quote estimate
	 * <p>
	 * If any new conditions have to be added to the quote estimate calculation,
	 * a new implementation of {@link QuoteCalculationService} interface can be
	 * added and this will automatically take that into consideration
	 * 
	 * @author hatrivedi
	 * @date Nov 26, 2018
	 * @param request
	 * @return
	 */
	private BigDecimal calculateQuotePrice(EventQuoteRequest request) {
		BigDecimal quotePrice = BigDecimal.ZERO;
		for (QuoteCalculationService service : applicationContext.getBeansOfType(QuoteCalculationService.class)
				.values()) {
			quotePrice = quotePrice.add(service.calculateQuotePrice(request));
		}
		logger.debug("Price calculated as : {}", quotePrice);
		return quotePrice;

	}

}
