package com.app.Dependencias.Entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

	@Column(name = "favorito")
	private Boolean favorito;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "iddependencia", referencedColumnName = "iddependencia")
	private Dependencia dependencias;

	public Busca() {
		super();
	}

	public Busca(Long idBusca, Long idUsuario, Boolean favorito) {
		super();
		this.idBusca = idBusca;
		this.idUsuario = idUsuario;
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

	public Boolean getFavorito() {
		return favorito;
	}

	public void setFavorito(Boolean favorito) {
		this.favorito = favorito;
	}

	public Dependencia getDependencias() {
		return dependencias;
	}

	public void setDependencias(Dependencia dependencias) {
		this.dependencias = dependencias;
	}
}
