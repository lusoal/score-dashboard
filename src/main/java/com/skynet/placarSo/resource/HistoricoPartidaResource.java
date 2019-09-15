package com.skynet.placarSo.resource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.skynet.placarSo.model.service.HistoricoPartidaService;

@RestController
@RequestMapping("/api")
public class HistoricoPartidaResource {

	@Autowired
	private HistoricoPartidaService historicoServ;

	@Autowired
	private REController responseEntityController;

	@Autowired
	private ExceptionController exceptionController;

	@GetMapping("/historico/pontuacao/{id}")
	public ResponseEntity<?> getPartidaForUser(@PathVariable("id") long id) {
		Long pontuacao = historicoServ.getPontuacaoJogador(id);

		ObjectMapper mapper = new ObjectMapper();
		ObjectNode pontuacaoObj = mapper.createObjectNode();

		pontuacaoObj.put("pontuacao", pontuacao);

		return responseEntityController.responseController(pontuacaoObj, HttpStatus.OK);
	}

}
