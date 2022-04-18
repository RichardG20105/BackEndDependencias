package com.app.Dependencias.Controlador;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.Dependencias.Entidades.Dependencia;
import com.app.Dependencias.Excepciones.ResourceNotFoundException;
import com.app.Dependencias.Repositorio.RepositorioDependencia;

@RestController
@RequestMapping("/Dependencia")
public class ControladorDependencia {
	@Autowired
	private RepositorioDependencia RepositorioDependencia;

	@GetMapping("/Listado")
	public List<Dependencia> getDependencias() throws ResourceNotFoundException{
		List<Dependencia> Dependencias = RepositorioDependencia.findAll();

		if(Dependencias.isEmpty()) {
			throw new ResourceNotFoundException("No hay Dependencias");
		}
		return Dependencias;
	}

	@GetMapping("/Listado/{nombre}")
	public List<Dependencia> getDependenciasCoincidencias(@PathVariable(value = "nombre")String NombreDependencia)throws ResourceNotFoundException{
		List<Dependencia> Dependencias = RepositorioDependencia.findByNombreDependenciaLike("%"+NombreDependencia+"%");

		return Dependencias;
	}

	@GetMapping("/Recomendados")
	public List<Dependencia> getRecomendados()throws ResourceNotFoundException{
		List<Dependencia> Dependencias = RepositorioDependencia.findRecomendados(5);

		if(Dependencias.isEmpty()) {
			throw new ResourceNotFoundException("No existen Dependencias");
		}

		return Dependencias;
	}

	@GetMapping("/ID/{id}")
	public Dependencia getDependenciaId(@PathVariable(value = "id")Long IdDep)throws ResourceNotFoundException {
		Dependencia dependencia = RepositorioDependencia.findById(IdDep)
				.orElseThrow(() -> new ResourceNotFoundException("No existe la dependencia con el ID: "+IdDep));
		return dependencia;
	}

	@GetMapping("/Nombre/{nombre}")
	public Dependencia getDependenciaNombre(@PathVariable(value = "nombre")String NombreDependencia)throws ResourceNotFoundException{
		Dependencia dependencia = RepositorioDependencia.findByNombreDependencia(NombreDependencia)
				.orElseThrow(() -> new ResourceNotFoundException("No existe la dependencia "+NombreDependencia));
		return dependencia;
	}

	@GetMapping("/Tipo/{tipoDependencia}")
	public List<Dependencia> getTipoDependencia(@PathVariable(value = "tipoDependencia")Long IdTipoDependencia)throws ResourceNotFoundException{
		List<Dependencia> dependencias = RepositorioDependencia.findAllByIdTipoDependencia(IdTipoDependencia);

		if(dependencias.isEmpty())
			throw new ResourceNotFoundException("No existen dependencias para ese tipo");

		return dependencias;
	}

	@PostMapping("/Registrar")
	public Dependencia saveDependencia(@Valid @RequestBody Dependencia dependencia) {
		return this.RepositorioDependencia.save(dependencia);
	}
}
