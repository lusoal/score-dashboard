package com.skynet.placarSo.resource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.skynet.placarSo.model.bean.Partidas;
import com.skynet.placarSo.model.service.PartidaService;

@RestController
@RequestMapping("/api")
public class PartidaResource {

	@Autowired
	private PartidaService partidaService;

	@Autowired
	private REController responseEntityController;

	@Autowired
	private ExceptionController exceptionController;

	@GetMapping("/partidas/")
	public ResponseEntity<?> buscarSessaoPorId() {
		List<Partidas> partidas = partidaService.getAllPartidas();
		for (Partidas p : partidas) {
			System.out.println(p.getTema().getNome());
		}
		if (partidas.isEmpty()) {
			return exceptionController.errorHandling("Nenhuma partida encontrada", HttpStatus.NOT_FOUND);
		}
		return responseEntityController.responseController(partidas, HttpStatus.OK);
	}

	@GetMapping("/partida/perguntas/{id}")
	public ResponseEntity<?> getPartidaForUser(@PathVariable("id") long id) {
		Partidas p = partidaService.encontrarPartida(id);
		List<Object> minhaLista = new ArrayList<>();
		minhaLista.add(p.getTema().getPerguntas());
		return responseEntityController.responseController(minhaLista, HttpStatus.OK);
	}

	@GetMapping("/partida/iniciada/")
	public ResponseEntity<?> getPartidaIniciada() {
		boolean statusPartida = true;
		try {
			partidaService.getPartidaIniciada();
		} catch (Exception e) {
			System.out.println(e);
			statusPartida = false;
		}
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode status = mapper.createObjectNode();
		status.put("message", statusPartida);
		return responseEntityController.responseController(status, HttpStatus.OK);
	}

}
