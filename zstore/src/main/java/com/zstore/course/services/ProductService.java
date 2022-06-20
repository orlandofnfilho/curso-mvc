package com.zstore.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zstore.course.entities.Product;
import com.zstore.course.repositories.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public Product insert(Product product) {
		return productRepository.save(product);
	}

	public List<Product> findAll() {
		return productRepository.findAll();
	}

	public void delete(Long id) {
		productRepository.deleteById(id);
	}

	public Product findById(Long id) throws Exception {
		Optional<Product> product = productRepository.findById(id);
		if (product.isEmpty()) {
			throw new Exception("Produto não encontrado!");
		}

		return product.get();
	}

}
