package com.planning.event.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

import com.planning.event.constants.EventTypes;
import com.planning.event.controller.EventQuoteController;

/**
 * Domain class representing all of the details related to a quote estimate that
 * will be saved and returned to the user
 * 
 * @author hatrivedi
 * @date Nov 26, 2018
 */
public class EventQuoteDetails extends ResourceSupport {
	private String quoteId;
	private Integer headCount;
	private EventTypes eventType;
	private LocalDate eventDate;
	private BigDecimal quotePrice;
	private String name;
	private String phoneNumber;
	private String email;
	private String city;

	public void createResourceLink() {
		this.add(ControllerLinkBuilder.linkTo(EventQuoteController.class).slash(this.quoteId).withSelfRel());
	}

	// For less code, we can use Lombok library for generating these
	// getters and setters

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

	public LocalDate getEventDate() {
		return eventDate;
	}

	public void setEventDate(LocalDate eventDate) {
		this.eventDate = eventDate;
	}

	public BigDecimal getQuotePrice() {
		return quotePrice;
	}

	public void setQuotePrice(BigDecimal quotePrice) {
		this.quotePrice = quotePrice;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber
	 *            the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

}
