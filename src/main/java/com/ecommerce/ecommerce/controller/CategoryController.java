package com.ecommerce.ecommerce.controller;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ecommerce.constants.EcommerceConstant;
import com.ecommerce.ecommerce.elasticsearch.Category;
import com.ecommerce.ecommerce.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	public CategoryService categoryService;
	
	@PostMapping("/addCategory")
	public Category addCategory(@RequestBody Map<String, String> map) {
		Category category =  new Category(map.get("name"),
						map.get("description"),
						EcommerceConstant.CATEGORY,
						new Date().getTime());
		return categoryService.addCategory(category);
	}
		
	
	@GetMapping("hello")
	public String hello() {
		return "hello sir";
	}
	
	@PostMapping("categoryById")
	public Category  getCategoryById(@RequestParam(value="id") String id) {
		System.out.println("hello category");
		return categoryService.allCategories(id);
	}
}
