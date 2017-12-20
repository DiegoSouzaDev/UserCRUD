package com.simplus.challenge.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

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
		final User user = new User();
		final String json = mapper.writeValueAsString(user);
		
		when(service.create(new User())).thenReturn(1l);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/user/add").contentType(MediaType.APPLICATION_JSON).content(json)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andExpect(content().string(equalTo("1")));
		
		Mockito.verify(service, times(1)).create(user);
	}

	@Test
	public void testUpdateUser() throws Exception {
		final ObjectMapper mapper = new ObjectMapper();
		final User user = new User();
		final String json = mapper.writeValueAsString(user);
		
		when(service.updateUser(user)).thenReturn(user);
		
		mockMvc.perform(MockMvcRequestBuilders.put("/user/update").contentType(MediaType.APPLICATION_JSON).content(json)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk());
		
		Mockito.verify(service, times(1)).updateUser(user);
	}

	@Test
	public void testDelete() throws Exception {
		
		Mockito.doNothing().when(service).delete(1l);
		
		mockMvc.perform(MockMvcRequestBuilders.delete("/user/delete/1").accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk());
		
		verify(service, times(1)).delete(1l);
	}
	
	@Test
	public void testFindAll() throws Exception {
		final ObjectMapper mapper = new ObjectMapper();
		final User user1 = new User();
		final User user2 = new User();
		final List<User> userList = Arrays.asList(user1, user2);
		
		final String json = mapper.writeValueAsString(userList);
		
		when(service.findAll()).thenReturn(userList);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/user/find").accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
				.andExpect(content().string(equalTo(json)));
		
		verify(service, times(1)).findAll();
		
	}
	
}
