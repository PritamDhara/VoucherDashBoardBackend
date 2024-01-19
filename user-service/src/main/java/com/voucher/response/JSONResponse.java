package com.voucher.response;

import java.util.List;

public class JSONResponse {

	private String token;

	private String type = "Bearer";

	private String username;
	
	private String name;

	private List<String> roles;

	public void setRoles(List<String> roles) {

		this.roles = roles;

	}

	public JSONResponse(String accessToken, String username, String name,List<String> roles) {

		this.token = accessToken;

		this.username = username;

		this.roles = roles;
		this.name=name;

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

	public String getUsername() {

		return username;

	}

	public void setUsername(String username) {

		this.username = username;

	}

	public List<String> getRoles() {

		return roles;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
