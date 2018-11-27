package com.planning.event.service.impl;

import java.math.BigDecimal;
import java.util.Set;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import com.planning.event.domain.EventQuoteRequest;
import com.planning.event.service.QuoteCalculationService;

/**
 * TODO
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
	 * Implementation Notes: TODO
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