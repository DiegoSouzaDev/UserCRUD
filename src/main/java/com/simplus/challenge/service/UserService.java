package com.simplus.challenge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simplus.challenge.exception.NoUserToUpdateException;
import com.simplus.challenge.exception.UserNameAlreadyExistException;
import com.simplus.challenge.model.User;
import com.simplus.challenge.persistence.UserPersistence;

@Service
public class UserService {
	
	private final UserPersistence userPersist;

	@Autowired
	public UserService(final UserPersistence usrPersistence) {
		this.userPersist = usrPersistence;
	}
	
	public Long create(final User user) {
		if (userPersist.userNameAlreadyExist(user.getUserName())) {
			throw new UserNameAlreadyExistException();
		}
		return userPersist.create(user);
	}
	
	public User findUserByID(final Long id) {
		return userPersist.findByID(id);
		
	}
	
	public User updateUser(final User user) {
		if (userPersist.findByID(user.getId()) == null) {
			throw new NoUserToUpdateException();
		}
		return userPersist.update(user);
	}

	public void delete(final Long id) {
		userPersist.delete(id);
	}
	
}
