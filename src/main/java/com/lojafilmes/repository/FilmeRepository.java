package com.lojafilmes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lojafilmes.model.Filme;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long>{

}
