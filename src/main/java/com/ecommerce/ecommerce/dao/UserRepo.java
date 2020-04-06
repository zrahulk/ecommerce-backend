package com.ecommerce.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.ecommerce.pojo.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long>{
		User getByEmail(String email);
}
