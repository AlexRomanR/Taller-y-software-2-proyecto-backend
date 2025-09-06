package com.GestionAsisDocente.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tipo")
public class Tipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String nombre;
}
