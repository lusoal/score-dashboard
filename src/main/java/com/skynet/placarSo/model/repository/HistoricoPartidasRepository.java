package com.skynet.placarSo.model.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.skynet.placarSo.model.bean.HistoricoPartidas;

@Repository
public interface HistoricoPartidasRepository extends JpaRepository<HistoricoPartidas, Long>{
	
	public static final String PONTUACAO_TOTAL = "SELECT sum(pontuacao_salvar) FROM jogoSo.tb_historico WHERE jogador_id = ?";

	@Query(value = PONTUACAO_TOTAL, nativeQuery = true)
	public Long getPontuacaoJogador(Long jogador_id);


}
