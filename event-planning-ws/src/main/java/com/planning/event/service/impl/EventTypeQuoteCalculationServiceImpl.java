package com.planning.event.service.impl;

import java.math.BigDecimal;
import java.util.Set;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import com.planning.event.constants.EventTypes;
import com.planning.event.domain.EventQuoteRequest;
import com.planning.event.service.QuoteCalculationService;

/**
 * TODO
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
	 * TODO
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
