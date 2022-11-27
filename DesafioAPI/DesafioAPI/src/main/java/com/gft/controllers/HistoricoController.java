package com.gft.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gft.entities.Historico;
import com.gft.services.HistoricoService;

@RestController
@RequestMapping("v1/historico")
public class HistoricoController {

	private final HistoricoService historicoService;

	public HistoricoController(HistoricoService historicoService) {
	
		this.historicoService = historicoService;
	}
	
	@GetMapping
	public ResponseEntity<Page<Historico>> buscarParametros(@PageableDefault Pageable pageable){
		
		return ResponseEntity.ok(historicoService.listarTodosParametros(pageable));
	}
	
	@GetMapping("/{username}")
	public ResponseEntity<List<Historico>> buscarParametrosUsuario(@PathVariable(value = "username") String username){
	
		return ResponseEntity.ok(historicoService.listarParametroUsuario(username));
	}
	
	@GetMapping(("/etiqueta/{etiqueta}"))
	public ResponseEntity<List<Historico>> buscarEtiquetaMaisAcessadas(@PathVariable(value = "etiqueta") @Valid String etiqueta){
		
		return ResponseEntity.ok(historicoService.listarEtiquetasMaisAcessadas(etiqueta));
	}
}