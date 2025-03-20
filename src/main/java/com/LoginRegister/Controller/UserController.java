package com.LoginRegister.Controller;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.LoginRegister.Entity.User;
import com.LoginRegister.Repo.UserRepo;
import com.LoginRegister.Request.LoginRequest;
import com.LoginRegister.Service.UserService;

@RestController
public class UserController {
	
	@Autowired
    private UserRepo userRepository;
	@Autowired
	UserService service;
	@PostMapping("/addUser")
	@CrossOrigin(origins = "http://localhost:4200/")
	public User addUser(@RequestBody User user) {
		return service.addUser(user);
	}
	
	@PostMapping("/loginUser")
	@CrossOrigin(origins = "http://localhost:4200/")
	public boolean loginUser(@RequestBody LoginRequest loginReq) {
		return service.loginUser(loginReq);
	}
	 @GetMapping("/{email}")
	 @CrossOrigin(origins = "http://localhost:4200/")
	    public ResponseEntity<?> getUserProfile(@PathVariable String email) {
		 email = email.trim(); 
	        Optional<User> user = userRepository.findById(email);
	        
	        if (user.isPresent()) {
	            return ResponseEntity.ok().body(Map.of(
	                "name", user.get().getName(),
	                "email", user.get().getEmail()
	            ));
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("message", "User not found"));
	        }
	 }
}
