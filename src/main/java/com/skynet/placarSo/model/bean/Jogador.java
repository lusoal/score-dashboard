package com.skynet.placarSo.model.bean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

	@Column(nullable = true, length = 200)
	@JsonProperty("senha")
	private String senha;

	@OneToOne
	@JoinColumn(name = "sessao_id")
	@JsonBackReference
	private Sessao sessao;
	
	@JsonProperty("sessaoStatus")
	private boolean sessaoStatus;
	
	@OneToMany(mappedBy = "jogador", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<HistoricoPartidas> historicoPartidas;

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


	@Override
	public String toString() {
		return "{" +
			" id='" + getId() + "'" +
			", usuario='" + getUsuario() + "'" +
			", senha='" + getSenha() + "'" +
			", sessao='" + getSessao() + "'" +
			", sessaoStatus='" + isSessaoStatus() + "'" +
			"}";
	}

}
