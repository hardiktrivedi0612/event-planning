package com.planning.event.service.impl;

import java.math.BigDecimal;
import java.util.Set;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import com.planning.event.domain.EventQuoteRequest;
import com.planning.event.service.QuoteCalculationService;

/**
 * Implementation of {@link QuoteCalculationService} interface where this
 * calculates the quote estimate based on conditions provided for the month in
 * which the event is requested
 * 
 * @author hatrivedi
 * @date Nov 26, 2018
 */
@Service
@ConfigurationProperties("month-condition-quote-config")
public class MonthConditionQuoteCalculationServiceImpl implements QuoteCalculationService {

	private Set<Integer> monthsList;

	private BigDecimal quotePrice;

	/**
	 * {@inheritDoc}
	 * 
	 * Implementation Notes: This will return a positive amount (which is
	 * defined in the config) if the month of the event date falls in the list
	 * of months provided
	 */
	@Override
	public BigDecimal calculateQuotePrice(EventQuoteRequest request) {
		if (monthsList.contains(request.getEventDate().getMonthValue())) {
			return quotePrice;
		}
		return BigDecimal.ZERO;
	}

	public Set<Integer> getMonthsList() {
		return monthsList;
	}

	public void setMonthsList(Set<Integer> monthsList) {
		this.monthsList = monthsList;
	}

	public BigDecimal getQuotePrice() {
		return quotePrice;
	}

	public void setQuotePrice(BigDecimal quotePrice) {
		this.quotePrice = quotePrice;
	}

}
