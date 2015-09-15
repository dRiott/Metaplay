package com.thoughtriott.metaplay.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.thoughtriott.metaplay.data.wrappers.CreateArtistWrapper;

public class CreateArtistWrapperValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return CreateArtistWrapper.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		CreateArtistWrapper caw = (CreateArtistWrapper) target;

		// if artist name is null, throw error.
		if (caw.getName() == null) {
			errors.rejectValue("name", "artist.name", "The artist name cannot be empty.");
		}
		
		// if artist name is LESS THAN three characters, throw error.
		if (caw.getName().length() < 3) {
			errors.rejectValue("name", "artist.name", "The artist name is too short.");
		}

		// if there are any digits in the City field, throw error
		if (caw.getLocationCity().length()!=0 && caw.getLocationCity().matches("^[a-zA-Z]+$")) {
			errors.rejectValue("locationCity", "location.city", "The state can only contain letters.");
		}

		// if there are any digits in the State field, throw error
		if (caw.getLocationState().length()!=0 && caw.getLocationState().matches("^[a-zA-Z]+$")) {
			errors.rejectValue("locationState", "location.state", "The state can only contain letters.");
		}

		// if there are any letters in the Number of Tracks On Album field, throw error.
		if (caw.getAlbumNumTracks().length()!=0 && caw.getAlbumNumTracks().matches("^[0-9]+$")) {
			errors.rejectValue("albumNumTracks", "album.numTracks", "The albunNumTracks cannot contain letters.");
		}

		// if artist name is LESS THAN three characters, throw error.
		if (caw.getAlbumName().length()!=0 && caw.getAlbumName().length() < 3) {
			errors.rejectValue("name", "artist.name", "The album name is too short");
		}
	

	}

}
