package com.springcloud.auth.authorization.oauth.password;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class PasswordAuthentication implements Authentication {

	private static final long serialVersionUID = 7622559567885228781L;
	
	private Collection<? extends GrantedAuthority> authorities;
	private String credentials;
	private Object details;
	private String principal;
	private boolean authenticated;
	
	public PasswordAuthentication(String principal, String credentials) {
		this.principal = principal;
		this.credentials = credentials;
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.principal;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.authorities;
	}

	@Override
	public String getCredentials() {
		// TODO Auto-generated method stub
		return this.credentials;
	}

	@Override
	public Object getDetails() {
		// TODO Auto-generated method stub
		return this.details;
	}

	@Override
	public String getPrincipal() {
		// TODO Auto-generated method stub
		return this.principal;
	}

	@Override
	public boolean isAuthenticated() {
		// TODO Auto-generated method stub
		return authenticated;
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		this.authenticated = isAuthenticated;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public void setCredentials(String credentials) {
		this.credentials = credentials;
	}

	public void setDetails(Object details) {
		this.details = details;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

}
