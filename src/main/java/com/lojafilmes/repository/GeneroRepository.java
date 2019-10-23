package com.lojafilmes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lojafilmes.model.Genero;

@Repository
public interface GeneroRepository extends JpaRepository<Genero, Long>{ }
