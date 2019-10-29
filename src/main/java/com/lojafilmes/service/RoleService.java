package com.lojafilmes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lojafilmes.model.Role;
import com.lojafilmes.repository.RoleRepository;

@Service
public class RoleService {
	@Autowired
	private RoleRepository repository;

	public void saveRole(Role role) {
		repository.saveAndFlush(role);
	}
	
	public Role findByRole(String name) {
		return repository.findByRole(name);
	}
}
