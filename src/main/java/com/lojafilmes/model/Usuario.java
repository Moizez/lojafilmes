package com.lojafilmes.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Usuario implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private Long id;
	
	@Column(nullable=false, length = 100)
	@NotBlank(message = "Nome é uma informação obrigatória")
	private String nome;
	
	@Column//(nullable=false, length = 100)
	@NotBlank(message = "E-mail é uma informação obrigatória")
	private String email;
	
	@Column//(nullable=false, length = 100)
	@NotBlank(message = "Senha é uma informação obrigatória")
	private String senha;
	
	@Column(nullable=false, length = 100)
	@NotBlank(message = "Rua é uma informação obrigatória")
	private String rua;
	
	@Column(nullable=false, length = 100)
	@NotBlank(message = "Bairro é uma informação obrigatória")
	private String bairro;
	
	@Column(nullable=false, length = 100)
	@NotBlank(message = "Cidade é uma informação obrigatória")
	private String cidade;
	
	@Column(nullable=false, length = 100)
	@NotBlank(message = "o CEP é uma informação obrigatória")
	private String cep;
	
	@Column(nullable=false, length = 100)
	@NotBlank(message = "Número da casa é uma informação obrigatória")
	private String numero;
	
	@Column(nullable=false, length = 100)
	@NotBlank(message = "UF é uma informação obrigatória")
	private String uf;
	
	@Lob
	private byte[] imagem;
	
	//@Temporal(TemporalType.TIMESTAMP)
	//private Date dataCriacao;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	
	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}
	
	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}	

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}
	
	/*public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	*/
	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
