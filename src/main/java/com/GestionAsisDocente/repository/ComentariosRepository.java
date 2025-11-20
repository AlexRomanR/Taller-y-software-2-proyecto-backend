package com.GestionAsisDocente.repository;

import com.GestionAsisDocente.entity.Comentarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComentariosRepository extends JpaRepository<Comentarios, Integer> {

    List<Comentarios> findByPublicacionIdOrderByIdAsc(Integer publicacionId);
}
