package com.planning.event.service.impl;

import java.math.BigDecimal;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import com.planning.event.domain.EventQuoteRequest;
import com.planning.event.service.QuoteCalculationService;

/**
 * Implementation of {@link QuoteCalculationService} interface where this
 * calculates the quote estimate based on conditions provided for the head count
 * for the event
 * 
 * @author hatrivedi
 * @date Nov 26, 2018
 */
@Service
@ConfigurationProperties("head-count-quote-config")
public class HeadCountQuoteCalculationServiceImpl implements QuoteCalculationService {

	private static final Logger logger = LoggerFactory.getLogger(HeadCountQuoteCalculationServiceImpl.class);

	private TreeMap<Integer, BigDecimal> priceByRange;

	/**
	 * {@inheritDoc}
	 * 
	 * Implementation Notes: This will return a positive amount based on the
	 * conditions provided for range of head counts that the event is being
	 * requested for
	 */
	@Override
	public BigDecimal calculateQuotePrice(EventQuoteRequest request) {
		// Assumptions:
		// 1. Head count and Ranges will be values that are within Integer Range
		// 2. The start of the ranges provided in the requirement
		// is excluding the value provided
		// 3. There is no gap in the ranges

		BigDecimal quotePrice = priceByRange.floorEntry(request.getHeadCount()).getValue();

		logger.debug("Head count price found as : {}", quotePrice);

		return quotePrice.multiply(new BigDecimal(request.getHeadCount()));

	}

	/**
	 * @return the priceByRange
	 */
	public TreeMap<Integer, BigDecimal> getPriceByRange() {
		return priceByRange;
	}

	/**
	 * @param priceByRange
	 *            the priceByRange to set
	 */
	public void setPriceByRange(TreeMap<Integer, BigDecimal> priceByRange) {
		this.priceByRange = priceByRange;
	}

}
