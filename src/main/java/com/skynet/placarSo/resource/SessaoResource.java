package com.skynet.placarSo.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.skynet.placarSo.model.bean.Sessao;
import com.skynet.placarSo.model.service.SessaoService;

@RestController
@RequestMapping("/api")
public class SessaoResource {

	@Autowired
	private SessaoService sessionService;

	@Autowired
	private REController responseEntityController;

	@Autowired
	private ExceptionController exceptionController;

	@PostMapping("/sessao/")
	public ResponseEntity<?> iniciarSessao(@RequestBody ObjectNode json, UriComponentsBuilder ucBuilder) {
		Long partidaId;
		Long jogadorId;
		try {
			partidaId = json.get("partida_id").asLong();
			jogadorId = json.get("jogador_id").asLong();
		
		} catch (Exception e) {
			return exceptionController.errorHandling("Parametros Incorretos", HttpStatus.BAD_REQUEST);
		}
		try {
			ObjectMapper mapper = new ObjectMapper();
			ObjectNode sessaoStatus = mapper.createObjectNode();

			sessaoStatus.put("status", true);

			sessionService.iniciarSessao(partidaId, jogadorId);
			return responseEntityController.responseController(sessaoStatus, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return exceptionController.errorHandling(e.toString(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/sessao/{id}")
	public ResponseEntity<?> buscarSessaoPorId(@PathVariable("id") long id) {
		Sessao sessao = sessionService.buscarSessao(id);
		
		if (sessao != null) {
			return responseEntityController.responseController(sessao, HttpStatus.OK);
		}else {
			return exceptionController.errorHandling("Nao existe essa sessao", HttpStatus.NOT_FOUND);
		}
		
	}
	
	

}
