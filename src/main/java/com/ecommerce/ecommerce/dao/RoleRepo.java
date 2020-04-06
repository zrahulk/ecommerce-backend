package com.ecommerce.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.ecommerce.pojo.Role;
@Repository
public interface RoleRepo extends JpaRepository<Role, Integer> {

}
