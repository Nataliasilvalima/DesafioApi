package com.gft.controllers;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gft.entities.Perfil;
import com.gft.services.PerfilService;

@RestController
@RequestMapping("v1/perfil")
public class PerfilController {

	final private PerfilService perfilService;

	public PerfilController(PerfilService perfilService) {
		this.perfilService = perfilService;
	}

	@JsonIgnore
	@GetMapping
	public ResponseEntity<Page<Perfil>> buscarTodasEtiquetas(@PageableDefault Pageable pageable) {
		return ResponseEntity.ok(perfilService.listarTodosPerfis(pageable));
	}

	@PostMapping
	public ResponseEntity<Object> salvarPerfil(@RequestBody @Valid Perfil dto) {
		if(perfilService.existsByPerfil(dto.getNome())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: Perfil já existe!");
		} 
		return ResponseEntity.status(HttpStatus.CREATED).body(perfilService.salvarPerfil(dto));
	}

	@GetMapping("{id}")
	public ResponseEntity<Object> buscarPerfil(@PathVariable @Valid Long id) {
		if(! perfilService.existsByPerfil(id)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Perfil não cadastrado!");
		} 
		Perfil perfil = perfilService.buscarPerfil(id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(perfil);

	}

	@PutMapping("{id}")
	public ResponseEntity<Object> alterarPerfil(@RequestBody Perfil dto, @PathVariable @Valid Long id) {
		if(! perfilService.existsByPerfil(id)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Perfil não cadastrado!");
		} 
		Perfil perfil = perfilService.atualizarPerfil(dto, id);

		return ResponseEntity.status(HttpStatus.ACCEPTED).body(perfil);

	}

	@DeleteMapping("{id}")
	public ResponseEntity<Object> excluirPerfil(@PathVariable  @Valid Long id) {
		if(! perfilService.existsByPerfil(id)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Perfil não cadastrado!");
		} 
		perfilService.excluirPerfil(id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}
}