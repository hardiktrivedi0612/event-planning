package com.planning.event.service.impl;

import java.math.BigDecimal;
import java.util.TreeMap;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import com.planning.event.domain.EventQuoteRequest;
import com.planning.event.service.QuoteCalculationService;

/**
 * @author hatrivedi
 * @date Nov 26, 2018
 * @since 2.5
 */
@Service
@ConfigurationProperties("head-count-quote-config")
public class HeadCountQuoteCalculationServiceImpl implements QuoteCalculationService {

	private TreeMap<Integer, BigDecimal> priceByRange;

	/**
	 * {@inheritDoc}
	 * 
	 * Implementation Notes: TODO
	 */
	@Override
	public BigDecimal calculateQuotePrice(EventQuoteRequest request) {
		// Assumptions:
		// 1. Head count and Ranges will be values that are within Integer Range
		// 2. The start of the ranges provided in the requirement
		// is excluding the value provided
		// 3. There is no gap in the ranges

		BigDecimal quotePrice = priceByRange.floorEntry(request.getHeadCount()).getValue();

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
