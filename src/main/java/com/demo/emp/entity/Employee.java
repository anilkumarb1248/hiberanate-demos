package com.demo.emp.entity;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

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
@EqualsAndHashCode(of = { "id", "name" }, doNotUseGetters = true)

@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "EMPLOYEE")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "emp_seq")
	@SequenceGenerator(initialValue = 1, name = "emp_seq", sequenceName = "EMPLOYEE_SEQUENCE", allocationSize = 50)
	@Column(name = "EMPLOYEE_ID", updatable = false, nullable = false)
	private Long id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "SALARY")
	private double salary;

//	@Column(name = "ROLE")
//	private Role role;
//
//	@Column(name = "GENDER")
//	private Gender gender;

}
