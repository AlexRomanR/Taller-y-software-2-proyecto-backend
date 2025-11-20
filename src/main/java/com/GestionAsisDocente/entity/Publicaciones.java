package com.GestionAsisDocente.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "publicaciones")
public class Publicaciones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String titulo;

    private String descripcion;

    private LocalDate fecha;

    private LocalTime hora;

    private String ubicacion;

    private boolean estado;

    @Column(nullable = true) // permite null
    private String archivo;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = true) // permite null
    private Usuario usuario; // quien publica

    @ManyToOne
    @JoinColumn(name = "tipo_id", nullable = false)
    private Tipo tipo;
}
