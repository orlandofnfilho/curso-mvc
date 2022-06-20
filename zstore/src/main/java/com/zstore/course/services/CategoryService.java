package com.zstore.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zstore.course.entities.Category;
import com.zstore.course.repositories.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	public Category insert(Category category) {
		return categoryRepository.save(category);
	}

	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	public void delete(Long id) {
		categoryRepository.deleteById(id);
	}

	public Category finById(Long id) throws Exception {
		Optional<Category> category = categoryRepository.findById(id);
		if (category.isEmpty()) {
			throw new Exception("Categoria não encontrada!");
		}

		return category.get();
	}

}
