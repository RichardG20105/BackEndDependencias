package com.app.Dependencias.Entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Tiposdependencia")
public class TipoDependencia {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idtipodependencia")
	private Long idTipoDependencia;

	@Column(name = "nombretipodependencia")
	private String nombreTipoDependencia;

	public TipoDependencia() {
		super();
	}

	public TipoDependencia(String nombreTipoDependencia) {
		super();
		this.nombreTipoDependencia = nombreTipoDependencia;
	}

	public Long getIdTipoDependencia() {
		return idTipoDependencia;
	}

	public void setIdTipoDependencia(Long idTipoDependencia) {
		this.idTipoDependencia = idTipoDependencia;
	}

	public String getNombreTipoDependencia() {
		return nombreTipoDependencia;
	}

	public void setNombreTipoDependencia(String nombreTipoDependencia) {
		this.nombreTipoDependencia = nombreTipoDependencia;
	}
}
