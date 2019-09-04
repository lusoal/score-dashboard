package com.skynet.placarSo.model.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skynet.placarSo.model.bean.Partidas;
import com.skynet.placarSo.model.repository.PartidasRepository;

@Service
public class PartidaService {
	
	@Autowired
	private PartidasRepository partidaRepo;
	
	public Partidas encontrarPartida(Long partidaId) {
		Optional<Partidas> partida = partidaRepo.findById(partidaId);
		if (partida.isPresent()) {
			return partida.get();
		}
		return null;
	}
	
	
	
}
