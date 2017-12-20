package com.simplus.challenge.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simplus.challenge.enumerator.Currency;
import com.simplus.challenge.enumerator.LanguageAndRegion;
import com.simplus.challenge.enumerator.Ocupation;
import com.simplus.challenge.enumerator.Role;
import com.simplus.challenge.enumerator.State;
import com.simplus.challenge.enumerator.Target;
import com.simplus.challenge.model.Address;
import com.simplus.challenge.model.Company;
import com.simplus.challenge.model.User;
import com.simplus.challenge.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureWebMvc
public class UserRegistrationControllerTest {
	
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wac;
	
	@Autowired
	private UserRegistrationController controller;
	
	@MockBean
	private UserService service;

	@Before
	public void setup() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void testReadUserById() throws Exception {
		when(service.findUserByID(1l)).thenReturn(new User());
		
		mockMvc.perform(MockMvcRequestBuilders.get("/user/find/1").accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk());

	}
	
	@Test
	public void testCreateUser() throws Exception {
		final ObjectMapper mapper = new ObjectMapper();
		
		final User user = getUser(1l);
		
		final String json = mapper.writeValueAsString(user);
		
		when(service.create(new User())).thenReturn(1l);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/user/add").content(json).accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
				.andExpect(content().string(equalTo("1")));
		
		Mockito.verify(service, times(1)).create(new User());
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
		user.setRamal("ramal" + index);
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
		addr.setCep("89231-11" + index);
		return addr;
	}
	
}
