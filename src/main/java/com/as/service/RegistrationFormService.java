package com.as.service;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.as.domain.Registration;
import com.as.dto.RegistrationDto;
import com.as.reporsitories.RegistrationFormRepository;
import com.as.responseDto.BaseResponse;
import com.as.responseDto.ResponseType;

@Service
public class RegistrationFormService {

	@Autowired
	RegistrationFormRepository repository; 

	public BaseResponse findAll(Principal principal) {
		BaseResponse response = new BaseResponse();
		response.setStatus(200);
		response.setType(ResponseType.SUCCESS);
		response.setMessage("List");
		response.setBody(repository.findAll(Sort.by(Sort.Direction.DESC, "id")));
		return response;
	}
		

	public BaseResponse save(RegistrationDto form, Principal principal) {
		BaseResponse response = new BaseResponse();
		Registration entity = repository.findByProjectName(form.getProjectName());
		if (entity != null) {
			response.setStatus(300);
			response.setType(ResponseType.WARNING);
			response.setMessage("Already Exits");
			return response;
		}
		entity = new Registration();
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

	public BaseResponse update(RegistrationDto form, Principal principal) {
		BaseResponse response = new BaseResponse();
		Long id = form.getId();
		Optional<Registration> isEntity = repository.findById(id);
		if (!isEntity.isPresent()) {
			response.setStatus(300);
			response.setType(ResponseType.WARNING);
			response.setMessage("Not Found");
			return response;
		}
		Registration entity = isEntity.get();
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
		Optional<Registration> isEntity = repository.findById(id);
		if (isEntity == null) {
			response.setStatus(300);
			response.setType(ResponseType.WARNING);
			response.setMessage("Not Found");
			return response;
		} else {
			Registration entity = isEntity.get();
			repository.delete(entity);
			response.setStatus(200);
			response.setType(ResponseType.SUCCESS);
			response.setMessage("Deleted");
			return response;
		}
	}

}
