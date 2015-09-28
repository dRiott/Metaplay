package com.thoughtriott.metaplay.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Resource Not Found")
public class MetaplayNotFoundException extends RuntimeException {

}
