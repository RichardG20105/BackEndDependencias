package com.app.Dependencias.Repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.Dependencias.Entidades.Dependencia;

@Repository
public interface RepositorioDependencia extends JpaRepository<Dependencia, Long>{
	Optional<Dependencia> findByNombreDependencia(String nombreDependencia);
	List<Dependencia> findAllByIdTipoDependencia(Long idTipoDependencia);
	List<Dependencia> findByNombreDependenciaLike(String nombreDependecia);

	@Query(value = "SELECT * FROM dependencia LIMIT ?1",nativeQuery = true)
	List<Dependencia> findRecomendados(int limite);
}
