package com.as.security;

import java.security.Principal;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.as.requestDto.LoginRequest;
import com.as.requestDto.SignupRequest;
import com.as.responseDto.BaseResponse;
import com.as.responseDto.ResponseType;
import com.as.service.hims.UserService;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

	@Autowired
	UserService userService;
	
//	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/allUser")
	public BaseResponse findAll(Principal principal) {
		return userService.findAll();
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/getUsersByLoginType/{login_type}")
	public BaseResponse getUsersByLoginType(@PathVariable(value="login_type") String loginType,Principal principal) {
		return userService.getUsersByLoginType(loginType);
	}
	

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/delete/{username}")
	public BaseResponse deleteUser(@PathVariable(value = "username") String username, Principal principal) {
		BaseResponse response = new BaseResponse();
		if (username == null) {
			response.setStatus(300);
			response.setType(ResponseType.RESPONSE_ERROR);
			response.setMessage("Please review data submitted");
			return response;
		}
		return userService.delete(username, principal);
	}
	
	@PostMapping("/update")
	public BaseResponse updateUser(@Valid @RequestBody SignupRequest signUpRequest) {
		return userService.updateUser(signUpRequest);
	}
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/console-update")
	public BaseResponse ConsoleUserCreate(@RequestBody LoginRequest loginRequest, ServletRequest request,
			HttpServletRequest httpServletRequest) {
		return userService.ConsoleUserCreate(loginRequest);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/getConsoleUser")
	public BaseResponse getConsoleUser(Principal principal) {
		return userService.getConsoleUser();
	}
	
}
