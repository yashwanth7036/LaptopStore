package com.example.laptopstore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotfound extends RuntimeException{
private static final long serialVersionUID = 1L;
	
	public ResourceNotfound(String message) {
		super(message);
	}
}
