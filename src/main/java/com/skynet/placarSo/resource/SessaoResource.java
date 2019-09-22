package com.skynet.placarSo.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.skynet.placarSo.model.bean.Sessao;
import com.skynet.placarSo.model.bean.Ganhador;
import com.skynet.placarSo.model.bean.Partidas;
import com.skynet.placarSo.model.service.PartidaService;
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

	@Autowired
	private PartidaService partidaServ;

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

			if (partidaServ.encontrarPartida(partidaId) == null) {
				return exceptionController.errorHandling("Partida nao iniciada", HttpStatus.FORBIDDEN);
			}
			Sessao s = sessionService.iniciarSessao(partidaId, jogadorId);
			sessaoStatus.put("sessao_id", s.getId());
			return responseEntityController.responseController(sessaoStatus, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return exceptionController.errorHandling("" + e, HttpStatus.FORBIDDEN);

		}
	}

	@GetMapping("/sessao/{id}")
	public ResponseEntity<?> buscarSessaoPorId(@PathVariable("id") long id) {
		Sessao sessao = sessionService.buscarSessao(id);

		if (sessao != null) {
			return responseEntityController.responseController(sessao, HttpStatus.OK);
		} else {
			return exceptionController.errorHandling("Nao existe essa sessao", HttpStatus.NOT_FOUND);
		}

	}

	@PostMapping("/sessao/pontuar/{id}")
	public ResponseEntity<?> pontuarSessao(@PathVariable("id") long id) {
		try {
			Sessao s = sessionService.pontuarSessao(id);
			return responseEntityController.responseController(s, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionController.errorHandling("ERROR: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/sessao/winners/{id}")
	public ResponseEntity<?> getWinnersOfPartida(@PathVariable("id") long partidaId) {
		Partidas p = partidaServ.encontrarPartida(partidaId);
		List<Ganhador> ganhadores = sessionService.getWinners(p);
		if (ganhadores.isEmpty()) {
			return exceptionController.errorHandling("Nao existem ganhadoresna partida", HttpStatus.NOT_FOUND);
		}

		return responseEntityController.responseController(ganhadores, HttpStatus.OK);
	}

}
