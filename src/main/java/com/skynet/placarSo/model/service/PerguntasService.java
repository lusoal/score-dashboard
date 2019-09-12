package com.skynet.placarSo.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skynet.placarSo.model.bean.Perguntas;
import com.skynet.placarSo.model.repository.PerguntasRepository;

@Service
public class PerguntasService {
	
	@Autowired
	private PerguntasRepository perguntasRepo;
	
	public List<Perguntas> getPerguntasByTemaId(Long temaId) {
		List<Perguntas> perguntas = perguntasRepo.findAllByTemaId(temaId);
		return perguntas;
	}
}
