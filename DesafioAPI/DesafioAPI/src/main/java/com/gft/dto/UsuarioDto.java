package com.gft.dto;

import java.util.ArrayList;
import java.util.List;

import com.gft.entities.Perfil;

public class UsuarioDto {
	
	private Long id;
	private String username;
	private String email;
	private String password;
	private List<Perfil> perfil = new ArrayList<>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<Perfil> getPerfil() {
		return perfil;
	}
	public void setPerfil(List<Perfil> perfil) {
		this.perfil = perfil;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	
	public UsuarioDto(Long id, String username, String email, String password, List<Perfil> perfil) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.perfil = perfil;
	}
	public UsuarioDto() {

	}

}
