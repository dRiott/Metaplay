package com.thoughtriott.metaplay.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(value=HttpStatus.UNSUPPORTED_MEDIA_TYPE, reason="Media Type Unsupported")
public class MetaplayUnsupportedMediaException extends RuntimeException {

}
