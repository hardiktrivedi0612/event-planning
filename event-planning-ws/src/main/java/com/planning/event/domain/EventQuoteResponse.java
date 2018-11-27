package com.planning.event.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.planning.event.constants.EventTypes;

/**
 * TODO
 * 
 * @author hatrivedi
 * @date Nov 26, 2018
 */
public class EventQuoteResponse {
	private String quoteId;
	private Integer headCount;
	private EventTypes eventType;
	private Date eventDate;
	private BigDecimal quotePrice;

	// TODO: lombok can be used

	public String getQuoteId() {
		return quoteId;
	}

	public void setQuoteId(String quoteId) {
		this.quoteId = quoteId;
	}

	public Integer getHeadCount() {
		return headCount;
	}

	public void setHeadCount(Integer headCount) {
		this.headCount = headCount;
	}

	public EventTypes getEventType() {
		return eventType;
	}

	public void setEventType(EventTypes eventType) {
		this.eventType = eventType;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public BigDecimal getQuotePrice() {
		return quotePrice;
	}

	public void setQuotePrice(BigDecimal quotePrice) {
		this.quotePrice = quotePrice;
	}

}
