package com.as.responseDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FieldErrorMessage {
	private String fieldName;
	private String errorMessage;

}