package com.gft.treinamento.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gft.treinamento.entities.Starter;

@Controller
@RequestMapping("/starter")
public class StarterController {

	@RequestMapping(method = RequestMethod.GET, path = "/new")
	public ModelAndView formStarter() {

		ModelAndView mv = new ModelAndView("starterform.html");
		mv.addObject("starter", new Starter());

		return mv;

	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView newStarter(Starter starter) {

		ModelAndView mv = new ModelAndView("starter.html");

		mv.addObject("starter", starter);

		return mv;
	}

}
