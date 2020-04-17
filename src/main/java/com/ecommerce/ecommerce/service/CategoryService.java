package com.ecommerce.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ecommerce.ecommerce.dao.CategoryRepo;
import com.ecommerce.ecommerce.elasticsearch.Category;



@Service
public class CategoryService {

	@Autowired
	CategoryRepo categoryRepo;
	
	public Category addCategory(Category category) {
		return categoryRepo.save(category);
	}
	
	public Category allCategories(String id) {
		return categoryRepo.findById(id).orElse(null);
	}
}
