package com.planning.event.advice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.planning.event.domain.ErrorObject;
import com.planning.event.exception.EventQuoteNotFoundException;
import com.planning.event.exception.ValidationException;

/**
 * TODO
 * 
 * @author hatrivedi
 * @date Nov 26, 2018
 */
@ControllerAdvice
public class CommonExceptionAdvice {

	/**
	 * TODO
	 * 
	 * @author hatrivedi
	 * @date Nov 26, 2018
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(EventQuoteNotFoundException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<List<ErrorObject>> handleEventQuoteNotFoundException(EventQuoteNotFoundException exception) {
		List<ErrorObject> list = new ArrayList<>();
		list.add(new ErrorObject("EQ1001", "Unable to find quote with reference ID as " + exception.getQuoteId()));
		return new ResponseEntity<List<ErrorObject>>(list, HttpStatus.BAD_REQUEST);
	}

	/**
	 * TODO
	 * 
	 * @author hatrivedi
	 * @date Nov 26, 2018
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(ValidationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<List<ErrorObject>> handleValidationException(ValidationException exception) {
		return new ResponseEntity<List<ErrorObject>>(exception.getErrors(), HttpStatus.BAD_REQUEST);
	}

}
