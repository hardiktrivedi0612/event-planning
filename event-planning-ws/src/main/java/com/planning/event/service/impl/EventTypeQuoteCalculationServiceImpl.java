package com.planning.event.service.impl;

import java.math.BigDecimal;
import java.util.Set;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import com.planning.event.constants.EventTypes;
import com.planning.event.domain.EventQuoteRequest;
import com.planning.event.service.QuoteCalculationService;

/**
 * Implementation of {@link QuoteCalculationService} interface where this
 * calculates the quote estimate based on conditions provided for the type of
 * the event
 * 
 * @author hatrivedi
 * @date Nov 26, 2018
 */
@Service
@ConfigurationProperties("event-type-quote-config")
public class EventTypeQuoteCalculationServiceImpl implements QuoteCalculationService {

	private Set<EventTypes> eventTypeList;

	private BigDecimal quotePrice;

	/**
	 * {@inheritDoc}
	 * 
	 * Implementation Notes: This will return a negative price (which is present
	 * in the config) if the request event type is present in the eligible event
	 * types list
	 */
	@Override
	public BigDecimal calculateQuotePrice(EventQuoteRequest request) {
		if (eventTypeList.contains(request.getEventType())) {
			return quotePrice;
		}
		return BigDecimal.ZERO;
	}

	public Set<EventTypes> getEventTypeList() {
		return eventTypeList;
	}

	public void setEventTypeList(Set<EventTypes> eventTypeList) {
		this.eventTypeList = eventTypeList;
	}

	public BigDecimal getQuotePrice() {
		return quotePrice;
	}

	public void setQuotePrice(BigDecimal quotePrice) {
		this.quotePrice = quotePrice;
	}

}
