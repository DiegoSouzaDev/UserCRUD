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

import com.simplus.challenge.enumerator.Currency;
import com.simplus.challenge.enumerator.LanguageAndRegion;
import com.simplus.challenge.enumerator.Ocupation;
import com.simplus.challenge.enumerator.Role;
import com.simplus.challenge.enumerator.State;
import com.simplus.challenge.enumerator.Target;
import com.simplus.challenge.exception.NoUserToUpdateException;
import com.simplus.challenge.exception.UserNameAlreadyExistsException;
import com.simplus.challenge.model.Address;
import com.simplus.challenge.model.Company;
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
		final User user = getUser(index);
		
		when(repos.create(user)).thenReturn(index);
		final Long userId = service.create(user);
		
		Assert.assertEquals(index, userId);
	}

	@Test(expected = UserNameAlreadyExistsException.class)
	public void testCreateWithDuplicatedUsername() throws Exception {
		final User user = getUser(1l);
		when(repos.userNameAlreadyExist(user.getUserName())).thenReturn(true);
		
		service.create(user);
	}
	
	@Test
	public void testFindUserByID() throws Exception {
		final Long id = 1l;

		when(repos.findByID(id)).thenReturn(getUser(id));
		final User user = service.findUserByID(id);

		Assert.assertEquals("name1", user.getName());
	}

	@Test
	public void testSucessfulyUpdateUser() throws Exception {
		final User user = getUser(1l);

		when(repos.findByID(user.getId())).thenReturn(user);
		when(repos.update(user)).thenReturn(user);

		final User userUpdated = service.updateUser(user);

		Assert.assertEquals(user.getEmail(), userUpdated.getEmail());
		verify(repos, times(1)).findByID(user.getId());
		verify(repos, times(1)).update(user);
	}
	
	@Test(expected = NoUserToUpdateException.class)
	public void testUpdateNonExistentUser() throws Exception {
		final User user = getUser(1l);

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

	private User getUser(final Long index) {
		final User user = new User();
		user.setId(index);
		user.setName("name" + index);
		user.setLastName("lastname" + index);
		user.setUserName(user.getUserName() + user.getLastName());
		user.setEmail(user.getName() + "@domain.com");
		user.setRole(Role.ADMIN);
		user.setPhone("phoneNumber + index");
		user.setExtension("ramal" + index);
		user.setCellphone("cellNumber" + index);
		user.setOcupation(Ocupation.MANAGER);
		user.setCompany(getCompany(index));
		user.setAddress(getAddress(index));
		user.setLanguageAndRegion(LanguageAndRegion.PT_BR);
		user.setCurrency(Currency.BRL);
		user.setTarget(Target.VET);
		return user;
	}
	
	private Company getCompany(final Long index) {
		final Company company = new Company();
		company.setId(index);
		company.setName("CompanyName" + index);
		return company;
	}
	
	private Address getAddress(final Long index) {
		final Address addr = new Address();
		addr.setStreet("Street " + index);
		addr.setNumber("11" + index);
		addr.setNeighborhood("neighborhood" + index);
		addr.setCity("city" + index);
		addr.setState(State.SC);
		addr.setCountry("country" + index);
		addr.setZipCode("89231-11" + index);
		return addr;
	}
}
