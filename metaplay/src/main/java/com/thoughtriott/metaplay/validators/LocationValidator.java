package com.thoughtriott.metaplay.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.thoughtriott.metaplay.data.entities.Location;

public class LocationValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Location.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Location location = (Location) target;

		// if there are any digits in the City field, throw error
		if (location.getCity().length()!=0 && location.getCity().matches("^[a-zA-Z]+$")) {
			errors.rejectValue("city", "location.city", "The city can only contain letters.");
		}

		// if there are any digits in the State field, throw error
		if (location.getState().length()!=0 && location.getState().matches("^[a-zA-Z]+$")) {
			errors.rejectValue("state", "location.state", "The state can only contain letters.");
		}
		
	}

}
