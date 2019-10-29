package com.lojafilmes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lojafilmes.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
	@Query
    public Role findByRole(String role);

}
