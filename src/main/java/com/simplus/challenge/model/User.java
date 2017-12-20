package com.simplus.challenge.model;

import com.simplus.challenge.enumerator.Currency;
import com.simplus.challenge.enumerator.LanguageAndRegion;
import com.simplus.challenge.enumerator.Ocupation;
import com.simplus.challenge.enumerator.Role;
import com.simplus.challenge.enumerator.Target;

import lombok.Data;

@Data
public class User {
	private Long id;
	private String name;
	private String lastName;
	private String userName;
	private String email;
	private Role role;
	private String phone;
	private String extension;
	private String cellphone;
	private Ocupation ocupation;
	private Company company;
	private Address address;
	private LanguageAndRegion languageAndRegion;
	private Currency currency;
	private Target target;
	
}
