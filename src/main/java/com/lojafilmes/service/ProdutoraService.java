package com.lojafilmes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lojafilmes.model.Produtora;
import com.lojafilmes.repository.ProdutoraRepository;

@Service
public class ProdutoraService {
	
	@Autowired
	private ProdutoraRepository repository;
	
	public void save(Produtora produtora) {
        repository.saveAndFlush(produtora);
    }

	public List<Produtora> listaAll(){
		return repository.findAll();
	}
	
	public Produtora findOne(Long id) {
        return repository.getOne(id);
    }
     
    public void delete(Long id) {
        repository.deleteById(id);
    }

}
