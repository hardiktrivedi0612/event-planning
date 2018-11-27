package com.planning.event.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.planning.event.domain.EventQuoteResponse;
import com.planning.event.service.EventQuoteTransactionalService;

/**
 * TODO
 * 
 * @author hatrivedi
 * @date Nov 26, 2018
 */
@Service
public class EventQuoteMemoryTransactionalService implements EventQuoteTransactionalService {

	// Concurrent hash map??
	private Map<String, EventQuoteResponse> memoryMap = new HashMap<>();

	private final String alphabet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

	private Random rand = new Random();

	private String generateRandomQuoteId() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 6; i++) {
			sb.append(alphabet.charAt(rand.nextInt(62)));
		}
		return sb.toString();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String saveEventQuote(EventQuoteResponse eventQuote) {
		String quoteId = "EQ" + generateRandomQuoteId();
		eventQuote.setQuoteId(quoteId);
		memoryMap.put(quoteId, eventQuote);
		return quoteId;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EventQuoteResponse getEventQuote(String quoteId) {
		if (memoryMap.containsKey(quoteId)) {
			return memoryMap.get(quoteId);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<EventQuoteResponse> getAllEventQuotes() {
		List<EventQuoteResponse> eventQuotes = new ArrayList<>();
		for (Map.Entry<String, EventQuoteResponse> entry : memoryMap.entrySet()) {
			eventQuotes.add(entry.getValue());
		}
		return eventQuotes;
	}

}
