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
	private final String name;
	private final String lastName;
	private final String userName;
	private final String email;
	private final Role role;
	private final String phone;
	private final String ramal;
	private final String cellphone;
	private final Ocupation ocupation;
	private final Company company;
	private final Address address;
	private final LanguageAndRegion languageAndRegion;
	private final Currency currency;
	private final Target target;
	
}
