package com.lojafilmes.model;

import java.io.Serializable;

import javax.persistence.*;

import org.springframework.security.core.GrantedAuthority;

import lombok.Data;

@Data
@Entity
@Table(name = "role")
public class Role implements GrantedAuthority, Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private int id;
    
    @Column(name = "role")
    private String role;

	@Override
	public String getAuthority() {
		return role;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
}
