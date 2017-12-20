package com.simplus.challenge.persistence;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.simplus.challenge.model.User;

@Repository
public class UserMemoryRepository implements UserRepository {
	
	protected Long id = 0l;

	protected final List<User> userList = new ArrayList<User>();
	
	@Override
	public Long create(User user) {
		id++;
		user.setId(id);
		userList.add(user);
		return user.getId();
	}

	@Override
	public User findByID(final Long id) {
		return userList.stream().filter(user -> id.equals(user.getId())).findAny().orElse(null);
	}

	@Override
	public Boolean userNameAlreadyExist(final String userName) {
		return userList.stream().filter(user -> userName.equals(user.getUserName())).findAny().isPresent();
	}
	
	@Override
	public User update(final User user) {
		removeUser(user.getId());
		userList.add(user);
		return user;
	}

	@Override
	public void delete(final Long id) {
		removeUser(id);
	}
	
	@Override
	public List<User> findAll() {
		return userList;
	}

	private void removeUser(final Long id) {
		for (final User curUser : userList) {
			if (curUser.getId().equals(id)) {
				userList.remove(curUser);
				break;
			}
		}
	}
}
