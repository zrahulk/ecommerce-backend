package com.ecommerce.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.ecommerce.pojo.Address;

@Repository
public interface AddressRepo extends JpaRepository<Address, Integer> {

}
