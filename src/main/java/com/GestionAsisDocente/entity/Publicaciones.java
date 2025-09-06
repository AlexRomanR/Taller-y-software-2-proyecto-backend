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

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario; // quien publica

    @ManyToOne
    @JoinColumn(name = "tipo_id", nullable = false)
    private Tipo tipo;
}
