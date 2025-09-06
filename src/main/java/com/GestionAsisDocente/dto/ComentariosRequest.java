package com.GestionAsisDocente.dto;

import lombok.Data;

@Data
public class ComentariosRequest {
    private String archivo;
    private Boolean incognito;
    private String texto;
    private Integer likes;
    private Integer publicacionId;
    private Integer usuarioId;
}
