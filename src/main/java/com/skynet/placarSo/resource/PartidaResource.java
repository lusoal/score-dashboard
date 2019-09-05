package com.skynet.placarSo.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

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
		return responseEntityController.responseController(partidas, HttpStatus.OK);
	}

}
