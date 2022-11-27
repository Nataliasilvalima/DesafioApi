package com.gft.excepctions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.gft.dto.ApiErrorDTO;

@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ ErroException.class })
	public ResponseEntity<ApiErrorDTO> handlerLojaException(ErroException ex, WebRequest request) {

		String error = "Erro no sistema";
		ApiErrorDTO apiError = new ApiErrorDTO(ex.getMessage(), error, HttpStatus.INTERNAL_SERVER_ERROR);

		return new ResponseEntity<ApiErrorDTO>(apiError, new HttpHeaders(), apiError.getStatus());
	}
}
