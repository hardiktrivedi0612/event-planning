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

import com.planning.event.domain.EventQuoteDetails;
import com.planning.event.domain.EventQuoteRequest;
import com.planning.event.domain.EventQuoteResponse;
import com.planning.event.exception.EventQuoteNotFoundException;
import com.planning.event.exception.ValidationException;
import com.planning.event.service.EventQuoteService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * Controller class for defining RESTful APIs related to Quote estimated for
 * WePlanIt
 * 
 * @author hatrivedi
 * @date Nov 26, 2018
 */

@RestController
@RequestMapping("/v1")
@Api("Controller for RESTful APIs related to Quote estimates for WePlanIt")
public class EventQuoteController {

	@Autowired
	private EventQuoteService eventQuoteService;

	/**
	 * Endpoint that the user can use to get a quote estimate.
	 * <p>
	 * This API will return a quote price and a quote ID that the user can later
	 * use as a reference once they decide to accept the quoted price
	 * 
	 * @author hatrivedi
	 * @date Nov 26, 2018
	 * @param request
	 * @return
	 * @throws ValidationException
	 * @throws EventQuoteNotFoundException
	 */
	@PostMapping(value = "/quote", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get quote estimate", notes = "Endpoint that the user can use to get a quote estimate")
	public ResponseEntity<EventQuoteResponse> createEventQuote(
			@ApiParam(name = "Request Body") @RequestBody @Valid EventQuoteRequest request)
			throws ValidationException, EventQuoteNotFoundException {
		return new ResponseEntity<EventQuoteResponse>(eventQuoteService.createEventQuote(request), HttpStatus.OK);
	}

	/**
	 * Endpoint that the user can use to access a previous quote.
	 * <p>
	 * Input to this API is the quote reference ID that the user received when
	 * they received the quote.
	 * 
	 * @author hatrivedi
	 * @date Nov 26, 2018
	 * @param quoteId
	 * @return
	 * @throws EventQuoteNotFoundException
	 */
	@GetMapping(value = "/quote/{quoteId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get quote details", notes = "Endpoint that the user can use to access a previous quote")
	public ResponseEntity<EventQuoteDetails> getEventQuote(
			@ApiParam(name = "quoteId", value = "Reference id used to access the previuosly quoted estimate") @PathVariable(name = "quoteId") String quoteId)
			throws EventQuoteNotFoundException {
		return new ResponseEntity<EventQuoteDetails>(eventQuoteService.getEventQuote(quoteId), HttpStatus.OK);
	}

	/**
	 * Endpoint that the system admin can use to get all of the quotes that are
	 * created in the system
	 * <p>
	 * This endpoint will be authorized and only certain users will have access
	 * to this endpoint
	 * 
	 * @author hatrivedi
	 * @date Nov 26, 2018
	 * @return
	 */
	@GetMapping(value = "/quotes", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get all quotes", notes = "Endpoint that the system admin can use to get all of the quotes")
	public ResponseEntity<List<EventQuoteDetails>> getAllEventQuotes() {
		return new ResponseEntity<List<EventQuoteDetails>>(eventQuoteService.getAllEventQuotes(), HttpStatus.OK);
	}

}
