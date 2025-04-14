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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.LoginRegister.Entity.User;
import com.LoginRegister.Repo.UserRepo;
import com.LoginRegister.Request.LoginRequest;
import com.LoginRegister.Service.UserService;

@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/api") 
@RestController
public class UserController {
	
	@Autowired
    private UserRepo userRepository;
	@Autowired
	UserService service;
	//@CrossOrigin(origins = "http://localhost:4200/")
	@PostMapping("/addUser")
	public ResponseEntity<?> addUser(@RequestBody User user) {
	    // Check if the user already exists
	    Optional<User> existingUser = userRepository.findById(user.getEmail());
	    
	    if (existingUser.isPresent()) {
	        // Return a 400 BAD REQUEST with a message if the email exists
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", "User with this email already exists"));
	    }
	    
	    // Save the new user
	    User savedUser = service.addUser(user);
	    return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
	}

//	public User addUser(@RequestBody User user) {
//		return service.addUser(user);
//		
//	}
	
	@PostMapping("/loginUser")
//	@CrossOrigin(origins = "http://localhost:4200/")
	public boolean loginUser(@RequestBody LoginRequest loginReq) {
		return service.loginUser(loginReq);
	}
	 @GetMapping("/{email}")
	// @CrossOrigin(origins = "http://localhost:4200/")
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
