package com.GestionAsisDocente.repository;

import com.GestionAsisDocente.entity.Comentarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentariosRepository extends JpaRepository<Comentarios, Integer> {
}
