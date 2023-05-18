package com.as.service;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.as.domain.Inquiry;
import com.as.dto.InquiryDto;
import com.as.reporsitories.InquiryFormRepository;
import com.as.responseDto.BaseResponse;
import com.as.responseDto.ResponseType;

@Service
public class InquiryFormService {
	
	@Autowired
	InquiryFormRepository repository; 

	public BaseResponse findAll(Principal principal) {
		BaseResponse response = new BaseResponse();
		response.setStatus(200);
		response.setType(ResponseType.SUCCESS);
		response.setMessage("List");
		response.setBody(repository.findAll(Sort.by(Sort.Direction.DESC, "id")));
		return response;
	}
		

	public BaseResponse save(InquiryDto form, Principal principal) {
		BaseResponse response = new BaseResponse();
		Inquiry entity = repository.findByCustomerNameAndMobileNumber(form.getCustomerName(), form.getMobileNumber());
		if (entity != null) {
			response.setStatus(300);
			response.setType(ResponseType.WARNING);
			response.setMessage("Already Exits");
			return response;
		}
		entity = new Inquiry();
		BeanUtils.copyProperties(form, entity);
		entity = repository.save(entity);
		if (entity.getId() != 0) {
			response.setStatus(200);
			response.setType(ResponseType.SUCCESS);
			response.setMessage("Saved");
			return response;
		}
		return response;
	}

	public BaseResponse update(InquiryDto form, Principal principal) {
		BaseResponse response = new BaseResponse();
		Long id = form.getId();
		Optional<Inquiry> isEntity = repository.findById(id);
		if (!isEntity.isPresent()) {
			response.setStatus(300);
			response.setType(ResponseType.WARNING);
			response.setMessage("Not Found");
			return response;
		}
		Inquiry entity = isEntity.get();
		BeanUtils.copyProperties(form, entity);
		entity = repository.save(entity);
		if (entity.getId() != 0) {
			response.setStatus(200);
			response.setType(ResponseType.SUCCESS);
			response.setMessage("Update");
			return response;
		}
		return response;
	}

	public BaseResponse delete(Long id, Principal principal) {
		BaseResponse response = new BaseResponse();
		Optional<Inquiry> isEntity = repository.findById(id);
		if (isEntity == null) {
			response.setStatus(300);
			response.setType(ResponseType.WARNING);
			response.setMessage("Not Found");
			return response;
		} else {
			Inquiry entity = isEntity.get();
			repository.delete(entity);
			response.setStatus(200);
			response.setType(ResponseType.SUCCESS);
			response.setMessage("Deleted");
			return response;
		}
	}


}
