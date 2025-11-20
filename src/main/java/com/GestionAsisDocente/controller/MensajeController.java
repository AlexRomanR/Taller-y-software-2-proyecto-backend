package com.GestionAsisDocente.controller;

import com.GestionAsisDocente.dto.MensajeRequest;
import com.GestionAsisDocente.entity.Mensaje;
import com.GestionAsisDocente.service.MensajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public")
public class MensajeController {

    @Autowired
    private MensajeService mensajeService;

    // Crear mensaje
    @PostMapping("/mensaje-create")
    public ResponseEntity<Mensaje> crearMensaje(@RequestBody MensajeRequest request) {
        Mensaje mensaje = mensajeService.crearMensaje(request);
        return ResponseEntity.ok(mensaje);
    }

    // Listar mensajes de un chat
    @GetMapping("/mensaje-chat/{chatId}")
    public ResponseEntity<List<Mensaje>> getMensajesPorChat(@PathVariable Integer chatId) {
        List<Mensaje> mensajes = mensajeService.getMensajesPorChat(chatId);
        return ResponseEntity.ok(mensajes);
    }

    // Obtener mensaje por id
    @GetMapping("/mensaje-get/{id}")
    public ResponseEntity<Mensaje> getMensajeById(@PathVariable Integer id) {
        Mensaje mensaje = mensajeService.getMensajeById(id);
        return ResponseEntity.ok(mensaje);
    }

    // Eliminar mensaje
    @DeleteMapping("/mensaje-delete/{id}")
    public ResponseEntity<Void> eliminarMensaje(@PathVariable Integer id) {
        mensajeService.eliminarMensaje(id);
        return ResponseEntity.noContent().build();
    }
}
