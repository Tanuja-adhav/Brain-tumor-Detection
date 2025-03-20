package com.LoginRegister.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.LoginRegister.Entity.User;
import com.LoginRegister.Repo.UserRepo;
import com.LoginRegister.Request.LoginRequest;

@Service
public class UserService {
	
	@Autowired
	UserRepo repo;
	public User addUser(User user) {
		return repo.save(user);
	}
	
	public Boolean loginUser(LoginRequest loginReq) {
		Optional <User> user=repo.findById(loginReq.getUserId());
		if(user==null) {
			return false;
		}
		User user1 = user.get();
		if(!user1.getPassword().equals(loginReq.getPassword())) {
			return false;
		}
		return true;
	}
}
