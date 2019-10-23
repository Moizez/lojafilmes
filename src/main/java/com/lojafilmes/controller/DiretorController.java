package com.lojafilmes.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lojafilmes.model.Diretor;
import com.lojafilmes.service.DiretorService;

@Controller
@RequestMapping("/diretor")
public class DiretorController {
	
	@Autowired
	private DiretorService service;
	
	@GetMapping("/add")
	public ModelAndView add(Diretor diretor) {
		
		ModelAndView mv = new ModelAndView("diretor/form");
		mv.addObject("diretor", diretor);
		
		return mv;
	}
	
	@PostMapping("/save")
	public ModelAndView save(@Valid Diretor diretor, BindingResult result) {
		
		if(result.hasErrors()) {
			return add(diretor);
		}
		
		service.save(diretor);
		
		return findAll();
	}
	
	@GetMapping("/listar")
	public ModelAndView findAll() {
		
		ModelAndView mv = new ModelAndView("diretor/listar");
		mv.addObject("diretores", service.findAll());
		
		return mv;
	}
	
	@GetMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable("id") Long id) {
		
		return add(service.findOne(id));
	}
	
	@GetMapping("/delete/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {
		
		service.delete(id);
		
		return findAll();
	}

}
