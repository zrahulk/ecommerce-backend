package com.ecommerce.ecommerce.service;

import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.dao.RoleRepo;
import com.ecommerce.ecommerce.dao.UserRepo;
import com.ecommerce.ecommerce.pojo.Role;
import com.ecommerce.ecommerce.pojo.User;

@Service
public class UserService {

	@Autowired
	UserRepo userRepo;
	@Autowired
	RoleRepo roleRepo;
	public User registration(Map<String, String> map) {
		String email = map.get("email");
		User user = userRepo.getByEmail(email);
		if(user!=null) {
			return null;
		}
		
		User newUser = null;
	//	userRepo.save(newUser);
		Optional<Role> optional = roleRepo.findByName("USER");
		  
		Role role = null;
		Set<Role> set = new HashSet<>();
		if(optional.isPresent()) {
			role = optional.get();
			
			set.add(role);
		}else {
			set.add(new Role("USER"));
		}
			newUser = new User(
					map.get("name"),
					map.get("email"),
					map.get("password"), 
					map.get("phoneNumber"),
					set,
					map.get("areaCode"),
					new Date().getTime());
			
		
		
		System.out.println("hello");
		
		System.out.println(newUser);
			
		return userRepo.save(newUser);
	}
	
	
	
	public User updatePhoneNumber(Long userId,String pNumber) {
		Optional<User> optional = userRepo.findById(userId);
		//optional.ifPresent(action);
		if(optional.isPresent()) {
			User user = optional.get();
			user.setPhoneNumber(pNumber);
			return userRepo.save(user);
		}
		else return null;
	}
}
