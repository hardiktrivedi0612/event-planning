package com.planning.event.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.planning.event.domain.EventQuoteDetails;
import com.planning.event.service.EventQuoteTransactionalService;

/**
 * An memory based implementation of
 * {@link EventQuoteMemoryTransactionalService} that uses a HashMap to store the
 * records
 * 
 * @author hatrivedi
 * @date Nov 26, 2018
 */
@Service
public class EventQuoteMemoryTransactionalService implements EventQuoteTransactionalService {

	private Map<String, EventQuoteDetails> memoryMap = new HashMap<>();

	private final String alphabet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

	private Random rand = new Random();

	/**
	 * Method to generate a 6 character identifier for quote identification that
	 * will be returned to the user
	 * 
	 * @author hatrivedi
	 * @date Nov 27, 2018
	 * @return
	 */
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
	public String saveEventQuote(EventQuoteDetails eventQuote) {
		String quoteId = "EQ" + generateRandomQuoteId();
		eventQuote.setQuoteId(quoteId);
		memoryMap.put(quoteId, eventQuote);
		return quoteId;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EventQuoteDetails getEventQuote(String quoteId) {
		if (memoryMap.containsKey(quoteId)) {
			return memoryMap.get(quoteId);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<EventQuoteDetails> getAllEventQuotes() {
		List<EventQuoteDetails> eventQuotes = new ArrayList<>();
		for (Map.Entry<String, EventQuoteDetails> entry : memoryMap.entrySet()) {
			eventQuotes.add(entry.getValue());
		}
		return eventQuotes;
	}

}
