package com.app.Dependencias.Repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.Dependencias.Entidades.Busca;

@Repository
public interface RepositorioBusca extends JpaRepository<Busca, Long> {
	List<Busca> findAllByIdUsuario(Long idUsuario);
}
