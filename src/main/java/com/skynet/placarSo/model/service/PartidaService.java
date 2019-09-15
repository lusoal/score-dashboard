package com.skynet.placarSo.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skynet.placarSo.model.bean.Jogador;
import com.skynet.placarSo.model.bean.Partidas;
import com.skynet.placarSo.model.repository.PartidasRepository;

@Service
public class PartidaService {

	@Autowired
	private PartidasRepository partidaRepo;

	@Autowired
	private SessaoService sessaoService;

	@Autowired
	private JogadorService jogadorService;

	public List<Partidas> getAllPartidas() {
		return partidaRepo.findAll();
	}
	
	public boolean cadastrarIniciarPartidas(Partidas partidas) throws Exception {
		try {
			List<Partidas> listPartidas = partidaRepo.findAll();
			if (listPartidas.isEmpty()) {
				partidas.setId(1);
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

	public boolean finalizarPartida(Long partidaId) {
		System.out.println(partidaId);
		List<Jogador> jogadores = jogadorService.getJogadoresAtivos();

		for (Jogador j : jogadores) {
			j.setSessao(null);
			j.setSessaoStatus(false);
			jogadorService.updateJogador(j);
			System.out.println("ATUALIZADO USUARIO: " + j.getUsuario());
		}
		
		if (sessaoService.droparTodasSessoes()) {
			Partidas p = partidaRepo.findById(partidaId).orElse(null);
			if (p != null) {
				p.setTema(null);
				partidaRepo.save(p);
				partidaRepo.deletePartida(partidaId);
				return true;
			}
		}
		return false;
	}
	
}
