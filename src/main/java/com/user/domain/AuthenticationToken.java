package com.user.domain;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class AuthenticationToken {
	
	private String username;
	private String name;
    private Collection<? extends GrantedAuthority> authorities;
    private String token;
    
    public AuthenticationToken(String username, String name, 
    		Collection<? extends GrantedAuthority> collection, String token) {
         this.username = username;
         this.name = name;
         this.authorities = collection;
         this.token = token;
    }
    public String getUsername() {
         return username;
    }
    public void setUsername(String username) {
         this.username = username;
    }
    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Collection<? extends GrantedAuthority> getAuthorities() {
         return authorities;
    }
    public void setAuthorities(Collection<GrantedAuthority> authorities) {
         this.authorities = authorities;
    }
    public String getToken() {
         return token;
    }
    public void setToken(String token) {
         this.token = token;
    }  

}
