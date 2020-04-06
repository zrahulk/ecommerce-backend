package com.ecommerce.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ecommerce.dao.UserRepo;

@RestController
public class UserController {

	@Autowired
	UserRepo userrepo;
	
	/*
	 * @PostMapping("/saveUser") public User saveUser(@RequestMapping)
	 */
	@GetMapping("/")
	public String welcome() {
		return "welcome to new world";
	}
	
}
