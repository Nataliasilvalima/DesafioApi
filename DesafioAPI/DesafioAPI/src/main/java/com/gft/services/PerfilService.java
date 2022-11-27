package com.gft.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gft.entities.Perfil;
import com.gft.excepctions.ErroException;
import com.gft.repositories.PerfilRepository;

@Service
public class PerfilService {
	
	final PerfilRepository perfilRepository;

	public PerfilService(PerfilRepository perfilRepository) {
		this.perfilRepository = perfilRepository;
	}
	
	public Perfil salvarPerfil(Perfil perfil) {
		return perfilRepository.save(perfil);
	}
	
	public Perfil buscarPerfil(Long id) {
		Optional<Perfil> optional= perfilRepository.findById(id);
		
		return optional.orElseThrow(() -> new ErroException("Perfil não encontrado."));
	}
	
	public Perfil atualizarPerfil(Perfil perfil, Long id) {
		
		Perfil perfilOriginal = this.buscarPerfil(id);
		
		perfil.setId(perfilOriginal.getId());
		
		return perfilRepository.save(perfil);
	}
	
	public Page<Perfil> listarTodosPerfis(Pageable pageable){
		
		return perfilRepository.findAll(pageable);
	}
		
	public void excluirPerfil(Long id) {
		
		Perfil perfilOriginal = this.buscarPerfil(id); 
		perfilRepository.delete(perfilOriginal);
		
	}
	
	public boolean existsByPerfil(String nome) {
		
		return perfilRepository.existsByNome(nome);
		
	}
	
	public boolean existsByPerfil(Long id) {
		
		return perfilRepository.existsById(id);
	}
}