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
					()->new UsernameNotFoundException("email addresss not found"));
			CustomUserDetails userDetails = optional.map(CustomUserDetails::new ).get();
			System.out.println(userDetails.getRoles());
			return  userDetails;
					
			
		
	}
	
	

}
