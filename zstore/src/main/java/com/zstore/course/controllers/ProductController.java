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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.zstore.course.entities.Product;
import com.zstore.course.services.CategoryService;
import com.zstore.course.services.ProductService;
import com.zstore.course.services.SupplierService;

@Controller
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private SupplierService supplierService;

	@GetMapping("/edit")
	public ModelAndView edit(@RequestParam(required = false) Long id) {
		ModelAndView mv = new ModelAndView("products/form");

		Product product;

		if (id == null) {
			product = new Product();
		} else {
			try {
				product = productService.findById(id);
			} catch (Exception e) {
				product = new Product();
				mv.addObject("message", e.getMessage());
			}
		}

		mv.addObject("product", product);

		mv.addObject("categories", categoryService.findAll());
		mv.addObject("suppliers", supplierService.findAll());

		return mv;
	}

	@PostMapping("/edit")
	public ModelAndView edit(@Valid Product product, BindingResult bindingResult) {
		ModelAndView mv = new ModelAndView("products/form");
		boolean newProduct = true;

		if (product.getId() != null) {
			newProduct = false;
		}

		if (bindingResult.hasErrors()) {
			mv.addObject("product", product);
			return mv;
		}

		Product productSaved = productService.insert(product);

		if (newProduct) {
			mv.addObject("product", new Product());
		} else {
			mv.addObject("product", productSaved);
		}

		mv.addObject("categories", categoryService.findAll());
		mv.addObject("suppliers", supplierService.findAll());
		mv.addObject("message", "Produto salvo com sucesso!");
		return mv;

	}

	@GetMapping
	public ModelAndView list() {
		ModelAndView mv = new ModelAndView("products/list");
		mv.addObject("list", productService.findAll());

		return mv;

	}

	@GetMapping("/delete")
	public ModelAndView delete(@RequestParam Long id, RedirectAttributes redirectAttributes) {

		ModelAndView mv = new ModelAndView("redirect:/products");

		try {
			productService.delete(id);
			redirectAttributes.addFlashAttribute("message", "Produto excluido com sucesso!");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("message", "Erro ao excluir o produto " + e.getMessage());
		}

		return mv;

	}

}
