package com.as.service.hims;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.as.entities.ConsoleUser;
import com.as.entities.User;
import com.as.reporsitories.ConsoleUser_Repository;
import com.as.reporsitories.UserRepository;
import com.as.requestDto.LoginRequest;
import com.as.requestDto.SignupRequest;
import com.as.responseDto.BaseResponse;
import com.as.responseDto.ResponseType;
import com.as.responseDto.UserResponse;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder encoder;

	@Autowired
	ConsoleUser_Repository console_repository;
	


	public BaseResponse findAll() {
		List<User> user = userRepository.findAll();
		List<UserResponse> userResponse = new ArrayList<UserResponse>();
		UserResponse Uresponse = null;
		for (User u : user) {
			Uresponse = new UserResponse(u.getId(), u.getUsername(), u.getLoginType());
			userResponse.add(Uresponse);
		}
		BaseResponse response = new BaseResponse();
		response.setStatus(200);
		response.setType(ResponseType.SUCCESS);
		response.setMessage("List");
		response.setBody(userResponse);
		return response;
	}

	public BaseResponse getUsersByLoginType(String loginType) {
		List<User> user = userRepository.findByLoginType(loginType);
		List<UserResponse> userResponse = new ArrayList<UserResponse>();
		UserResponse Uresponse = null;
		for (User u : user) {
			Uresponse = new UserResponse(u.getId(), u.getUsername(), u.getLoginType());
			userResponse.add(Uresponse);
		}
		BaseResponse response = new BaseResponse();
		response.setStatus(200);
		response.setType(ResponseType.SUCCESS);
		response.setMessage("List");
		response.setBody(userResponse);
		return response;
	}

	public BaseResponse delete(String username, Principal principal) {
		BaseResponse response = new BaseResponse();
		User entity = userRepository.findByUsername(username).orElse(null);
		if (entity == null) {
			response.setStatus(300);
			response.setType(ResponseType.WARNING);
			response.setMessage("Not Found");
			return response;
		} else {
			userRepository.delete(entity);
			response.setStatus(200);
			response.setType(ResponseType.SUCCESS);
			response.setMessage("Deleted");
			return response;
		}
	}

	public BaseResponse updateUser(SignupRequest signUpRequest) {
		BaseResponse response = new BaseResponse();
		User entity = userRepository.findByUsername(signUpRequest.getUsername()).orElse(null);
		if (entity == null) {
			response.setStatus(300);
			response.setType(ResponseType.WARNING);
			response.setMessage("User Not Found");
			return response;
		} else {
			if (signUpRequest.getPassword() != null) {
				entity.setPassword(encoder.encode(signUpRequest.getPassword()));
			}
			userRepository.save(entity);
			response.setStatus(200);
			response.setType(ResponseType.SUCCESS);
			response.setMessage("Updated User Data");
			return response;
		}
	}
	
	public BaseResponse ConsoleUserCreate(LoginRequest loginRequest) {
		
		BaseResponse response = new BaseResponse();
		
		ConsoleUser user = console_repository.findByUsername(loginRequest.getUsername());
		if (user == null) {
			response.setStatus(404);
			response.setType(ResponseType.RESPONSE_ERROR);
			response.setMessage("User Not Found");
			return response;
		} else {
//			user.setUsername(loginRequest.getLoginType());
			user.setPassword(encoder.encode(loginRequest.getPassword()));
			console_repository.save(user);
			response.setStatus(200);
			response.setType(ResponseType.SUCCESS);
			response.setMessage("User Updated");
			return response;
		}
		
	}
	
	public BaseResponse getConsoleUser() {
		List<ConsoleUser> userList = console_repository.findAll();
		ConsoleUser user= userList.get(0);
		UserResponse Uresponse = new UserResponse(user.getId(), user.getUsername());
		
		BaseResponse response = new BaseResponse();
		response.setStatus(200);
		response.setType(ResponseType.SUCCESS);
		response.setMessage("List");
		response.setBody(Uresponse);
		return response;
	}	
	
}
