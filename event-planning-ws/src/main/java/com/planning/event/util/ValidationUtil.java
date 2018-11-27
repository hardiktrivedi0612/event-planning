package com.planning.event.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.planning.event.domain.ErrorObject;
import com.planning.event.domain.EventQuoteRequest;
import com.planning.event.exception.ValidationException;

/**
 * Utility class for having all validation methods
 * 
 * @author hatrivedi
 * @date Nov 26, 2018
 */
@Component
public class ValidationUtil {

	public final String phoneNumberValidationRegex = "^[0-9]{10}$";

	public final String emailValidationRegex = "^(.+)@(.+)$";

	/**
	 * Validation method to valide the input for an event quote
	 * 
	 * @author hatrivedi
	 * @date Nov 27, 2018
	 * @param request
	 * @throws ValidationException
	 */
	public void validateEventQuoteRequest(EventQuoteRequest request) throws ValidationException {
		List<ErrorObject> errors = new ArrayList<>();
		if (!request.getPhoneNumber().matches(phoneNumberValidationRegex)) {
			errors.add(new ErrorObject("EQ1002", "Invalid phone number format"));
		}
		if (!request.getEmail().matches(emailValidationRegex)) {
			errors.add(new ErrorObject("EQ1003", "Invalid email format"));
		}
		if (!errors.isEmpty()) {
			throw new ValidationException(errors);
		}
	}

}
