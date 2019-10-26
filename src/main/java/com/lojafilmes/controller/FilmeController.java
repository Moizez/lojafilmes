package com.lojafilmes.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lojafilmes.model.Filme;
import com.lojafilmes.model.Genero;
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
	private DiretorService diretorService;

	@Autowired
	private GeneroService generoService;
	
	@Autowired
	private ProdutoraService produtoraService;
	
	@GetMapping("/add")
	public ModelAndView add(Filme filme) {
		
		ModelAndView mv = new ModelAndView("filme/form");
		mv.addObject("diretores", diretorService.findAll());
		mv.addObject("generos", generoService.findAll());
		mv.addObject("produtoras", produtoraService.findAll());
		mv.addObject("filme", filme);
		
		return mv;
	}
	
	@PostMapping("/save")
	public ModelAndView save(@Valid Filme filme, @RequestParam("file")MultipartFile file, RedirectAttributes attr, BindingResult result) throws IOException {
		
		if (result.hasErrors()) {
			return add(filme);
		}
		
		if (!file.isEmpty()) {
			filme.setImagem(file.getBytes());
		}
		
		filmeService.save(filme);
		attr.addFlashAttribute("success", "Filme inserido com sucesso.");
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
	
	//EXIBIR IMAGEM
	@RequestMapping(path = {"/imagem/{id}"}, produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<byte[]> getImagem(@PathVariable("id") Long id){
		Filme filme = filmeService.findOne(id);
		byte[] imagem = filme.getImagem();
		final org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.setContentType(MediaType.IMAGE_JPEG);
		return new ResponseEntity<byte[]>(imagem, headers, HttpStatus.OK);
	}
	
	//MÉTODO DE BUSCA = FILMES POR NOME
	@PostMapping("/buscar/titulo")
	public ModelAndView findByFilme (@RequestParam ("titulo") String titulo) {
		ModelAndView mv = new ModelAndView("filme/listar");
		mv.addObject("filmes", filmeService.findByTitulo(titulo));
		return mv;		
	}
	
	//MÉTODO DE BUSCA = FILMES POR DIRETOR
	@PostMapping("/buscar/diretor")
	public ModelAndView findByDiretor (@RequestParam ("nome") String nome) {
		ModelAndView mv = new ModelAndView("filme/listar");
		mv.addObject("diretores", diretorService.findByNome(nome));
		return mv;		
	}
	
	//MÉTODO DE BUSCA = FILMES POR GÊNERO
	@PostMapping("/buscar/genero")
	public ModelAndView findByGenero (@RequestParam ("nome") String nome) {
		ModelAndView mv = new ModelAndView("filme/listar");
		mv.addObject("generos", generoService.findByNome(nome));
		return mv;		
	}	
	
  	//PEGAR TODOS OS GÊNEROS
	@ModelAttribute("generos")
	public List<Genero> getGeneros() {
		return generoService.findAll();
	}
	
}
