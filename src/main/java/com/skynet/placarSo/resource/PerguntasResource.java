package com.skynet.placarSo.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skynet.placarSo.model.bean.Perguntas;
import com.skynet.placarSo.model.service.PerguntasService;

@RestController
@RequestMapping("/api")
public class PerguntasResource {
	
	@Autowired
	private PerguntasService perguntasService;

	@Autowired
	private REController responseEntityController;

	@Autowired
	private ExceptionController exceptionController;
	
	
	@GetMapping("/perguntas/{tema_id}")
	public ResponseEntity<?> buscarPerguntasTema(@PathVariable("tema_id") long id) {
		List<Perguntas> perguntas = perguntasService.getPerguntasByTemaId(id);
		return responseEntityController.responseController(perguntas, HttpStatus.OK);
	}
}
