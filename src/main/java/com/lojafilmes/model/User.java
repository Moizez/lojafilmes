package com.lojafilmes.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "user")
public class User implements UserDetails, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
    @LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany
	@JsonIgnore
	private Set<Role> roles = new HashSet<Role>();
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
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		authorities.addAll(getRoles());
		return authorities;
	}
	@Override
	public String getUsername() {
		return email;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}
    
}
