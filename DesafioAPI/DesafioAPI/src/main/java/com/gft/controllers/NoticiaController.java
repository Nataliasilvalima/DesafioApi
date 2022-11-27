package com.gft.controllers;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gft.entities.Historico;
import com.gft.entities.Usuario;
import com.gft.repositories.UsuarioRepository;
import com.gft.services.HistoricoService;
import com.gft.services.NoticiasService;
import com.gft.webClient.NoticiaApi;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api")
public class NoticiaController {

	private final NoticiasService noticiaWeb;
	private final UsuarioRepository usuarioRepository;
	private final HistoricoService historicoService;

	public NoticiaController(NoticiasService noticiaWeb, UsuarioRepository usuarioRepository , HistoricoService historicoService) {
		this.noticiaWeb = noticiaWeb;
		this.usuarioRepository = usuarioRepository;
		this.historicoService = historicoService;
	}
	

	@GetMapping("/")
	@ResponseStatus(HttpStatus.OK)
	public Flux<NoticiaApi>  getNoticiaTeste(@RequestParam String q, @RequestParam String date) throws Exception{
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    String username = ((UserDetails)principal).getUsername();
	    Optional<Usuario> usuario = usuarioRepository.findByUsername(username);
	    Historico historico = new Historico(usuario.get().getId(), username, q, date);
	    historicoService.salvarHistorico(historico);
	    
	    return noticiaWeb.findAndNoticiaLista(q, date);
	}
}
