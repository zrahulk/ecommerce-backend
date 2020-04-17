package com.ecommerce.ecommerce.dao;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.ecommerce.elasticsearch.Category;

@Repository
public interface CategoryRepo extends ElasticsearchRepository<Category, String> {

}
