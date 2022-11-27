package com.gft.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gft.entities.Etiqueta;
import com.gft.excepctions.ErroException;
import com.gft.repositories.EtiquetaRepository;

@Service
public class EtiquetaService {

	private final EtiquetaRepository etiquetaRepository;

	public EtiquetaService(EtiquetaRepository etiquetaRepository) {
		this.etiquetaRepository = etiquetaRepository;
	}
	
	public Etiqueta salvarEtiqueta(Etiqueta etiqueta) {
			return etiquetaRepository.save(etiqueta);
		}
	
	public Page<Etiqueta> listarTodasEtiquetas(Pageable pageable){
		
		return etiquetaRepository.findAll(pageable);
	}
	
	public Etiqueta buscarEtiqueta(Long id) throws Exception {
		Optional<Etiqueta> optional= etiquetaRepository.findById(id);
		
		return optional.orElseThrow(() -> new ErroException("Etiqueta não encontrada."));
	}
	
	public Etiqueta buscarEtiqueta(String etiqueta) throws Exception {
		Optional<Etiqueta> optional= etiquetaRepository.findByNome(etiqueta);
		
		return optional.orElseThrow(() -> new ErroException("Etiqueta não encontrada."));
	}
	public Etiqueta atualizarEtiqueta(Etiqueta etiqueta, Long id) throws Exception {
		
		Etiqueta etiquetaOriginal = this.buscarEtiqueta(id);
		
		etiqueta.setId(etiquetaOriginal.getId());
		
		return etiquetaRepository.save(etiqueta);
	}
	
	public void excluirEtiqueta(Long id) throws Exception {
		
		Etiqueta etiquetaOriginal = this.buscarEtiqueta(id); 
		etiquetaRepository.delete(etiquetaOriginal);
		
	}
	
	public boolean existsByName(String nome) {
		
		return etiquetaRepository.existsByNome(nome);
	}
	
	public boolean existsById(Long id) {
		
		return etiquetaRepository.existsById(id);
	}
}