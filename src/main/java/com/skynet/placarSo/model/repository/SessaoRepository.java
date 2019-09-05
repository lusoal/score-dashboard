package com.skynet.placarSo.model.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.skynet.placarSo.model.bean.Sessao;


@Repository
public interface SessaoRepository extends JpaRepository<Sessao, Long> {
	
	public static final String FIND_LAST_SESSION = "SELECT MAX(ID) FROM tb_sessao";
	@Query(value = FIND_LAST_SESSION, nativeQuery = true)
    public Long findLastId();

}
