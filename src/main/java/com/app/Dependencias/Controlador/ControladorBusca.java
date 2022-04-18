package com.app.Dependencias.Controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.Dependencias.Entidades.Busca;
import com.app.Dependencias.Excepciones.ResourceNotFoundException;
import com.app.Dependencias.Repositorio.RepositorioBusca;
@RestController
@RequestMapping("/Busca")
public class ControladorBusca {
	@Autowired
	private RepositorioBusca RepositorioBusca;

	@GetMapping("/{idUsuario}")
	public List<Busca> getFavoritos(@PathVariable(value = "idUsuario")Long IdUsuario)throws ResourceNotFoundException{
		List<Busca> favoritos = RepositorioBusca.findAllByIdUsuario(IdUsuario);
		if(favoritos.isEmpty())
			throw new ResourceNotFoundException("No hay favoritos almacenados");
		return favoritos;
	}
}
