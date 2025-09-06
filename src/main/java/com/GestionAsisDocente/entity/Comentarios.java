package com.GestionAsisDocente.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "comentarios")
public class Comentarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String archivo;

    private Boolean incognito;

    private String texto;

    private Integer likes;

    @ManyToOne
    @JoinColumn(name = "publicacion_id", nullable = false)
    private Publicaciones publicacion;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
}
