package com.lojafilmes.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

@Entity
public class Filme implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private Long id;
	
	@Column(nullable = false, length = 100)
	@NotBlank(message = "Titulo é uma informação obrigatória.")
	private String titulo;
	
	@Column(nullable = false)
	@NotBlank(message = "Sinopse é uma informação obrigatória.")
	private String sinopse;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date ano;
	
	@Column
	private int asin;
	
	@NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	@Column(nullable = false, columnDefinition = "DECIMAL(7,2) DEFAULT 0.00")
	private double preco;
	
	@ManyToOne
	public Produtora produtora;
	
	@ManyToOne
	public Genero genero;
	
	@ManyToOne
	@JoinTable(name="filme_diretor")
	public Diretor diretor;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getSinopse() {
		return sinopse;
	}

	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}

	public Date getAno() {
		return ano;
	}

	public void setAno(Date ano) {
		this.ano = ano;
	}

	public int getAsin() {
		return asin;
	}

	public void setAsin(int asin) {
		this.asin = asin;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public Produtora getProdutora() {
		return produtora;
	}

	public void setProdutora(Produtora produtora) {
		this.produtora = produtora;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public Diretor getDiretor() {
		return diretor;
	}

	public void setDiretor(Diretor diretor) {
		this.diretor = diretor;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
