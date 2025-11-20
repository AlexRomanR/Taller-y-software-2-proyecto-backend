package com.GestionAsisDocente.repository;

import com.GestionAsisDocente.entity.Chat;
import com.GestionAsisDocente.entity.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MensajeRepository extends JpaRepository<Mensaje, Integer> {

    // Lista de mensajes de un chat, ordenados por id (básico)
    List<Mensaje> findByChatOrderByIdAsc(Chat chat);
}
