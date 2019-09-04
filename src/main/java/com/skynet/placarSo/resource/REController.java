package com.skynet.placarSo.resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class REController {

	public ResponseEntity<?> responseController(Object obj, HttpStatus statusCode) {
		return ResponseEntity.status(statusCode).body(obj);
	}
	
}
