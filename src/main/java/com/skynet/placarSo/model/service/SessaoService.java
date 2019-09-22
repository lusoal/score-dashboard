package com.skynet.placarSo.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import com.skynet.placarSo.model.bean.Ganhador;
import com.skynet.placarSo.model.bean.Partidas;
import com.skynet.placarSo.model.bean.Sessao;
import com.skynet.placarSo.model.repository.SessaoRepository;

@Service
public class SessaoService {

	@Autowired
	private SessaoRepository sessaoRepo;
	@Autowired
	private PartidaService partidaService;
	@Autowired
	private JogadorService jogadorService;

	// Partida ja estara cadastrada, e sera enviada o ID da mesma pelo Mobile depois
	// de scanear o QR Code
	public Sessao iniciarSessao(Long partidaId, Long jogadorId) throws Exception {
		Sessao session = new Sessao();
		long sessaoId = 0;
		// Cria sessao automaticamente baseado no ID da partida
		try {
			Partidas partidaOptional = partidaService.encontrarPartida(partidaId);
			if (partidaOptional != null) {
				session.setId(sessaoId);
				session.setPartidas(partidaOptional);
				session.setStatus(true);
				session.setScore_jogador(0);
				List<Sessao> sessao = sessaoRepo.findAll();
				if (!sessao.isEmpty()) {
					sessaoId = sessaoRepo.findLastId() + 1;
					System.out.println("ID da sessao: " + sessaoId);
					session.setId(sessaoId);
				}

				sessaoRepo.save(session);
				boolean addSession = jogadorService.adicionarSessaoAoUsuario(sessaoId, true, jogadorId);

				// inicia a sessao do jogador caso o status dela seja false
				if (addSession) {
					return session;
				} else {
					System.out.println("Sessao que sera deletada " + sessaoId);
					sessaoRepo.deleteById(sessaoId);
					throw new Exception("Usuario ja tem sessao");
				}
			}
			// Caso partida nao exista
			throw new Exception("Partida inexistente");
		} catch (Exception e) {
			throw e;
		}

	}

	public Sessao buscarSessao(Long sessaoId) {
		return sessaoRepo.findById(sessaoId).orElse(null);
	}

	public boolean droparTodasSessoes() {
		List<Sessao> sessions = sessaoRepo.findAll();
		try {
			for (Sessao s : sessions) {
				System.out.println("SESSAO ID: " + s.getId());
				sessaoRepo.deleteAllSessions(s.getId());
			}
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

	public List<Ganhador> getWinners(Partidas partidas) {
		List<Ganhador> ganhadores = sessaoRepo.getWinners(partidas);
		return ganhadores;

	}

	public Sessao pontuarSessao(Long sessaoId) throws Exception {
		Sessao sessao = sessaoRepo.findById(sessaoId).orElse(null);
		if (sessao != null) {
			sessao.setScore_jogador(sessao.getScore_jogador() + 10);
			sessaoRepo.save(sessao);
			return sessao;
		} else {
			throw new Exception("Sessao nao encontrada");
		}
	}
}
