package com.zstore.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zstore.course.entities.Supplier;
import com.zstore.course.repositories.SupplierRepository;

@Service
public class SupplierService {

	@Autowired
	private SupplierRepository supplierRepository;

	public Supplier insert(Supplier supplier) {
		return supplierRepository.save(supplier);
	}

	public List<Supplier> findAll() {
		return supplierRepository.findAll();
	}

	public void delete(Long id) {
		supplierRepository.deleteById(id);
	}

	public Supplier findById(Long id) throws Exception {
		Optional<Supplier> supplier = supplierRepository.findById(id);
		if (supplier.isEmpty()) {
			throw new Exception("Fornecedor não encontrado!");
		}

		return supplier.get();
	}
}
