package com.course.springcourse.model.repository;

import com.course.springcourse.model.entities.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepo extends CrudRepository<Category, Long> {
}
