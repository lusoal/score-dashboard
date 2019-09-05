package com.skynet.placarSo.model.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "tb_jogador")
public class Jogador implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false, length = 200)
	@JsonProperty("usuario")
	private String usuario;

	@Column(nullable = false, length = 200)
	@JsonProperty("senha")
	private String senha;

	@OneToOne
	@JoinColumn(name = "sessao_id")
	@JsonBackReference
	private Sessao sessao;
	
	@JsonProperty("sessaoStatus")
	private boolean sessaoStatus;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Sessao getSessao() {
		return sessao;
	}

	public void setSessao(Sessao sessao) {
		this.sessao = sessao;
	}

	public boolean isSessaoStatus() {
		return sessaoStatus;
	}

	public void setSessaoStatus(boolean sessaoStatus) {
		this.sessaoStatus = sessaoStatus;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
