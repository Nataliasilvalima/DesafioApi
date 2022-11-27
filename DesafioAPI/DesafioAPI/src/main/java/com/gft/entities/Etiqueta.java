package com.gft.entities;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_etiqueta")
public class Etiqueta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String nome;
	
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

	public Etiqueta(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public Etiqueta() {
	}

	@Override
	public int hashCode() {
		
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
	
		return super.equals(obj);
	}
	
}
