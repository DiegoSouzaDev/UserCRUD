package com.simplus.challenge.model;

import com.simplus.challenge.enumerator.State;

import lombok.Data;

@Data
public class Address {
	private Long id;
	private String street;
	private String number;
	private String neighborhood;
	private String city;
	private String zipCode;
	private State state;
	private String country;
}
