package com.lojafilmes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lojafilmes.model.Genero;

@Repository
public interface GeneroRepository extends JpaRepository<Genero, Long>{
	
	@Query
	public List<Genero> findByNomeIgnoreCaseContaining(String nome);
	
}
