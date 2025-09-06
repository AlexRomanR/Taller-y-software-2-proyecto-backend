package com.GestionAsisDocente.controller;

import com.GestionAsisDocente.dto.ChatRequest;
import com.GestionAsisDocente.entity.Chat;
import com.GestionAsisDocente.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/adminuser")
public class ChatController {

    @Autowired
    private ChatService chatService;

    // Crear chat
    @PostMapping("/create")
    public ResponseEntity<Chat> crearChat(@RequestBody ChatRequest chatRequest) {
        Chat chat = chatService.crearChat(chatRequest);
        return ResponseEntity.ok(chat);
    }

    // Eliminar chat por id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> eliminarChat(@PathVariable Integer id) {
        chatService.eliminarChat(id);
        return ResponseEntity.noContent().build();
    }
}
