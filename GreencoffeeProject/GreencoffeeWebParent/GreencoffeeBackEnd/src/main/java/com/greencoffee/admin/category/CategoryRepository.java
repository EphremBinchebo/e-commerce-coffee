package com.greencoffee.admin.category;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import com.greencoffee.common.entity.Category;

public interface CategoryRepository extends PagingAndSortingRepository<Category, Integer> {

	Category save(Category category);

}