package com.GestionAsisDocente.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "rol_user")
public class RolUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Usuario usuario; // ← DEBE llamarse "usuario"

    @ManyToOne
    @JoinColumn(name = "rol_id", nullable = false)
    private Roles rol; // ← DEBE llamarse "rol"


}
