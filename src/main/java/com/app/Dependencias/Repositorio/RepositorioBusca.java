package com.app.Dependencias.Repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.Dependencias.Entidades.Busca;
import com.app.Dependencias.Entidades.Dependencia;

@Repository
public interface RepositorioBusca extends JpaRepository<Busca, Long> {
	List<Busca> findAllByIdUsuario(Long idUsuario);
	Optional<Busca> findByIdUsuarioAndDependencias(Long idUsuario, Dependencia dependencias);

	Boolean existsByIdUsuarioAndDependencias_idDependencia(Long idUsuario, Long dependencias);
}
