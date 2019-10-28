package com.lojafilmes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lojafilmes.model.Produtora;

@Repository
public interface ProdutoraRepository extends JpaRepository<Produtora, Long> { }
