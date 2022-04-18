package com.app.Dependencias.Repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.Dependencias.Entidades.TipoDependencia;

@Repository
public interface RepositorioTipoDependencia extends JpaRepository<TipoDependencia,Long>{
	Optional<TipoDependencia> findByNombreTipoDependencia(String nombreTipoDependencia);
}
