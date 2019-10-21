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

import com.lojafilmes.model.Filme;
import com.lojafilmes.service.DiretorService;
import com.lojafilmes.service.FilmeService;
import com.lojafilmes.service.GeneroService;
import com.lojafilmes.service.ProdutoraService;

@Controller
@RequestMapping("/filme")
public class FilmeController {
	
	@Autowired
	private FilmeService filmeService;
	
	@Autowired
	private DiretorService diretorServicer;

	@Autowired
	private GeneroService generoService;
	
	@Autowired
	private ProdutoraService produtoraService;
	
	@GetMapping("/add")
	public ModelAndView add(Filme filme) {
		
		ModelAndView mv = new ModelAndView("filme/form");
		mv.addObject("diretores", diretorServicer.findAll());
		mv.addObject("generos", generoService.listaAll());
		mv.addObject("produtoras", produtoraService.listaAll());
		mv.addObject("filme", filme);
		
		return mv;
	}
	
	@PostMapping("/save")
	public ModelAndView save(@Valid Filme filme, BindingResult result) {
		
		if(result.hasErrors()) {
			return add(filme);
		}
		
		filmeService.save(filme);
		
		return findAll();
	}
	
	@GetMapping("/listar")
	public ModelAndView findAll() {
		
		ModelAndView mv = new ModelAndView("filme/listar");
		mv.addObject("filmes", filmeService.findAll());
		
		return mv;
	}
	
	@GetMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable("id") Long id) {
		
		return add(filmeService.findOne(id));
	}
	
	@GetMapping("/details/{id}")
	public ModelAndView details(@PathVariable("id") Long id) {
		
		ModelAndView mv = new ModelAndView("filme/detalhes");
		mv.addObject("filme", filmeService.findOne(id));
		
		return mv;
	}
	
	@GetMapping("/delete/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {
		
		filmeService.delete(id);
		
		return findAll();
	}

}
