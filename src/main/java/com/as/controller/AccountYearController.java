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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.as.requestDto.AccountYear_Form;
import com.as.responseDto.BaseResponse;
import com.as.responseDto.ResponseType;
import com.as.service.hims.AccountYearService;
import com.as.utils.ApiValidationUtility;

@RestController
@RequestMapping("/AccountYear")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AccountYearController {

	@Autowired
	AccountYearService service;

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/save")
	public BaseResponse save(@RequestBody AccountYear_Form form,
			Principal principal, @RequestHeader("yearCd") Long yearCd) {
				BaseResponse response =  new ApiValidationUtility().validateAPI(form);
		if(response.getStatus() == 300) {
			return response;
		}
		return service.save(form, principal);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/update")
	public BaseResponse update(@RequestBody AccountYear_Form form, Principal principal, @RequestHeader("yearCd") Long yearCd) {
		BaseResponse response =  new ApiValidationUtility().validateAPI(form);
		if(response.getStatus() == 300) {
			return response;
		}
		return service.update(form, principal);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/delete/{id}")
	public BaseResponse delete(@PathVariable(value = "id") int id, Principal principal, @RequestHeader("yearCd") Long yearCd) {
		BaseResponse response = new BaseResponse();
		if (id == 0) {
			response.setStatus(300);
			response.setType(ResponseType.RESPONSE_ERROR);
			response.setMessage("Please review data submitted");
			return response;
		}
		return service.delete(id, principal);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/findAll")
	public BaseResponse findAll(Principal principal, @RequestHeader("yearCd") Long yearCd) {
		return service.findAll(principal);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/set_defalut/{id}")
	public BaseResponse setDefalut(@PathVariable(value = "id") Long id, Principal principal, @RequestHeader("yearCd") Long yearCd) {
		BaseResponse response = new BaseResponse();
		if (id == 0) {
			response.setStatus(300);
			response.setType(ResponseType.RESPONSE_ERROR);
			response.setMessage("Please review data submitted");
			return response;
		}
		return service.setDefalut(id, principal);
	}

}
