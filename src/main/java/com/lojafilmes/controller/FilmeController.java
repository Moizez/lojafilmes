package com.lojafilmes.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
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
		mv.addObject("generos", generoService.findAll());
		mv.addObject("produtoras", produtoraService.findAll());
		mv.addObject("filme", filme);
		
		return mv;
	}
	
	@PostMapping("/save")
	public ModelAndView save(@Valid Filme filme, @RequestParam("file")MultipartFile file, BindingResult result) throws IOException {
		
		if (result.hasErrors()) {
			return add(filme);
		}
		
		if (!file.isEmpty()) {
			filme.setImagem(file.getBytes());
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
	
	@RequestMapping(path = {"/imagem/{id}"}, produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<byte[]> getImagem(@PathVariable("id") Long id){
		Filme filme = filmeService.findOne(id);
		byte[] imagem = filme.getImagem();
		final org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.setContentType(MediaType.IMAGE_JPEG);
		return new ResponseEntity<byte[]>(imagem, headers, HttpStatus.OK);
	}
	
}
