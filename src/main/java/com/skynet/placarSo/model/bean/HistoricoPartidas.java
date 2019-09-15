package com.skynet.placarSo.model.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "tb_historico")
public class HistoricoPartidas implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "jogador_id")
	private Jogador jogador;
	
	private Long pontuacaoSalvar;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Jogador getJogador() {
		return jogador;
	}

	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}

	public Long getPontuacaoSalvar() {
		return pontuacaoSalvar;
	}

	public void setPontuacaoSalvar(Long pontuacaoSalvar) {
		this.pontuacaoSalvar = pontuacaoSalvar;
	}

}
