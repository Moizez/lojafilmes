package com.lojafilmes.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lojafilmes.model.Filme;
import com.lojafilmes.repository.FilmeRepository;

@RestController
@RequestMapping("/filme")
public class APIController {
	
	@Autowired
	private FilmeRepository repository;
	
	@PostMapping
	public Filme add(@Valid @RequestBody Filme filme) {
		return repository.save(filme);
	}
	
	@GetMapping
	public List<Filme> listar(){
		return repository.findAll();
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Filme> buscar(@PathVariable Long id){
		Filme filme = repository.getOne(id);
		
		if (filme == null) {
			return ResponseEntity.notFound().build();
			
		}
		return ResponseEntity.ok(filme);
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Filme> update(@PathVariable Long id,
			@Valid @RequestBody Filme filme){
		Filme filmeExist = repository.getOne(id);
		
		if (filmeExist == null) {
			return ResponseEntity.notFound().build();
		}
		
		BeanUtils.copyProperties(filme, filmeExist, "id");
		
		filmeExist = repository.save(filmeExist);
		
		return ResponseEntity.ok(filmeExist);
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id){
		Filme filme = repository.getOne(id);
		
		if (filme == null) {
			return ResponseEntity.notFound().build();
		}
		repository.delete(filme);
		return ResponseEntity.noContent().build();
		
	}

}
