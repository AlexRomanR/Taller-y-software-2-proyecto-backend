package com.GestionAsisDocente.repository;

import com.GestionAsisDocente.entity.Publicaciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicacionesRepository extends JpaRepository<Publicaciones, Integer> {
}
