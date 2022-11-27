package com.gft.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_perfis")
public class Perfil implements GrantedAuthority, Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome",nullable = false, unique = true)
	private String nome;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "perfil", fetch = FetchType.EAGER )
	private List<Usuario> usuarios = new ArrayList<>();
	
	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

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

	@Override
	public String getAuthority() {
	
		return this.getNome();
	}

	public Perfil() {
		super();
	}

	public Perfil(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return super.toString();
	}

}
