package com.greencoffee.admin.category;

import java.util.List;
import java.util.Optional;

//import org.springframework.data.repository.ListCrudRepository;
//import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.greencoffee.common.entity.Category;

@Repository
public interface CategoryRepository extends PagingAndSortingRepository <Category, Integer> {

	Category save(Category category);

	Optional<Category> findById(int i);

	Iterable<Category> findAll();



}