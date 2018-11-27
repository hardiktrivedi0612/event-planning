package com.planning.event.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.planning.event.domain.ErrorObject;
import com.planning.event.exception.EventQuoteNotFoundException;

/**
 * TODO
 * 
 * @author hatrivedi
 * @date Nov 26, 2018
 */
@ControllerAdvice
public class CommonExceptionAdvice {
	@ExceptionHandler(EventQuoteNotFoundException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ErrorObject> handleEventQuoteNotFoundException(EventQuoteNotFoundException exception) {
		ErrorObject errorObject = new ErrorObject("EQ1001",
				"Unable to find quote with reference ID as " + exception.getQuoteId());
		return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.BAD_REQUEST);
	}

}
