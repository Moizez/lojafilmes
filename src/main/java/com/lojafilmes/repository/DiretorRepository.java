package com.lojafilmes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lojafilmes.model.Diretor;

@Repository
public interface DiretorRepository extends JpaRepository<Diretor, Long>{ }
