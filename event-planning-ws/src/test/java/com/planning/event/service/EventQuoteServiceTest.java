package com.planning.event.service;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.planning.event.EventPlanningWsApplicationTests;
import com.planning.event.constants.EventTypes;
import com.planning.event.domain.EventQuoteDetails;
import com.planning.event.domain.EventQuoteRequest;
import com.planning.event.domain.EventQuoteResponse;
import com.planning.event.exception.EventQuoteNotFoundException;
import com.planning.event.exception.ValidationException;
import com.planning.event.util.EventQuoteRequestUtil;

/**
 * JUnit test cases for Service class
 * 
 * @author hatrivedi
 * @date Nov 26, 2018
 */
public class EventQuoteServiceTest extends EventPlanningWsApplicationTests{

	@Autowired
	private EventQuoteService eventQuoteService;

	@Test
	public void checkIfQuoteIsBeingFetchedCorrectly() throws ValidationException, EventQuoteNotFoundException {
		EventQuoteRequest request = EventQuoteRequestUtil.createDummyObject();
		request.setHeadCount(12);
		EventQuoteResponse response = eventQuoteService.createEventQuote(request);
		EventQuoteDetails newResponse = eventQuoteService.getEventQuote(response.getQuoteId());
		assertEquals(response.getQuotePrice(), newResponse.getQuotePrice());
	}

	@Test
	public void headCountInFirstRange() throws ValidationException, EventQuoteNotFoundException {
		EventQuoteRequest request = EventQuoteRequestUtil.createDummyObject();
		request.setHeadCount(12);
		EventQuoteResponse response = eventQuoteService.createEventQuote(request);
		assertEquals(new BigDecimal(12 * 2000), response.getQuotePrice());
	}

	@Test
	public void headCountInSecondRange() throws ValidationException, EventQuoteNotFoundException {
		EventQuoteRequest request = EventQuoteRequestUtil.createDummyObject();
		request.setHeadCount(150);
		EventQuoteResponse response = eventQuoteService.createEventQuote(request);
		assertEquals(new BigDecimal(150 * 1500), response.getQuotePrice());
	}

	@Test
	public void headCountInThirdRange() throws ValidationException, EventQuoteNotFoundException {
		EventQuoteRequest request = EventQuoteRequestUtil.createDummyObject();
		request.setHeadCount(503);
		EventQuoteResponse response = eventQuoteService.createEventQuote(request);
		assertEquals(new BigDecimal(503 * 1000), response.getQuotePrice());
	}

	@Test
	public void checkMusicalEventDiscount() throws ValidationException, EventQuoteNotFoundException {
		EventQuoteRequest request = EventQuoteRequestUtil.createDummyObject();
		request.setHeadCount(12);
		request.setEventType(EventTypes.MUSICAL);
		EventQuoteResponse response = eventQuoteService.createEventQuote(request);
		assertEquals(new BigDecimal(12 * 2000 - 1000), response.getQuotePrice());
	}

	@Test
	public void checkNonMusicalEventDiscount() throws ValidationException, EventQuoteNotFoundException {
		EventQuoteRequest request = EventQuoteRequestUtil.createDummyObject();
		request.setHeadCount(12);
		request.setEventType(EventTypes.OTHER);
		EventQuoteResponse response = eventQuoteService.createEventQuote(request);
		assertEquals(new BigDecimal(12 * 2000), response.getQuotePrice());
	}

	@Test
	public void checkMonthSpecificQuote() throws ValidationException, EventQuoteNotFoundException {
		EventQuoteRequest request = EventQuoteRequestUtil.createDummyObject();
		request.setHeadCount(12);
		request.setEventDate(LocalDate.of(2019, 1, 1));
		EventQuoteResponse response = eventQuoteService.createEventQuote(request);
		assertEquals(new BigDecimal(12 * 2000 + 3000), response.getQuotePrice());
	}

	@Test
	public void checkMonthSpecificQuoteNegative() throws ValidationException, EventQuoteNotFoundException {
		EventQuoteRequest request = EventQuoteRequestUtil.createDummyObject();
		request.setHeadCount(12);
		request.setEventDate(LocalDate.of(2019, 2, 1));
		EventQuoteResponse response = eventQuoteService.createEventQuote(request);
		assertEquals(new BigDecimal(12 * 2000), response.getQuotePrice());
	}

	@Test(expected = EventQuoteNotFoundException.class)
	public void invalidQuoteIdTest() throws EventQuoteNotFoundException {
		eventQuoteService.getEventQuote("Some wrong quoteId");
	}

	@Test(expected = ValidationException.class)
	public void invalidPhoneNumberTest() throws ValidationException, EventQuoteNotFoundException {
		EventQuoteRequest request = EventQuoteRequestUtil.createDummyObject();
		request.setPhoneNumber("110011");
		eventQuoteService.createEventQuote(request);
	}

	@Test(expected = ValidationException.class)
	public void invalidEmailTest() throws ValidationException, EventQuoteNotFoundException {
		EventQuoteRequest request = EventQuoteRequestUtil.createDummyObject();
		request.setEmail("test@");
		eventQuoteService.createEventQuote(request);
	}

}
