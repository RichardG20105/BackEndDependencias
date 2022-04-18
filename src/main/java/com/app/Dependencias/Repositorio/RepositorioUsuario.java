package com.app.Dependencias.Repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.Dependencias.Entidades.Usuario;

@Repository
public interface RepositorioUsuario extends JpaRepository<Usuario, Long>{
	Optional<Usuario> findByNombresAndApellidos(String nombres, String apellidos);
	Boolean existsByUsuario(String usuario);
	Boolean existsByUsuarioAndContrasena(String usuario,String contrasena);
	Usuario findByUsuario(String usuario);
	Optional<Usuario> findByUsuarioAndContrasena(String usuario, String contrasena);
}
