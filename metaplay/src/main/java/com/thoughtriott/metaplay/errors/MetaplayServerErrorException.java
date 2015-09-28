package com.thoughtriott.metaplay.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR, reason="Server Error")
public class MetaplayServerErrorException extends RuntimeException {

}
