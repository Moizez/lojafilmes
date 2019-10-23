package com.lojafilmes.api;

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

import com.lojafilmes.model.Produtora;
import com.lojafilmes.service.ProdutoraService;

@RestController
@RequestMapping("/api")
public class ProdutoraResource {
	
	@Autowired
	private ProdutoraService service;
	
	
	@PostMapping("/produtora")
	public Produtora add(@RequestBody Produtora produtora) {
		return service.save(produtora);
	}
	
	@GetMapping("/produtora")
	public List<Produtora> listar(){
		return service.findAll();
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Produtora> buscar(@PathVariable Long id){
		Produtora produtora = service.findOne(id);
		
		if (produtora == null) {
			return ResponseEntity.notFound().build();
			
		}
		return ResponseEntity.ok(produtora);
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Produtora> update(@PathVariable Long id,
			@Valid @RequestBody Produtora produtora){
		Produtora prod = service.findOne(id);
		
		if (prod == null) {
			return ResponseEntity.notFound().build();
		}
		
		BeanUtils.copyProperties(produtora, prod, "id");
		
		prod = service.save(prod);
		
		return ResponseEntity.ok(prod);
		
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		service.delete(id);
	}
	
	/*@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id){
		Produtora produtora = repository.getOne(id);
		
		if (produtora == null) {
			return ResponseEntity.notFound().build();
		}
		repository.delete(produtora);
		return ResponseEntity.noContent().build();
		
	}*/

}
