package com.demo.mappings;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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

@Entity
@Table(name = "PERSON")
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PERSON_ID")
	private Long id;

	@Column(name = "PERSON_NAME")
	private String name;

//	@OneToOne
//	@JoinColumn(name = "VEHICLE_ID")
//	private Vehicle vehicle;

	@OneToMany
	@JoinTable(name="PERSON_VEHICLE", 
		joinColumns = @JoinColumn(name="PERSON_ID"), 
		inverseJoinColumns = @JoinColumn(name="VEHICLE_ID"))
	private List<Vehicle> vehiclesList = new ArrayList<>();
}




