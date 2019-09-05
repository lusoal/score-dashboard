package com.skynet.placarSo.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skynet.placarSo.model.bean.Partidas;
import com.skynet.placarSo.model.repository.PartidasRepository;

@Service
public class PartidaService {

	@Autowired
	private PartidasRepository partidaRepo;

	public List<Partidas> getAllPartidas() {
		return partidaRepo.findAll();
	}

	public boolean cadastrarIniciarPartidas(Partidas partidas) throws Exception {
		try {
			List<Partidas> listPartidas = partidaRepo.findAll();
			// Cadastrar/iniciar partida apenas se tiver vazio
			if (listPartidas.isEmpty()) {
				partidas.setStatus(true);
				partidaRepo.save(partidas);
				return true;
			}
			throw new Exception("Partida ja existente");
		} catch (Exception e) {
			System.out.println(e);
			throw e;
		}

	}

	public Partidas getPartidaIniciada() throws Exception {
		Partidas partida = partidaRepo.findPartidaAndamento();
		if (partida != null) {
			return partida;
		} else {
			throw new Exception("Sem partidas em andamento");
		}

	}

	public Partidas encontrarPartida(Long partidaId) {
		Optional<Partidas> partida = partidaRepo.findById(partidaId);
		if (partida.isPresent()) {
			return partida.get();
		}
		return null;
	}

}
