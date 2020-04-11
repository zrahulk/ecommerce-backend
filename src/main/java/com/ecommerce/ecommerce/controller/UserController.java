package com.ecommerce.ecommerce.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.ecommerce.ecommerce.dao.UserRepo;
import com.ecommerce.ecommerce.pojo.CustomUserDetails;
import com.ecommerce.ecommerce.pojo.User;
import com.ecommerce.ecommerce.service.UserService;

@RestController("/")
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	UserRepo userRepo;
	@GetMapping("/welcome")
	public String welcome() {
		return "welcome to new world";
	}
	
	@GetMapping("/user" )
	public ResponseEntity<Object> user() {
		return new ResponseEntity<Object>(
				userRepo.findByEmail("z.rahul.k@gmail.com").get().getRoles(),
				HttpStatus.OK);
	}
	
	
	@GetMapping("/checkEmail")
	public boolean checkEmail(@RequestBody String email) {
		if(userRepo.getByEmail(email)!=null) {
			return true;
		}
		return false;
	}
	
	@GetMapping("/ForgetPassword")
	public String forgetPassword(@RequestBody String email ) {
		User user = userRepo.getByEmail(email); 
		if(user==null) {
			return "wrong Email";
		}
		
		return "Password is "+user.getPassword();
		
	}
	
	
	
	
	@PostMapping("/registration")
	public ResponseEntity<Object> registration(
			@RequestBody
			Map<String, String> map) {
			System.out.println(map.get("name"));
			User user =	userService.registration(map);
			if (user==null) {
				return new ResponseEntity<Object>(
						"user already exist", HttpStatus.NOT_FOUND);
				}
			else {
				return new ResponseEntity<Object>(
						user, HttpStatus.CREATED);
			}
		
	}
	
	
	@Secured("ROLE_USER")
	@GetMapping("/secured")
	public ResponseEntity<Object> secWelcome(@AuthenticationPrincipal CustomUserDetails userDetailSevice) {
			
		return new ResponseEntity<Object>(userDetailSevice,HttpStatus.OK);
	}
	
	
	
}
