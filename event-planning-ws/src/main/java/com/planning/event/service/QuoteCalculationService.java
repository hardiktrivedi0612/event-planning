package com.planning.event.service;

import java.math.BigDecimal;

import com.planning.event.domain.EventQuoteRequest;

/**
 * Service interface for quote estimate calculations for different conditions
 * 
 * @author hatrivedi
 * @date Nov 26, 2018
 */
public interface QuoteCalculationService {

	/**
	 * Method that will return the price of the quote estimate for the given
	 * input request based on the condition of the implementing class
	 * <p>
	 * This will return a positive amount if it is to be added to the final
	 * quote and will return a negative amount if it is to be subtracted from
	 * the final quote estimate
	 * 
	 * @author hatrivedi
	 * @date Nov 26, 2018
	 * @param request
	 * @return
	 */
	public BigDecimal calculateQuotePrice(EventQuoteRequest request);
}
