package com.thoughtriott.metaplay.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.thoughtriott.metaplay.data.entities.Artist;

public class ArtistValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Artist.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Artist artist = (Artist) target;
		
		if(artist.getName().length() < 2) {
			errors.rejectValue("name", "artist.name", "The artist name is too short");
		}
	}

}
