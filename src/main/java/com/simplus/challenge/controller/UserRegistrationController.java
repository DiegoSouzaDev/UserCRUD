package com.simplus.challenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.simplus.challenge.model.User;
import com.simplus.challenge.service.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserRegistrationController {
	
	private final UserService userService;
	
	@Autowired
	public UserRegistrationController(final UserService userSvc) {
		this.userService = userSvc;
	}
	
	@RequestMapping(value = "add", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public Long createUser(@RequestBody final User user) {
		return userService.create(user);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	public User updateUser(@RequestBody final User user) {
		return userService.updateUser(user);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") final Long id) {
		userService.delete(id);
	}
	
	@RequestMapping(value = "/find/{id}", method = RequestMethod.GET)
	public User readUserById(@PathVariable("id") final Long id) {
		return userService.findUserByID(id);
	}
}
