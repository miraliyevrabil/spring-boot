package com.rabilmiraliyev.oauth2.service;

import com.rabilmiraliyev.oauth2.model.Provider;
import com.rabilmiraliyev.oauth2.model.User;
import com.rabilmiraliyev.oauth2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;
	
	public void processOAuthPostLogin(String username) {
		User existUser = repo.getUserByUsername(username);
		
		if (existUser == null) {
			User newUser = new User();
			newUser.setUsername(username);
			newUser.setProvider(Provider.GOOGLE);
			newUser.setEnabled(true);
			repo.save(newUser);
			System.out.println("Created new user: " + username);
		}
		
	}
	
}
