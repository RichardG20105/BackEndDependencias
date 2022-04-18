package com.app.Dependencias.Servicio;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.Dependencias.Entidades.Usuario;
import com.app.Dependencias.Repositorio.RepositorioUsuario;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	@Autowired
	private RepositorioUsuario repositorioUsuario;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = repositorioUsuario.findByUsuario(username);
		if(usuario == null) {
			throw new UsernameNotFoundException("No existe el usuario: "+username);
		}
		return new org.springframework.security.core.userdetails.User(usuario.getUsuario(), usuario.getContrasena(), new ArrayList<>());
	}
}
