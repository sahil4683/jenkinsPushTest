package com.as.security;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class JwtResponse {
	private String token;
	@JsonIgnore
	private String type = "Bearer";
	private Long id;
	private String username;

//	private List<String> roles;
	private String roles;

//	private String loginType;

	public JwtResponse(String accessToken, Long id, String username, String roles) {
		this.token = accessToken;
		this.id = id;
		this.username = username;
		this.roles = roles;
//		this.loginType = loginType;
	}

	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRoles() {
		return roles;
	}

//	public String getLoginType() {
//		return loginType;
//	}
//
//	public void setLoginType(String loginType) {
//		this.loginType = loginType;
//	}

}
