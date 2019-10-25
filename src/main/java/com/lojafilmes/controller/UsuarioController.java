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

import com.lojafilmes.model.Usuario;
import com.lojafilmes.service.UsuarioService;


@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/add")
	public ModelAndView add(Usuario usuario) {
		
		ModelAndView mv = new ModelAndView("/usuario/form");
		mv.addObject("usuario", usuario);
		
		return mv;
	}
	
	@PostMapping("/save")
	public ModelAndView save(@Valid Usuario usuario, @RequestParam("file")MultipartFile file, BindingResult result) throws IOException {
		
		if(result.hasErrors()) {
			return add(usuario);
		}
		
		if (!file.isEmpty()) {
			usuario.setImagem(file.getBytes());
		}
		
		usuarioService.save(usuario);
		
		return findAll();
	}
	
	
	@GetMapping("/listar")
	public ModelAndView findAll() {
		
		ModelAndView mv = new ModelAndView("usuario/listar");
		mv.addObject("usuarios", usuarioService.findAll());
		
		return mv;
	}
	
	@GetMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable("id") Long id) {
		
		return add(usuarioService.findOne(id));
	}
	
	@GetMapping("/delete/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {
		
		usuarioService.delete(id);
		
		return findAll();
	}
	
	@RequestMapping(path = {"/imagem/{id}"}, produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<byte[]> getImagem(@PathVariable("id") Long id){
		Usuario usuario = usuarioService.findOne(id);
		byte[] imagem = usuario.getImagem();
		final org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.setContentType(MediaType.IMAGE_JPEG);
		return new ResponseEntity<byte[]>(imagem, headers, HttpStatus.OK);
	}

}
