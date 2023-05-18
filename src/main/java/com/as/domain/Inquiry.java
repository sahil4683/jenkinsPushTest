package com.as.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Inquiry")
public class Inquiry {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String customerName;
	private Long mobileNumber;
	private String address;
	private String lookingFor;
	private String configuration;
	private Long budget;
	private String referenceFrom;
}
