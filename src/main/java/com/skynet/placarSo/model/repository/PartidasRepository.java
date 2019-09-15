package com.skynet.placarSo.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.placarSo.model.bean.Partidas;

@Repository
public interface PartidasRepository extends JpaRepository<Partidas, Long> {

	public static final String FIND_PARTIDA_ANDAMENTO = "SELECT * FROM tb_partidas WHERE status = true";
	@Query(value = FIND_PARTIDA_ANDAMENTO, nativeQuery = true)
    public Partidas findPartidaAndamento();
	
	public static final String DELETE_PARTIDA_BY_ID = "DELETE FROM tb_partidas WHERE id = ?";

	@Modifying
	@Transactional
	@Query(value = DELETE_PARTIDA_BY_ID, nativeQuery = true)
	public void deletePartida(Long partidaId);
}
