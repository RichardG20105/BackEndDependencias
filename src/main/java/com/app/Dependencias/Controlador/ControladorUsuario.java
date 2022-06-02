package com.app.Dependencias.Controlador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.Dependencias.Entidades.Usuario;
import com.app.Dependencias.Excepciones.ResourceNotFoundException;
import com.app.Dependencias.Repositorio.RepositorioUsuario;
import com.app.Dependencias.Seguridad.JwtTokenUtil;
import com.app.Dependencias.Servicio.JwtUserDetailsService;

@RestController
@RequestMapping("/Usuario")
public class ControladorUsuario {
	@Autowired
	private RepositorioUsuario RepositorioUsuario;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService jwtDetailsService;

	@GetMapping
	public List<Usuario> getUsuarios()throws ResourceNotFoundException{
		List<Usuario> Usuarios = RepositorioUsuario.findAll();

		if(Usuarios.isEmpty())
			throw new ResourceNotFoundException("No existen usuarios registrados");
		return Usuarios;
	}

	@PostMapping("/Info")
	public Usuario getUsuarioId(@RequestBody Usuario usuario)throws ResourceNotFoundException{
		Usuario UsuarioInfo = RepositorioUsuario.findByUsuarioAndContrasena(usuario.getUsuario(),usuario.getContrasena())
				.orElseThrow(() -> new ResourceNotFoundException("No existen el Usuario"));
		return UsuarioInfo;
	}

	@PostMapping("/Sesion")
	public ResponseEntity<?> generaraAutenticacion(@RequestBody Usuario usuario)throws Exception{
		if(RepositorioUsuario.existsByUsuarioAndContrasena(usuario.getUsuario(),usuario.getContrasena())) {
			final UserDetails userDetails = jwtDetailsService.loadUserByUsername(usuario.getUsuario());

			final String token = jwtTokenUtil.generateToken(userDetails);

			return ResponseEntity.ok().body(token);
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping("/Registro")
	public Usuario postUsuario(@Valid @RequestBody Usuario registro)throws ResourceNotFoundException{
		if(RepositorioUsuario.existsByUsuario(registro.getUsuario())) {
			throw new ResourceNotFoundException("Ya existe una cuenta con ese nombre de usuario");
		}
		return this.RepositorioUsuario.save(registro);
	}

	@PutMapping("/Actualizar/{id}")
	public ResponseEntity<Usuario> putUsuario(@PathVariable(value = "id")Long IdUsuario,@Valid @RequestBody Usuario usuario)throws ResourceNotFoundException{
		Usuario usuarioAct = RepositorioUsuario.findById(IdUsuario)
				.orElseThrow(() -> new ResourceNotFoundException("No existe ese Usuario"));
		if(RepositorioUsuario.existsByUsuario(usuario.getUsuario())) {
			Usuario usuarioComp = RepositorioUsuario.findByUsuario(usuario.getUsuario());
			if(usuarioComp.getIdUsuario() != IdUsuario) {
				throw new ResourceNotFoundException("Ya existe una cuenta con ese nombre de usuario");
			}
		}

		usuarioAct.setNombres(usuario.getNombres());
		usuarioAct.setApellidos(usuario.getApellidos());
		usuarioAct.setUsuario(usuario.getUsuario());
		usuarioAct.setContrasena(usuario.getContrasena());
		usuarioAct.setGenero(usuario.getGenero());
		usuarioAct.setCiudad(usuario.getCiudad());
		usuarioAct.setTelefono(usuario.getTelefono());
		usuarioAct.setCorreo(usuario.getCorreo());

		return ResponseEntity.ok(this.RepositorioUsuario.save(usuarioAct));
	}

	@DeleteMapping("/Eliminar/{id}")
	public Map<String, Boolean> deleteUsuario(@PathVariable(value = "id")Long IdUsuario)throws ResourceNotFoundException{
		Usuario usuario = RepositorioUsuario.findById(IdUsuario)
				.orElseThrow(() -> new ResourceNotFoundException("No existe el usuario"));

		this.RepositorioUsuario.delete(usuario);
		Map<String, Boolean> respuesta = new HashMap<>();
		respuesta.put("El usuario se elimino correctamente", Boolean.TRUE);
		return respuesta;
	}
}
