package com.course.springcourse.model.repository;

import com.course.springcourse.model.entities.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepo extends CrudRepository<Product, Long> {

    List<Product> findByNameContains(String name);
}
