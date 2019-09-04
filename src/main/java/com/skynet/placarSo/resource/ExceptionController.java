package com.skynet.placarSo.resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
public class ExceptionController {

	private static final String ERROR_MESSAGE = "Ops! ocorreu um erro";

	public ResponseEntity<Object> errorHandling(@Nullable String message, HttpStatus status) {
		if (message == null) {
			message = ERROR_MESSAGE;
		}
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode jsonObject = mapper.createObjectNode();
		jsonObject.put("message", message);
		return ResponseEntity.status(status).body(jsonObject);
	}

	public ResponseEntity<Object> errorHandlingAdmin(@Nullable Boolean message, HttpStatus status) {
		if (message == null) {
			message = false;
			;
		}
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode jsonObject = mapper.createObjectNode();
		jsonObject.put("admin", message);
		return ResponseEntity.status(status).body(jsonObject);
	}

	@GetMapping("/naologado/")
	public ResponseEntity<Object> erroHandlingLogin() {
		System.out.println("Teste");
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode jsonObject = mapper.createObjectNode();
		jsonObject.put("message", "Nao autenticado");

		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(jsonObject);

	}
}
