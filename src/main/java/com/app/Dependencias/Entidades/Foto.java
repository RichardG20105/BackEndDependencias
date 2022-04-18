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
@Table(name = "Foto")
public class Foto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idfoto")
	private Long idFoto;

	@Column(name = "nombrefoto")
	private String nombreFoto;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "iddependencia",referencedColumnName="iddependencia")
	private Dependencia dependencia;

	public Foto() {
		super();
	}

	public Foto(Long idFoto, String nombreFoto, int idDependencia) {
		super();
		this.idFoto = idFoto;
		this.nombreFoto = nombreFoto;
	}

	public Long getIdFoto() {
		return idFoto;
	}

	public void setIdFoto(Long idFoto) {
		this.idFoto = idFoto;
	}

	public String getNombreFoto() {
		return nombreFoto;
	}

	public void setNombreFoto(String nombreFoto) {
		this.nombreFoto = nombreFoto;
	}
}
