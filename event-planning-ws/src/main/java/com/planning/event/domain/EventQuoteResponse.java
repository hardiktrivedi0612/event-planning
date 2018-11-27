package com.planning.event.domain;

import java.math.BigDecimal;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

import com.planning.event.controller.EventQuoteController;
import com.planning.event.exception.EventQuoteNotFoundException;

/**
 * Domain class representing the variables of the quote estimate response
 * 
 * @author hatrivedi
 * @date Nov 26, 2018
 */
public class EventQuoteResponse extends ResourceSupport {
	private String quoteId;
	private BigDecimal quotePrice;

	public void createResourceLink() throws EventQuoteNotFoundException {
		this.add(ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder.methodOn(EventQuoteController.class).getEventQuote(this.quoteId))
				.withSelfRel());
	}

	// For less code, we can use Lombok library for generating these
	// getters and setters

	public String getQuoteId() {
		return quoteId;
	}

	public void setQuoteId(String quoteId) {
		this.quoteId = quoteId;
	}

	public BigDecimal getQuotePrice() {
		return quotePrice;
	}

	public void setQuotePrice(BigDecimal quotePrice) {
		this.quotePrice = quotePrice;
	}

}
