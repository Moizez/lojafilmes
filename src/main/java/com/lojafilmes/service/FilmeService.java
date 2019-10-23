package com.lojafilmes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lojafilmes.model.Filme;
import com.lojafilmes.repository.FilmeRepository;


@Service
public class FilmeService {
	
	@Autowired
	private FilmeRepository repository;

	public Filme save(Filme filme) {
		return repository.saveAndFlush(filme);
	}

	public List<Filme> findAll() {
		return repository.findAll();
	}
	
	public Filme findOne(Long id) {
		return repository.getOne(id);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	/*public List<Filme> findByPedido(Long id) {
		return repository.findByPedido(id);
	}*/

}
