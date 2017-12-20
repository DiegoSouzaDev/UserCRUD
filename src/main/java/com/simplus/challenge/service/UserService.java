package com.simplus.challenge.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simplus.challenge.exception.NoUserToUpdateException;
import com.simplus.challenge.exception.UserNameAlreadyExistException;
import com.simplus.challenge.model.User;
import com.simplus.challenge.persistence.UserRepository;

@Service
public class UserService {

	private final UserRepository repos;
	
	@Autowired
	public UserService(final UserRepository usrPersistence) {
		this.repos = usrPersistence;
	}

	public Long create(final User user) {
		if (repos.userNameAlreadyExist(user.getUserName())) {
			throw new UserNameAlreadyExistException();
		}
		return repos.create(user);
	}

	public User findUserByID(final Long id) {
		return repos.findByID(id);

	}

	public User updateUser(final User user) {
		if (repos.findByID(user.getId()) == null) {
			throw new NoUserToUpdateException();
		}
		return repos.update(user);
	}
	
	public void delete(final Long id) {
		repos.delete(id);
	}
	
	public List<User> findAll() {
		return repos.findAll();
	}

}
