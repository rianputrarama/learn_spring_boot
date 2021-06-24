package com.course.springcourse.model.repository;

import com.course.springcourse.model.entities.Supplier;
import org.springframework.data.repository.CrudRepository;

public interface SupplierRepo extends CrudRepository<Supplier, Long> {
}
