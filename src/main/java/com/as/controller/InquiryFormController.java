package com.as.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.as.dto.InquiryDto;
import com.as.responseDto.BaseResponse;
import com.as.responseDto.ResponseType;
import com.as.service.InquiryFormService;
import com.as.utils.ApiValidationUtility;

@RestController
@RequestMapping("/inquiry-form")
@CrossOrigin(origins = "*", maxAge = 3600)
public class InquiryFormController {
	
	@Autowired
	InquiryFormService service;

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/findAll")
	public BaseResponse findAll(Principal principal) {
		return service.findAll(principal);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/save")
	public BaseResponse save(@RequestBody InquiryDto form,
			Principal principal) {
				BaseResponse response =  new ApiValidationUtility().validateAPI(form);
		if(response.getStatus() == 300) {
			return response;
		}
		return service.save(form, principal);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/update")
	public BaseResponse update(@RequestBody InquiryDto form, Principal principal) {
		BaseResponse response =  new ApiValidationUtility().validateAPI(form);
		if(response.getStatus() == 300) {
			return response;
		}
		return service.update(form, principal);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/delete/{id}")
	public BaseResponse delete(@PathVariable(value = "id") Long id, Principal principal) {
		BaseResponse response = new BaseResponse();
		if (id == 0) {
			response.setStatus(300);
			response.setType(ResponseType.RESPONSE_ERROR);
			response.setMessage("Please review data submitted");
			return response;
		}
		return service.delete(id, principal);
	}

}
