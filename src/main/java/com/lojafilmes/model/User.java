package com.lojafilmes.model;

import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
@Entity
@Table(name = "user")
public class User {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private int id;
	
    @Column(name = "email")
    @Email(message = "*Por favor insira um e-mail válido!")
    @NotEmpty(message = "*Por favor insira seu e-mail!")
    private String email;
    
    @Column(name = "password")
    @Length(min = 3, message = "*Sua senha deve conter pelo menos 3 caracteres")
    @NotEmpty(message = "*Por favor, insira sua senha")
    private String password;
    
    @Column(name = "nome")
    @NotEmpty(message = "*Por favor insira seu nome")
    private String nome;
    
    @Column(name = "sobrenome")
    @NotEmpty(message = "*Por favor insira seu sobrenome")
    private String sobrenome;
    
    @Column(name = "active")
    private int active;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
    
}
