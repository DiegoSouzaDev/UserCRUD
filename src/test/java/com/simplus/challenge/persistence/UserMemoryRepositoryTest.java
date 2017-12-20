package com.simplus.challenge.persistence;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.simplus.challenge.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMemoryRepositoryTest {
	
	@Autowired
	private UserMemoryRepository repos;
	
	@Before
	public void cleanUp() {
		repos.userList.clear();
		repos.id = 0l;
	}

	@Test
	public void testCreate() throws Exception {
		final User user = new User();
		final Long id = repos.create(user);
		Assert.assertEquals(1l, id.longValue());

	}
	
	@Test
	public void testFindByID() throws Exception {
		final User user = new User();
		final Long id = repos.create(user);
		final User foundUser = repos.findByID(id);

		Assert.assertEquals(id.longValue(), foundUser.getId().longValue());
		Assert.assertEquals(repos.userList.size(), id.intValue());
	}
	
	@Test
	public void testUserNameAlreadyExist() throws Exception {
		final User user = new User();
		user.setUserName("userNameTest");
		repos.create(user);

		final Boolean alreadyExists = repos.userNameAlreadyExist(user.getUserName());

		Assert.assertTrue(alreadyExists);
	}

	@Test
	public void testUpdate() throws Exception {
		final User user = new User();
		user.setName("MyName");
		final Long id = repos.create(user);
		user.setId(id);
		
		Assert.assertEquals("MyName", user.getName());
		
		user.setName("My Name");
		repos.update(user);
		final User recoveredUser = repos.findByID(id);
		
		Assert.assertEquals("My Name", recoveredUser.getName());
	}
	
	@Test
	public void testDelete() throws Exception {
		final User user1 = new User();
		final Long id1 = repos.create(user1);
		final User user2 = new User();
		final Long id2 = repos.create(user2);

		Assert.assertEquals(repos.userList.size(), 2);
		
		repos.delete(id2);
		
		Assert.assertEquals(repos.userList.size(), 1);
	}

	@Test
	public void testFindAll() throws Exception {
		final User user1 = new User();
		final User user2 = new User();

		repos.create(user1);
		repos.create(user2);
		
		final List<User> allUsers = repos.findAll();
		
		Assert.assertEquals(2, allUsers.size());
		
	}

}
