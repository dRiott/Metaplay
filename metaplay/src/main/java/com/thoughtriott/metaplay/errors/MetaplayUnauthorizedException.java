package com.thoughtriott.metaplay.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Unauthorized")
public class MetaplayUnauthorizedException extends RuntimeException {

}
