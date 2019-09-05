package com.skynet.placarSo.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.skynet.placarSo.model.bean.Partidas;

@Repository
public interface PartidasRepository extends JpaRepository<Partidas, Long> {

	public static final String FIND_PARTIDA_ANDAMENTO = "SELECT * FROM tb_partidas WHERE status = true";
	@Query(value = FIND_PARTIDA_ANDAMENTO, nativeQuery = true)
    public Partidas findPartidaAndamento();
}
