package com.lojafilmes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lojafilmes.model.Genero;
import com.lojafilmes.repository.GeneroRepository;

@Service
public class GeneroService {
	
	@Autowired
	private GeneroRepository repository;

	public void save(Genero genero) {
        repository.saveAndFlush(genero);
    }
	
	public List<Genero> listaAll(){
		return repository.findAll();
	}
	
	public Genero findOne(Long id) {
        return repository.getOne(id);
    }
     
    public void delete(Long id) {
        repository.deleteById(id);
    }


}
