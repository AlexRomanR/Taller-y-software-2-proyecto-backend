package com.GestionAsisDocente.repository;

import com.GestionAsisDocente.entity.Publicaciones;
import com.GestionAsisDocente.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicacionesRepository extends JpaRepository<Publicaciones, Integer> {
    List<Publicaciones> findByEstadoFalse();

    // Retorna todas las publicaciones de un usuario específico
    List<Publicaciones> findByUsuario(Usuario usuario);
    List<Publicaciones> findByTipoIdAndEstadoTrue(Integer tipoId);
}
