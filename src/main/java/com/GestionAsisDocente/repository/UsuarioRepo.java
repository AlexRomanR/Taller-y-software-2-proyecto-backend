package com.GestionAsisDocente.repository;

import com.GestionAsisDocente.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepo extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByEmail(String email);

    @Query("SELECT u FROM Usuario u LEFT JOIN FETCH u.rolUsers ru LEFT JOIN FETCH ru.rol WHERE u.email = :email")
    Optional<Usuario> findByEmailWithRoles(@Param("email") String email);

    @Query("SELECT r.nombre FROM RolUser ru JOIN ru.rol r WHERE ru.usuario.id = :userId")
    List<String> findRoleNamesByUserId(@Param("userId") Integer userId);
}