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
import com.skynet.placarSo.model.bean.Jogador;
import com.skynet.placarSo.model.service.JogadorService;

@RestController
@RequestMapping("/api")
public class JogadorResource {

	@Autowired
	private JogadorService jogadorServ;

	@Autowired
	private REController responseEntityController;

	@Autowired
	private ExceptionController exceptionController;

	@PostMapping("/login/")
	public ResponseEntity<?> fazerLogin(@RequestBody Jogador jogador,
			UriComponentsBuilder ucBuilder) {
			Jogador j = jogadorServ.validarLogin(jogador);
		if (j != null) {
			System.out.println(jogador.getId());
			return responseEntityController.responseController(j, HttpStatus.OK);
		} else {
			return exceptionController.errorHandling("Erro no Login", HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping(value = "/jogador/")
	public ResponseEntity<?> cadastrarJogador(@RequestBody Jogador jogador, UriComponentsBuilder ucBuilder) {

		boolean statusCadastro = jogadorServ.cadastrarJogador(jogador);
		if (statusCadastro) {
			ObjectMapper mapper = new ObjectMapper();
			ObjectNode jogadorObj = mapper.createObjectNode();

			jogadorObj.put("id", jogador.getId());
			jogadorObj.put("iniciada", jogador.getUsuario());

			return responseEntityController.responseController(jogadorObj, HttpStatus.OK);
		} else {
			return exceptionController.errorHandling("Erro ao cadastrar jogador", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/jogador/{id}")
	public ResponseEntity<?> iniciarPartida(@PathVariable("id") long id) {
		Jogador jogador = jogadorServ.buscarPorId(id);
		if(jogador != null) {
			return responseEntityController.responseController(jogador, HttpStatus.OK);
		}else {
			return exceptionController.errorHandling("Jogador Nao Existente", HttpStatus.NOT_FOUND);
		}
		
	}

}
