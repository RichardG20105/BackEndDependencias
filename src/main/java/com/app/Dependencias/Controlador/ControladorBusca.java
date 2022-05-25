package com.app.Dependencias.Controlador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.Dependencias.Entidades.Busca;
import com.app.Dependencias.Entidades.Dependencia;
import com.app.Dependencias.Entidades.Usuario;
import com.app.Dependencias.Excepciones.ResourceNotFoundException;
import com.app.Dependencias.Repositorio.RepositorioBusca;
import com.app.Dependencias.Repositorio.RepositorioDependencia;
import com.app.Dependencias.Repositorio.RepositorioUsuario;

@RestController
@RequestMapping("/Busca")
public class ControladorBusca {
	@Autowired
	private RepositorioBusca RepositorioBusca;

	@Autowired
	private RepositorioUsuario RepositorioUsuario;

	@Autowired
	private RepositorioDependencia RepositorioDependencia;

	@GetMapping("/Listado")
	public List<Busca> getListadoFavoritos()throws ResourceNotFoundException{
		List<Busca> favoritos = RepositorioBusca.findAll();

		if(favoritos.isEmpty())
			throw new ResourceNotFoundException("No existen favoritos");
		return favoritos;
	}

	@GetMapping("/{Usuario}")
	public List<Busca> getFavoritos(@PathVariable(value = "Usuario")String Usuario)throws ResourceNotFoundException{
		Usuario usuario = RepositorioUsuario.findByUsuario(Usuario);

		if(usuario.getIdUsuario() != 0) {
			List<Busca> favoritos = RepositorioBusca.findAllByIdUsuario(usuario.getIdUsuario());
			if(favoritos.isEmpty())
				throw new ResourceNotFoundException("No hay favoritos almacenados");
			return favoritos;
		}
		throw new ResourceNotFoundException("No existe el usuario");
	}

	@GetMapping("/Favorito/{Usuario}/{id}")
	public Boolean getFavoritoDependencia(@PathVariable(value = "Usuario")String Usuario,@PathVariable(value = "id")Long IdDependencia) throws ResourceNotFoundException{
		Long IdUsuario = RepositorioUsuario.findByUsuario(Usuario).getIdUsuario();

		Boolean estado = RepositorioBusca.existsByIdUsuarioAndDependencias_idDependencia(IdUsuario,IdDependencia);
		if(estado == Boolean.TRUE || estado == Boolean.FALSE) {
			return estado;
		}

		throw new ResourceNotFoundException("No se econtro la dependencia o el error en el Usuario");

	}

	@GetMapping("/AgregarFavorito/{Usuario}/{id}")
	public Busca setFavorito(@PathVariable(value = "Usuario")String Usuario,@PathVariable(value = "id")Long IdDependencia)throws ResourceNotFoundException{
		Long usuario = RepositorioUsuario.findByUsuario(Usuario).getIdUsuario();
		Dependencia dependencia = RepositorioDependencia.findById(IdDependencia)
				.orElseThrow(() -> new ResourceNotFoundException("No existe esa Dependencia"));

		if(usuario != 0) {
			Busca favorito = new Busca();

			favorito.setIdUsuario(usuario);
			favorito.setFavorito(Boolean.TRUE);
			favorito.setDependencias(dependencia);

			return this.RepositorioBusca.save(favorito);
		}

		throw new ResourceNotFoundException("No existe el usuario");
	}

	@DeleteMapping("/EliminarFavorito/{Usuario}/{id}")
	public Map<String, Boolean> deleteFavorito(@PathVariable(value = "Usuario")String Usuario,@PathVariable(value = "id")Long IdDependencia)throws ResourceNotFoundException{
		Long usuario = RepositorioUsuario.findByUsuario(Usuario).getIdUsuario();

		Dependencia dependencia = RepositorioDependencia.findById(IdDependencia)
				.orElseThrow(() -> new ResourceNotFoundException("No existe esa Dependencia"));

		if(usuario != 0) {
			Busca favorito = RepositorioBusca.findByIdUsuarioAndDependencias(usuario,dependencia)
					.orElseThrow(() -> new ResourceNotFoundException("No existe la dependencia a eliminar"));
			this.RepositorioBusca.delete(favorito);
			Map<String, Boolean> respuesta = new HashMap<>();
			respuesta.put("La dependencia ya no es un favorito", Boolean.TRUE);
			return respuesta;
		}
		throw new ResourceNotFoundException("No existe el usuario");
	}
}
