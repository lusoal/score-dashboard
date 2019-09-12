package com.skynet.placarSo.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
		for (Partidas p: partidas) {
			System.out.println(p.getTema().getNome());
		}
		if(partidas.isEmpty()) {
			return exceptionController.errorHandling("Nenhuma partida encontrada", HttpStatus.NOT_FOUND);
		}
		return responseEntityController.responseController(partidas, HttpStatus.OK);
	}
	
	

}
