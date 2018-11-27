package com.planning.event.domain;

import java.time.LocalDate;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.planning.event.constants.EventTypes;

/**
 * Domain class that represents the fields that the user will provide as a part
 * of the request
 * 
 * @author hatrivedi
 * @date Nov 26, 2018
 */
public class EventQuoteRequest {

	// Assumption: Event can be scheduled for atleast 1 person and not less than
	// that
	@NotNull(message = "Head count is required to provide a quote")
	@Positive(message = "Event can be quoted with atleast 1 person")
	private Integer headCount;

	@NotNull(message = "Event type is required to provide a quote")
	private EventTypes eventType;

	// Assumption: Event date cannot be today or before today
	@NotNull(message = "Event date is required to provide a quote")
	@Future(message = "Event can only be quoted for future dates")
	private LocalDate eventDate;

	@NotNull(message = "Name is required to provide a quote")
	@NotEmpty(message = "Name is required to provide a quote")
	private String name;

	@NotNull(message = "Phone number is required to provide a quote")
	@NotEmpty(message = "Phone number is required to provide a quote")
	private String phoneNumber;

	@NotNull(message = "Email is required to provide a quote")
	@NotEmpty(message = "Email is required to provide a quote")
	private String email;

	@NotNull(message = "City is required to provide a quote")
	@NotEmpty(message = "City is required to provide a quote")
	private String city;

	// For less code, we can use Lombok library for generating these
	// getters and setters

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}
