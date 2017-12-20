package com.simplus.challenge.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.simplus.challenge.exception.NoUserToUpdateException;
import com.simplus.challenge.exception.UserNameAlreadyExistsException;
import com.simplus.challenge.model.User;
import com.simplus.challenge.persistence.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
	
	@Autowired
	private UserService service;

	@MockBean
	private UserRepository repos;
	
	@Test
	public void testCreateOneUser() throws Exception {
		final Long index = 1l;
		final User user = new User();
		
		when(repos.create(user)).thenReturn(index);
		final Long userId = service.create(user);
		
		Assert.assertEquals(index, userId);
	}

	@Test(expected = UserNameAlreadyExistsException.class)
	public void testCreateWithDuplicatedUsername() throws Exception {
		final User user = new User();
		when(repos.userNameAlreadyExist(user.getUserName())).thenReturn(true);
		
		service.create(user);
	}
	
	@Test
	public void testFindUserByID() throws Exception {
		final Long id = 1l;
		final User user = new User();
		user.setName("name");
		
		when(repos.findByID(id)).thenReturn(user);
		final User recoveredUser = service.findUserByID(id);

		Assert.assertEquals("name", recoveredUser.getName());
	}

	@Test
	public void testSucessfulyUpdateUser() throws Exception {
		final User user = new User();
		user.setId(1l);

		when(repos.findByID(user.getId())).thenReturn(user);
		when(repos.update(user)).thenReturn(user);

		service.updateUser(user);

		verify(repos, times(1)).findByID(user.getId());
		verify(repos, times(1)).update(user);
	}
	
	@Test(expected = NoUserToUpdateException.class)
	public void testUpdateNonExistentUser() throws Exception {
		final User user = new User();
		user.setId(1l);

		service.updateUser(user);
	}

	@Test
	public void testDeleteUser() {
		final Long id = 3l;
		service.delete(id);
		verify(repos, times(1)).delete(id);
	}
	
	@Test
	public void testFindAll() throws Exception {
		service.findAll();
		verify(repos, times(1)).findAll();
	}
}
