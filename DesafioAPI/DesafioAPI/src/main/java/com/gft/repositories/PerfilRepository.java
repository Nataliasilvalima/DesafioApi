package com.gft.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gft.entities.Perfil;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long> {

	Optional<Perfil> findById(Long id);
	
	boolean existsByNome(String nome);
	
	boolean existsById(Long id);

	Page<Perfil> findAll(Pageable pageable);
}
