package com.ecommerce.ecommerce.service;

import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	public User registerUserRole(User user) {

		Role role ;
		Optional<Role> optional = roleRepo.findByName("USER");
		Set<Role> set = new HashSet<>();
		if(optional.isPresent()) {
			role = optional.get();
			set.add(role);
		}else {
			set.add(new Role("USER"));
		}
		user.setCreateTs(new Date().getTime());
		user.setRoles(set);
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return userRepo.save(user);
	}
	
	
	
	public User updatePhoneNumber(Long userId,String pNumber) {
		Optional<User> optional = userRepo.findById(userId);
		if(optional.isPresent()) {
			User user = optional.get();
			user.setPhoneNumber(pNumber);
			return userRepo.save(user);
		}
		else return null;
	}

	/**
	 *
	 * @param email
	 * @return
	 */
	public boolean existByEmail(String email) {
		if(userRepo.getByEmail(email)!=null) {
			return true;
		}
		return false;
	}
}
