package com.gft.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gft.entities.Historico;

@Repository
public interface HistoricoRepository extends JpaRepository<Historico, Long> {

	Page<Historico> findAll(Pageable pageable);
	
	List<Historico> findAllByUsername(String username);

	List<Historico> findAllByEtiqueta(String etiqueta);
	
}