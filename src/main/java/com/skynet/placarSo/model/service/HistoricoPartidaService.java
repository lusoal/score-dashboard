package com.skynet.placarSo.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skynet.placarSo.model.bean.HistoricoPartidas;
import com.skynet.placarSo.model.bean.Jogador;
import com.skynet.placarSo.model.repository.HistoricoPartidasRepository;

@Service
public class HistoricoPartidaService {

	@Autowired
	private HistoricoPartidasRepository historicoRepo;

	public boolean salvarHistorico(Long pontuacao, Jogador jogador) {
		// Criando Historico dentro do metodo, nao recebendo de fora
		HistoricoPartidas historico = new HistoricoPartidas();
		historico.setJogador(jogador);
		historico.setPontuacaoSalvar(pontuacao);
		try {
			historicoRepo.save(historico);
			return true;
		} catch (Exception e) {
			throw e;
		}

	}

	public Long getPontuacaoJogador(Long jogadorId) {
		
		Long pontuacao = historicoRepo.getPontuacaoJogador(jogadorId);
		if (pontuacao != null) {
			return pontuacao;
		}else {
			return (long) 0;
		}
		 
	}

}
