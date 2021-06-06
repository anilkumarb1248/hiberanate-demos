package com.demo.embeded.collection;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
@Table(name = "USER_DETAILS")
public class UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "USER_ID")
	private Long id;

	@Column(name = "USER_NAME")
	private String name;

	@Column(name = "SALARY")
	private double salary;

//	@ElementCollection(fetch = FetchType.EAGER)
	@ElementCollection(fetch = FetchType.LAZY)
	@JoinTable(name = "USER_ADDRESS", joinColumns = @JoinColumn(name = "USER_ID"))
//	@GenericGenerator(name = "sequence-gen", strategy = "sequence")
//	@CollectionId(columns = { @Column(name = "ADDRESS_ID") }, generator = "sequence-gen", type = @Type(type = "long"))

	private List<Address> addressList = new ArrayList<>();

}


