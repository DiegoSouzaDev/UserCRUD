package com.simplus.challenge.persistence;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.simplus.challenge.model.User;

@Component
public class UserPersistence {

	private Long id = 0l;
	
	public final List<User> userList = new ArrayList<User>();

	public Long create(User user) {
		id++;
		user.setId(id);
		userList.add(user);
		return user.getId();
	}
	
	public User findByID(final Long id) {
		return userList.stream().filter(user -> id.equals(user.getId())).findAny().orElse(null);
	}
	
	public Boolean userNameAlreadyExist(final String userName) {
		return userList.stream().filter(user -> userName.equals(user.getUserName())).findAny().isPresent();
	}

	public User update(final User user) {
		removeUser(user.getId());
		userList.add(user);
		return user;
	}
	
	public void delete(final Long id) {
		removeUser(id);
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
