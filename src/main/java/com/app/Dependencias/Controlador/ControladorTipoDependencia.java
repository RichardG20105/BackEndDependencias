package com.app.Dependencias.Controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.Dependencias.Entidades.TipoDependencia;
import com.app.Dependencias.Excepciones.ResourceNotFoundException;
import com.app.Dependencias.Repositorio.RepositorioTipoDependencia;

@RestController
@RequestMapping("/TipoDependencia")
public class ControladorTipoDependencia {
	@Autowired
	private RepositorioTipoDependencia RepositorioTipoDependencia;

	@GetMapping("/Listado")
	public List<TipoDependencia> getTipoDependencias()throws ResourceNotFoundException{
		List<TipoDependencia> tipoDependencias = RepositorioTipoDependencia.findAll();

		if(tipoDependencias.isEmpty())
			throw new ResourceNotFoundException("No existen tipos de dependecias");

		return tipoDependencias;
	}

	@GetMapping("/{id}")
	public TipoDependencia getTipoDependencia(@PathVariable(value = "id")Long IdTipoDependencia)throws ResourceNotFoundException{
		TipoDependencia tipoDependencia = RepositorioTipoDependencia.findById(IdTipoDependencia)
				.orElseThrow(() -> new ResourceNotFoundException("No se encuentra el tipo de dependencia"));
		return tipoDependencia;
	}

	@GetMapping("/{nombre}")
	public TipoDependencia getTipoDependenciaNombre(@PathVariable(value = "nombre")String NombreTipoDependencia)throws ResourceNotFoundException{
		TipoDependencia tipoDependencia = RepositorioTipoDependencia.findByNombreTipoDependencia(NombreTipoDependencia)
				.orElseThrow(()-> new ResourceNotFoundException("No se encontro el tipo de dependencia"));
		return tipoDependencia;
	}
}
