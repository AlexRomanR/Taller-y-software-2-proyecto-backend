package com.GestionAsisDocente.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "mensaje")
public class Mensaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Opcional: puede ser null si solo se envía texto
    @Column(nullable = true)
    private String archivo;

    // Opcional también, por si quieres mensajes solo con archivo
    @Column(nullable = true)
    private String texto;

    @ManyToOne
    @JoinColumn(name = "chat_id", nullable = false)
    private Chat chat;

    @ManyToOne
    @JoinColumn(name = "mandado_por_id", nullable = false)
    private Usuario mandadoPor;
}
