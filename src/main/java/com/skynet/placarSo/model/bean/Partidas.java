package com.skynet.placarSo.model.bean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "tb_partidas")
public class Partidas implements Serializable {

	private static final long serialVersionUID = 1L;
	//Implemetar Status da Partida e nao da sessao, ao finalizar a partida finalizado todas as sessoes vinculadas
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@OneToMany(mappedBy = "partidas", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Sessao> sessao;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Sessao> getSessao() {
		return sessao;
	}

	public void setSessao(List<Sessao> sessao) {
		this.sessao = sessao;
	}

}
