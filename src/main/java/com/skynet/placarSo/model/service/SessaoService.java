package com.skynet.placarSo.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public boolean iniciarSessao(Long partidaId, Long jogadorId) throws Exception {
		Sessao session = new Sessao();
		long sessaoId = 1;
		// Cria sessao automaticamente baseado no ID da partida
		try {
			Partidas partidaOptional = partidaService.encontrarPartida(partidaId);
			if (partidaOptional != null) {
				session.setId(sessaoId);
				session.setPartidas(partidaOptional);
				session.setStatus(true);
				session.setScore_jogador(0);
				List <Sessao> sessao = sessaoRepo.findAll();
				
				if (!sessao.isEmpty()) {
					sessaoId = sessaoRepo.findLastId() + 1;
					session.setId(sessaoId);
				}
				
				sessaoRepo.save(session);
				boolean addSession = jogadorService.adicionarSessaoAoUsuario(sessaoId, true, jogadorId);
				
				// inicia a sessao do jogador caso o status dela seja false
				if (addSession) {
					return true;
				} else {
					System.out.println("Sessao que sera deletada " +  sessaoId);
					 sessaoRepo.deleteById(sessaoId);
					 throw new Exception("Usuario ja tem sessao");
				}
			}
			// Caso partida nao exista
			return false;
		} catch (Exception e) {
			throw e;
		}

	}
	
	public Sessao buscarSessao(Long sessaoId) {
		return sessaoRepo.findById(sessaoId).orElse(null);
	}
	
}
