package com.thoughtriott.metaplay.errors;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MetaplayExceptionHandler {

	@ExceptionHandler(MetaplayNotFoundException.class)
	public String metaplayNotFoundHandler() {
		return "error_notfound";
	}
	
	@ExceptionHandler(MetaplayServerErrorException.class)
	public String metaplayServerErrorHandler() {
		return "error_notfound";
	}
	
	@ExceptionHandler(MetaplayBadRequestException.class)
	public String metaplayBadRequestHandler() {
		return "error_notfound";
	}
	
	@ExceptionHandler(MetaplayUnsupportedMediaException.class)
	public String metaplayUnsupportedMediaHandler() {
		return "error_notfound";
	}
	
	@ExceptionHandler(MetaplayUnauthorizedException.class)
	public String metaplayUnauthorizedHandler() {
		return "error_notfound";
	}
}
