package com.zstore.course.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.zstore.course.entities.Supplier;
import com.zstore.course.services.SupplierService;

@Controller
@RequestMapping("/suppliers")
public class SupplierController {

	@Autowired
	private SupplierService supplierService;

	@GetMapping
	public ModelAndView list() {

		ModelAndView mv = new ModelAndView("suppliers/list");
		mv.addObject("list", supplierService.findAll());

		return mv;

	}

	@GetMapping("/edit")
	public ModelAndView edit(@RequestParam(required = false) Long id) {

		ModelAndView mv = new ModelAndView("suppliers/form");

		Supplier supplier;

		if (id == null) {
			supplier = new Supplier();
		} else {
			try {
				supplier = supplierService.findById(id);
			} catch (Exception e) {
				supplier = new Supplier();
				mv.addObject("message", e.getMessage());
			}
		}

		mv.addObject("supplier", supplier);

		return mv;

	}

	@PostMapping("/edit")
	public ModelAndView edit(@Valid Supplier supplier, BindingResult bindingResult) {
		ModelAndView mv = new ModelAndView("suppliers/form");

		boolean newSupplier = true;

		if (supplier.getId() != null) {
			newSupplier = false;
		}
		if (bindingResult.hasErrors()) {
			mv.addObject("supplier", supplier);
		}

		Supplier supplierSaved = supplierService.insert(supplier);

		if (newSupplier) {
			mv.addObject("supplier", new Supplier());
		} else {
			mv.addObject("supplier", supplierSaved);
		}

		mv.addObject("message", "Fornecedor salvo com sucesso.");

		return mv;
	}

}
