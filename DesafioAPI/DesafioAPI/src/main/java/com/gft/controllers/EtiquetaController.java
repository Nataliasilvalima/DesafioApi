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

import com.gft.entities.Etiqueta;
import com.gft.services.EtiquetaService;

@RestController
@RequestMapping("v1/etiqueta")
public class EtiquetaController {
	private final EtiquetaService etiquetaService;

	public EtiquetaController(EtiquetaService etiquetaService) {

		this.etiquetaService = etiquetaService;
	}

	@GetMapping
	public ResponseEntity<Page<Etiqueta>> buscarTodasEtiquetas(@PageableDefault Pageable pageable) {
		return ResponseEntity.ok(etiquetaService.listarTodasEtiquetas(pageable));
	}

	@PostMapping
	public ResponseEntity<Object> cadastrar(@RequestBody @Valid Etiqueta etiqueta) {
		if (etiquetaService.existsByName(etiqueta.getNome()))
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: Está etiqueta já existe!");
		return ResponseEntity.status(HttpStatus.CREATED).body(etiquetaService.salvarEtiqueta(etiqueta));
	}

	@GetMapping("{id}")
	public ResponseEntity<Object> buscarEtiqueta(@PathVariable @Valid Long id) throws Exception {
		if (!etiquetaService.existsById(id)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Etiqueta não cadastrada!");
		}

		Etiqueta etiqueta = etiquetaService.buscarEtiqueta(id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(etiqueta);

	}

	@PutMapping("{id}")
	public ResponseEntity<Object> alterarEtiqueta(@RequestBody Etiqueta dto, @PathVariable @Valid Long id) throws Exception {
		if (!etiquetaService.existsById(id)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Etiqueta não cadastrada!");
		}
		Etiqueta etiqueta = etiquetaService.atualizarEtiqueta(dto, id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(etiqueta);

	}

	@DeleteMapping("{id}")
	public ResponseEntity<Object> excluirEtiqueta(@PathVariable @Valid Long id) throws Exception {
		if (!etiquetaService.existsById(id)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Etiqueta não cadastrada!");
		}
		etiquetaService.excluirEtiqueta(id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}

}
