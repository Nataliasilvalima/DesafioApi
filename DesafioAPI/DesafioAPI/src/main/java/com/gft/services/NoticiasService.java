package com.gft.services;

import static org.springframework.http.MediaType.APPLICATION_JSON;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.gft.entities.Etiqueta;
import com.gft.webClient.NoticiaApi;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class NoticiasService {

	private final WebClient webClient;

	@Autowired
	private EtiquetaService etiquetaService;

	public NoticiasService(WebClient.Builder builder) {
		webClient = builder.baseUrl("https://apinoticias.tedk.com.br/api").build();
	}

	@SuppressWarnings("unlikely-arg-type")
	public Flux<NoticiaApi> findAndNoticiaLista(@Valid String q, String date) throws Exception {
		
		Etiqueta nova = etiquetaService.buscarEtiqueta(q);

		if (!nova.equals(q)) {
			return webClient.get().uri("/?q=" + q + "&date=" + date).accept(APPLICATION_JSON).retrieve()
					.onStatus(HttpStatus::is4xxClientError,
							serror -> Mono.error(new RuntimeException("verifique os parâmetros informados")))
					.bodyToFlux(NoticiaApi.class);
		}
		return null;

	}
}