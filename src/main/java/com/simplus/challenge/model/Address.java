package com.simplus.challenge.model;

import com.simplus.challenge.enumerator.State;

import lombok.Data;

@Data
public class Address {
	private final Long id;
	private final String street;
	private final String number;
	private final String neighborhood;
	private final String city;
	private final String cep;
	private final State state;
	private final String country;
}
