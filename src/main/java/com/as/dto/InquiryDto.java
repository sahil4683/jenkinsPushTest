package com.as.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InquiryDto {
	
	private Long id;

	@NotNull
	@Size(min = 3, max = 150)
	@Column(nullable = false)
	private String customerName;
	private Long mobileNumber;
	private String address;
	private String lookingFor;
	private String configuration;
	private Long budget;
	private String referenceFrom;
}
