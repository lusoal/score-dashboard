package com.skynet.placarSo.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skynet.placarSo.model.bean.Jogador;
import com.skynet.placarSo.model.repository.JogadorRepository;

@Service
public class JogadorService {

	@Autowired
	private JogadorRepository jogadorRepo;

	public boolean cadastrarJogador(Jogador jogador) {
		try {
			jogadorRepo.save(jogador);
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

	public List<Jogador> listarTodosJogadores() {
		return jogadorRepo.findAll();
	}

	public Jogador buscarPorId(Long id) {
		Jogador jogador = jogadorRepo.findById(id).orElse(null);
		return jogador;
	}

	public Jogador validarLogin(Jogador jogador) {
		try {
			return jogadorRepo.validarLogin(jogador.getUsuario(), jogador.getSenha());
		} catch (Exception e) {
			throw e;
			// TODO: handle exception
		}
	}

	public Jogador buscarUnicoPorId(Long id) {
		return jogadorRepo.findById(id).orElse(null);
	}

	public boolean adicionarSessaoAoUsuario(Long sessaoId, boolean status, Long jogadorId) {
		Jogador jogador = jogadorRepo.findById(jogadorId).orElse(null);
		// Valida se o usuario ja tem uma sessao ativa
		if (jogador != null && jogador.isSessaoStatus() == false) {
			jogadorRepo.adicionarSessaoAoUsuario(sessaoId, status, jogadorId);
			return true;
		}
		return false;
	}

	public List<Jogador> getJogadoresAtivos() {
		return jogadorRepo.getJogadoresAtivos();
	}

	public Jogador updateJogador(Jogador jogador) {
		try {
			jogadorRepo.save(jogador);
			return jogador;
		} catch (Exception e) {
			throw e;
		}
	}

	public boolean removerJogador(long jogadorId) {
		try {
			jogadorRepo.deleteById(jogadorId);
			return true;
		} catch (Exception e) {
			throw e;
		}

	}

}
