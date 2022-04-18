package com.app.Dependencias.Entidades;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name="Dependencia")
public class Dependencia {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="iddependencia")
	private Long idDependencia;

	@Column(name="idtipodependencia")
	private Long idTipoDependencia;

	@Column(name="nombredependencia")
	private String nombreDependencia;

	@Column(name="descripciondependencia")
	private String descripcionDependencia;

	@Column(name="latitud")
	private Double latitud;

	@Column(name="longitud")
	private Double longitud;

	@OneToMany(mappedBy="dependencia", cascade = CascadeType.ALL)
	private Set<Foto> fotos;

	public Dependencia() {
		super();
	}

	public Dependencia(Long idTipoDependencia, String nombreDependencia, String descripcionDependencia, Double latitud,
			Double longitud) {
		super();
		this.idTipoDependencia = idTipoDependencia;
		this.nombreDependencia = nombreDependencia;
		this.descripcionDependencia = descripcionDependencia;
		this.latitud = latitud;
		this.longitud = longitud;
	}

	public Long getIdDependencia() {
		return idDependencia;
	}

	public void setIdDependencia(Long idDependencia) {
		this.idDependencia = idDependencia;
	}

	public Long getIdTipoDependencia() {
		return idTipoDependencia;
	}

	public void setIdTipoDependencia(Long idTipoDependencia) {
		this.idTipoDependencia = idTipoDependencia;
	}

	public String getNombreDependencia() {
		return nombreDependencia;
	}

	public void setNombreDependencia(String nombreDependencia) {
		this.nombreDependencia = nombreDependencia;
	}

	public String getDescripcionDependencia() {
		return descripcionDependencia;
	}

	public void setDescripcionDependencia(String descripcionDependencia) {
		this.descripcionDependencia = descripcionDependencia;
	}

	public Double getLatitud() {
		return latitud;
	}

	public void setLatitud(Double latitud) {
		this.latitud = latitud;
	}

	public Double getLongitud() {
		return longitud;
	}

	public void setLongitud(Double longitud) {
		this.longitud = longitud;
	}

	public Set<Foto> getFotos() {
		return fotos;
	}

	public void setFotos(Set<Foto> fotos) {
		this.fotos = fotos;
	}
}
