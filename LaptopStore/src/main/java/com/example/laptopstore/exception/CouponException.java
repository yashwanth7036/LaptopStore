package com.example.laptopstore.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class CouponException extends RuntimeException{
	public CouponException(String message) {
        super(message);
    }
}
