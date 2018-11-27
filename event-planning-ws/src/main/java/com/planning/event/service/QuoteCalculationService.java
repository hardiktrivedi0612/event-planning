package com.planning.event.service;

import java.math.BigDecimal;

import com.planning.event.domain.EventQuoteRequest;

/**
 * TODO
 * 
 * @author hatrivedi
 * @date Nov 26, 2018
 */
public interface QuoteCalculationService {

	/**
	 * TODO
	 * 
	 * @author hatrivedi
	 * @date Nov 26, 2018
	 * @param request
	 * @return
	 */
	public BigDecimal calculateQuotePrice(EventQuoteRequest request);
}
