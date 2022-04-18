package com.app.Dependencias.Repositorio;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.Dependencias.Entidades.Foto;

@Repository
public interface RepositorioFoto extends JpaRepository<Foto, Long>{

}
