package com.planning.event.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.planning.event.domain.EventQuoteRequest;
import com.planning.event.domain.EventQuoteResponse;
import com.planning.event.exception.EventQuoteNotFoundException;
import com.planning.event.service.EventQuoteService;

/**
 * TODO
 * 
 * @author hatrivedi
 * @date Nov 26, 2018
 */

@RestController
@RequestMapping("/v1")
public class EventQuoteController {

	@Autowired
	private EventQuoteService eventQuoteService;

	/**
	 * TODO
	 * 
	 * @author hatrivedi
	 * @date Nov 26, 2018
	 * @param request
	 * @return
	 */
	@PostMapping(value = "/quote", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EventQuoteResponse> createEventQuote(@RequestBody @Valid EventQuoteRequest request) {
		// TODO: to write why I didn't use created
		return new ResponseEntity<EventQuoteResponse>(eventQuoteService.createEventQuote(request), HttpStatus.OK);
	}

	/**
	 * TODO
	 * 
	 * @author hatrivedi
	 * @date Nov 26, 2018
	 * @param quoteId
	 * @return
	 * @throws EventQuoteNotFoundException
	 */
	@GetMapping(value = "/quote/{quoteId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EventQuoteResponse> getEventQuote(@PathVariable(name = "quoteId") String quoteId)
			throws EventQuoteNotFoundException {
		return new ResponseEntity<EventQuoteResponse>(eventQuoteService.getEventQuote(quoteId), HttpStatus.OK);
	}

	/**
	 * TODO
	 * 
	 * @author hatrivedi
	 * @date Nov 26, 2018
	 * @return
	 */
	@GetMapping(value = "/quotes", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<EventQuoteResponse>> getAllEventQuotes() {
		return new ResponseEntity<List<EventQuoteResponse>>(eventQuoteService.getAllEventQuotes(), HttpStatus.OK);
	}

}
