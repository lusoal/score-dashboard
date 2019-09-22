package com.skynet.placarSo.model.bean;

import java.util.Objects;


public class Ganhador {

    private String usuario;
    private Long scoreJogador;


    public Ganhador() {
    }

    public Ganhador(String usuario, Long scoreJogador) {
        this.usuario = usuario;
        this.scoreJogador = scoreJogador;
    }

    public String getUsuario() {
        return this.usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Long getScoreJogador() {
        return this.scoreJogador;
    }

    public void setScoreJogador(Long scoreJogador) {
        this.scoreJogador = scoreJogador;
    }

    public Ganhador usuario(String usuario) {
        this.usuario = usuario;
        return this;
    }

    public Ganhador scoreJogador(Long scoreJogador) {
        this.scoreJogador = scoreJogador;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Ganhador)) {
            return false;
        }
        Ganhador ganhador = (Ganhador) o;
        return Objects.equals(usuario, ganhador.usuario) && Objects.equals(scoreJogador, ganhador.scoreJogador);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuario, scoreJogador);
    }

    @Override
    public String toString() {
        return "{" +
            " usuario='" + getUsuario() + "'" +
            ", scoreJogador='" + getScoreJogador() + "'" +
            "}";
    }

}