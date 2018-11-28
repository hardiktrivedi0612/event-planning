package com.planning.event.util;

import java.time.LocalDate;

import com.planning.event.constants.EventTypes;
import com.planning.event.domain.EventQuoteRequest;

/**
 * @author hatrivedi
 * @date Nov 26, 2018
 */
public class EventQuoteRequestUtil {

	public static EventQuoteRequest createDummyObject() {
		EventQuoteRequest request = new EventQuoteRequest();
		request.setHeadCount(12);
		request.setEventType(EventTypes.OTHER);
		request.setEventDate(LocalDate.of(2019, 3, 15));
		request.setName("Test User");
		request.setPhoneNumber("1234567890");
		request.setEmail("test.user@gmail.com");
		request.setCity("Dallas");
		return request;
	}

}
