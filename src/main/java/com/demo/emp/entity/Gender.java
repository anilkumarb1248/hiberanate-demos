package com.demo.emp.entity;

public enum Gender {

	MALE("Male"), FEMALE("Female"), TRANSGENDER("Transgender");

	private String gender;

	private Gender(String gender) {
		this.gender = gender;
	}

	public String getCode() {
		return gender;
	}
}
