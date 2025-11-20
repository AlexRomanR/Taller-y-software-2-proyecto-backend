package com.GestionAsisDocente.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class PublicacionesRequest {
    private String titulo;
    private String descripcion;
    private LocalDate fecha;
    private LocalTime hora;
    private String ubicacion;
    private String archivo;
    private Integer tipoId; // id del tipo asociado
    private Integer usuarioId;
    private boolean estado;
}
