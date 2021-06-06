package com.demo.embeded.primarykey;

import java.io.Serializable;

import javax.persistence.Column;
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
public class ProductPK implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "PRODUCT_ID")
	private Integer productId;

	@Column(name = "PRODUCT_GROUP_ID")
	private Integer productGroupId;

}
