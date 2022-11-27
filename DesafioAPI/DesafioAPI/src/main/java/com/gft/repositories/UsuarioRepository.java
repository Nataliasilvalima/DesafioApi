package com.gft.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gft.entities.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	Optional<Usuario>findByEmail(String email);
	
	Optional<Usuario>findByUsername(String username);
	
	boolean existsByUsername(String username);
	
	boolean existsById(Long id);
}
