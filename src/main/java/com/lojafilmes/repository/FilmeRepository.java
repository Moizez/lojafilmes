package com.lojafilmes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lojafilmes.model.Filme;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long>{
	
	@Query
	public List<Filme> findByTituloIgnoreCaseContaining(String nome);
	
}
