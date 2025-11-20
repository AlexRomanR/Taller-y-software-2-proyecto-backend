package com.GestionAsisDocente.dto;

import lombok.Data;

@Data
public class MensajeRequest {

    private Integer chatId;        // id del chat
    private Integer mandadoPorId;  // id del usuario que manda el mensaje
    private String archivo;        // url / nombre del archivo (opcional)
    private String texto;          // texto del mensaje (opcional)
}
