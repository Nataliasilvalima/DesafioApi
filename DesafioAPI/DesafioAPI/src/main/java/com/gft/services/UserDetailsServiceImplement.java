package com.gft.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gft.entities.Perfil;
import com.gft.entities.Usuario;
import com.gft.repositories.PerfilRepository;
import com.gft.repositories.UsuarioRepository;

@Service
public class UserDetailsServiceImplement implements UserDetailsService{

	final UsuarioRepository usuarioRepository;
	

	
	@Lazy
	@Autowired
	private final PerfilRepository perfilRepository;
		
	public UserDetailsServiceImplement(UsuarioRepository usuarioRepository ) {
		
		this.usuarioRepository = usuarioRepository;
		
		this.perfilRepository = null;
	}
	
	public Usuario buscarUsuarioPorEmail(String email) {
		Optional<Usuario> optional = usuarioRepository.findByEmail(email);
		
		if(optional.isEmpty()) {
			throw new UsernameNotFoundException("Usuario não encontrado.");
		}
		
		return optional.get();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByUsername(username)
				.orElseThrow(()-> new UsernameNotFoundException("Usuario não encontrado com esse username: " + username));
		return new User(usuario.getUsername(), usuario.getPassword(), true, true, true, true, usuario.getAuthorities());
		
	} 
	
	public Usuario buscarUsuarioPorId(Long idUsuario) {
		
		 Optional<Usuario> optional = usuarioRepository.findById(idUsuario);
		 
		 if(optional.isEmpty()) {
			 throw new RuntimeException("Usuario não cadastrado.");
		 }
		 
		 return optional.get();
	}
	
	@Transactional
	public Usuario salvarUsuario(Usuario dto) {
		
		Usuario usuario = new Usuario();

		usuario.setEmail(dto.getEmail());
		usuario.setUsername(dto.getUsername());
		usuario.setPassword(dto.getPassword());
		
		List<Perfil> list = dto.getPerfil();
		for (Perfil perfil : list) {
			Perfil perfil2 = perfilRepository.findById(perfil.getId()).get();
			usuario.adicionarPerfil(perfil2);
			
		}
		
		return usuarioRepository.save(usuario);
	}
	
	public Usuario atualizarUsuario(Usuario usuario, Long id) {
		
		Usuario usuarioOriginal = this.buscarUsuarioPorId(id);
		
		usuario.setId(usuarioOriginal.getId());
		
		return usuarioRepository.save(usuario);
	}
	
	@Transactional
	public void excluirUsuario(Long id) {
		
		Usuario usuarioOriginal = this.buscarUsuarioPorId(id); 
		usuarioRepository.delete(usuarioOriginal);
		
}

	public Page<Usuario> listarTodosUsuarios(Pageable pageable) {
		
		return usuarioRepository.findAll(pageable);
	}

	public boolean existsByUsername(String username) {
		
		return usuarioRepository.existsByUsername(username);
	}
	
	public boolean existsById (Long id) {
		
		return usuarioRepository.existsById(id);
	}
}
