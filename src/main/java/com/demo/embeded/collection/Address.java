package com.demo.embeded.collection;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(doNotUseGetters = true)

@Embeddable
public class Address {

	private String street;
	private String city;
	private String state;
	private String pincode;

}
