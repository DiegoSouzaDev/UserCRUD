package com.simplus.challenge.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.simplus.challenge.exception.NoUserToUpdateException;
import com.simplus.challenge.exception.UserNameAlreadyExistsException;

@ControllerAdvice
@RestController
public class RequestExceptionHandler {
	
	@ExceptionHandler(UserNameAlreadyExistsException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public String userNameAlreadyExistHandler(final UserNameAlreadyExistsException e) {
		return "400 Bad Request: Username already in use by a user.";
	}

	@ExceptionHandler(NoUserToUpdateException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public String noUserToUpdateHandler(final NoUserToUpdateException e) {
		return "400 Bad Request: Cannot update. The user no longer exist in the database";
	}
}
