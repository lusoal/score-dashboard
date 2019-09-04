package com.skynet.placarSo.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.placarSo.model.bean.Jogador;


@Repository
public interface JogadorRepository extends JpaRepository<Jogador, Long>{
	
	public Jogador findOneByUsuarioAndSenha(String usuario, String senha);
	
	public static final String setSessao = "UPDATE tb_jogador\n" + 
			"SET sessao_id = ?, sessao_status = ?\n" + 
			"WHERE id = ?";
	@Modifying
	@Transactional
	@Query(value = setSessao, nativeQuery = true)
	public void adicionarSessaoAoUsuario(Long sessaoId, boolean status, Long id);
	
}