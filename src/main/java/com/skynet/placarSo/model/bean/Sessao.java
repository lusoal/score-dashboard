package com.skynet.placarSo.model.bean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "tb_sessao")
public class Sessao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@JsonProperty("status")
	private boolean status;
	
	@JsonProperty("score")
	private long score_jogador;
	
	//Mudar para OneToOne
	@OneToOne(mappedBy = "sessao", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference
	private Jogador jogador;

	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "partida_id")
	private Partidas partidas;

	public Partidas getPartidas() {
		return partidas;
	}

	public void setPartidas(Partidas partida) {
		this.partidas = partida;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public long getScore_jogador() {
		return score_jogador;
	}

	public void setScore_jogador(long score_jogador) {
		this.score_jogador = score_jogador;
	}

	public Jogador getJogador() {
		return jogador;
	}

	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}

	

}
