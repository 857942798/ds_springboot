package com.ds.service;


import com.ds.domain.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {

	public int add(User user);
	
	
	
	public int addAccount();

}
