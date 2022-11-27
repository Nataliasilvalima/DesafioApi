package com.gft.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gft.entities.Historico;
import com.gft.repositories.HistoricoRepository;

@Service
public class HistoricoService {

	private final HistoricoRepository historicoRepository;
	
	public HistoricoService(HistoricoRepository historicoRepository) {
		this.historicoRepository = historicoRepository;
	}


	public Historico salvarHistorico(Historico historico) {
		return historicoRepository.save(historico);
	}
	
	public Page<Historico> listarTodosParametros(Pageable pageable){
		return historicoRepository.findAll(pageable);
	}
	
	public List<Historico> listarParametroUsuario (String username ){
		return historicoRepository.findAllByUsername(username);
	}
	
	public List<Historico> listarEtiquetasMaisAcessadas(String etiqueta){
		
		return historicoRepository.findAllByEtiqueta(etiqueta);
	}
	
}