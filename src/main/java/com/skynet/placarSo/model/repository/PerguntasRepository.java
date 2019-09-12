package com.skynet.placarSo.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skynet.placarSo.model.bean.Perguntas;

@Repository
public interface PerguntasRepository extends JpaRepository<Perguntas, Long>{
	
	public List<Perguntas> findAllByTemaId(long id);
}
