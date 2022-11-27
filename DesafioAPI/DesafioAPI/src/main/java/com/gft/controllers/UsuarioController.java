package com.gft.controllers;



import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gft.dto.UsuarioDto;
import com.gft.entities.Usuario;
import com.gft.services.UserDetailsServiceImplement;


@RestController
@RequestMapping("v1/usuario")
public class UsuarioController {

	@Lazy
	private final UserDetailsServiceImplement usuarioService;
	private final PasswordEncoder encoder;
	
	public UsuarioController(UserDetailsServiceImplement usuarioService, PasswordEncoder encoder  ) {
			this.usuarioService = usuarioService;
			this.encoder = encoder;
		}

	@PostMapping	
	public ResponseEntity<Object> salvarUsuario(@RequestBody @Valid UsuarioDto usuario){
		 if(usuarioService.existsByUsername(usuario.getUsername())) {
	            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: Usuario já existe!");
		 }
		Usuario usuario1 = new Usuario();
		BeanUtils.copyProperties(usuario, usuario1);
		usuario1.setPerfil(usuario.getPerfil());
		usuario1.setPassword(encoder.encode(usuario.getPassword()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.salvarUsuario(usuario1));
		}
	
	@GetMapping
	public ResponseEntity<Page<Usuario>> buscarTodosOsUsuarios(@PageableDefault Pageable pageable)
	{
		return ResponseEntity.ok(usuarioService.listarTodosUsuarios(pageable));
	}

	@GetMapping("{id}")
	public ResponseEntity<Object> buscarUsuario(@PathVariable @Valid Long id){
		if(! usuarioService.existsById(id)){
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Conflito: Usuario não cadastrado!");
		 }
		
		Usuario usuario = usuarioService.buscarUsuarioPorId(id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(usuario);
	
	}
	
	
	@PutMapping("{id}")
	public ResponseEntity<Object> alterarUsuario(@RequestBody  Usuario dto,
			@PathVariable @Valid Long id){
		if(! usuarioService.existsById(id)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario não cadastrado!");
	 }
		dto.setPassword(encoder.encode(dto.getPassword()));
		usuarioService.atualizarUsuario(dto, id);

		return ResponseEntity.status(HttpStatus.ACCEPTED).body(dto);
	
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Object> excluirUsuario(@PathVariable @Valid Long id){
		if(! usuarioService.existsById(id)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario não cadastrado!");
	 }
		usuarioService.excluirUsuario(id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}
}
