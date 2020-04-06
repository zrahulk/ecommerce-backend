package com.ecommerce.ecommerce.service;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.dao.UserRepo;
import com.ecommerce.ecommerce.pojo.User;

@Service
public class UserService {

	@Autowired
	UserRepo userRepo;
	
	public User registration(Map<String, String> map) {
		String email = map.get("email");
		User user = userRepo.getByEmail(email);
		if(user!=null) {
			return null;
		}
		
		
		return userRepo.save(new User(
				map.get("name"),
				map.get("email"),
				map.get("password"), 
				map.get("phoneNumber"),
				map.get("areaCode"),
				new Date())); 
	}
}
