package com.GestionAsisDocente.repository;

import com.GestionAsisDocente.entity.Chat;
import com.GestionAsisDocente.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Integer> {

    Optional<Chat> findByUsuario1AndUsuario2OrUsuario2AndUsuario1(
            Usuario usuario1, Usuario usuario2,
            Usuario usuario2Alt, Usuario usuario1Alt
    );
}
