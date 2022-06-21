package com.github.rshtishi.recipe.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No such REcipe")
public class RecipeNotAvailableException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public RecipeNotAvailableException() {}
	
	public RecipeNotAvailableException(String message) {
		super(message);
	}
	
	public RecipeNotAvailableException(String message, Throwable cause) {
		super(message,cause);
	}	

}
