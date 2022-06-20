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

import com.zstore.course.entities.Category;
import com.zstore.course.services.CategoryService;

@Controller
@RequestMapping("categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping
	public ModelAndView list() {

		ModelAndView mv = new ModelAndView("categories/list");
		mv.addObject("list", categoryService.findAll());
		return mv;

	}

	@GetMapping("/new")
	public ModelAndView create() {

		ModelAndView mv = new ModelAndView("categories/form");
		mv.addObject("category", new Category());

		return mv;

	}

	@PostMapping("/new")
	public ModelAndView save(@Valid Category category, BindingResult bindingResult) {

		ModelAndView mv = new ModelAndView("categories/form");

		boolean newCategory = true;

		if (category.getId() != null) {
			newCategory = false;
		}

		if (bindingResult.hasErrors()) {
			mv.addObject("category", category);
			return mv;
		}

		Category categorySaved = categoryService.insert(category);

		if (newCategory) {
			mv.addObject("category", new Category());
		} else {
			mv.addObject("category", categorySaved);

		}
		mv.addObject("message", "Categoria salva com sucesso");

		return mv;

	}

	@GetMapping("/delete")
	public ModelAndView delete(@RequestParam Long id, RedirectAttributes redirectAttributes) {

		ModelAndView mv = new ModelAndView("redirect:/categories");

		try {
			categoryService.delete(id);
			redirectAttributes.addFlashAttribute("message", "Categoria excluída com sucesso.");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("message", "Erro ao excluir categoria!" + e.getMessage());
		}

		return mv;

	}

	@GetMapping("/edit")
	public ModelAndView edit(@RequestParam Long id) {
		ModelAndView mv = new ModelAndView("categories/form");
		Category category;

		try {
			category = categoryService.finById(id);
		} catch (Exception e) {
			category = new Category();
			mv.addObject("message", e.getMessage());
		}

		mv.addObject("category", category);

		return mv;
	}

}
