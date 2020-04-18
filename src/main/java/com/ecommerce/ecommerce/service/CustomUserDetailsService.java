package com.ecommerce.ecommerce.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.dao.UserRepo;
import com.ecommerce.ecommerce.pojo.CustomUserDetails;
import com.ecommerce.ecommerce.pojo.User;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	UserRepo userRepo;
	@Override
	public CustomUserDetails loadUserByUsername(String email) 
			throws UsernameNotFoundException {
			Optional<User> optional
						= userRepo.findByEmail(email);
			optional.orElseThrow(
					()->new UsernameNotFoundException("user name not found"));
			return optional.map(CustomUserDetails::new ).get();


					
	}

	@Transactional
	public CustomUserDetails loadUserById(Long id) {
		CustomUserDetails user =  new CustomUserDetails(userRepo.getOne(id));
	if(user==null) throw new UsernameNotFoundException("User not Found");
	return user;
	}
	
	

}
