package com.lojafilmes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lojafilmes.model.Diretor;
import com.lojafilmes.repository.DiretorRepository;

@Service
public class DiretorService {
	
	@Autowired
	private DiretorRepository repository;
	
	public Diretor save(Diretor diretor) {
        return repository.saveAndFlush(diretor);
    }

	public List<Diretor> findAll(){
		return repository.findAll();
	}	
	
	public Diretor findOne(Long id) {
        return repository.getOne(id);
    }
     
    public void delete(Long id) {
        repository.deleteById(id);
    }

}