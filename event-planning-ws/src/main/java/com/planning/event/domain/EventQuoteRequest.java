package com.planning.event.domain;

import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.planning.event.constants.EventTypes;

/**
 * TODO
 * 
 * @author hatrivedi
 * @date Nov 26, 2018
 */
public class EventQuoteRequest {

	@NotNull
	@Positive(message = "Event can be quoted with atleast 1 person")
	private Integer headCount;

	@NotNull
	private EventTypes eventType;

	@NotNull
	@Future(message = "Event can only be quoted for future dates")
	private Date eventDate;

	// TODO: lombok can be used

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

}
