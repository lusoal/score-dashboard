package com.skynet.placarSo.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.placarSo.model.bean.Sessao;

@Repository
public interface SessaoRepository extends JpaRepository<Sessao, Long> {

	public static final String FIND_LAST_SESSION = "SELECT MAX(ID) FROM tb_sessao";

	@Query(value = FIND_LAST_SESSION, nativeQuery = true)
	public Long findLastId();

	public static final String DELETE_ALL_SESSIONS = "DELETE FROM tb_sessao WHERE id = ?";

	@Modifying
	@Transactional
	@Query(value = DELETE_ALL_SESSIONS, nativeQuery = true)
	public void deleteAllSessions(Long sessaoId);

}
