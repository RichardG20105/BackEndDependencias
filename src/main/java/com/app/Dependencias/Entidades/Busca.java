package com.app.Dependencias.Entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Busca")
public class Busca {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idbusca")
	private Long idBusca;

	@Column(name = "idusuario")
	private Long idUsuario;

	@Column(name = "iddependencia")
	private Long idDependencia;

	@Column(name = "favorito")
	private Boolean favorito;

	public Busca() {
		super();
	}

	public Busca(Long idUsuario, Long idDependencia, Boolean favorito) {
		super();
		this.idUsuario = idUsuario;
		this.idDependencia = idDependencia;
		this.favorito = favorito;
	}

	public Long getIdBusca() {
		return idBusca;
	}

	public void setIdBusca(Long idBusca) {
		this.idBusca = idBusca;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Long getIdDependencia() {
		return idDependencia;
	}

	public void setIdDependencia(Long idDependencia) {
		this.idDependencia = idDependencia;
	}

	public Boolean getFavorito() {
		return favorito;
	}

	public void setFavorito(Boolean favorito) {
		this.favorito = favorito;
	}

}
