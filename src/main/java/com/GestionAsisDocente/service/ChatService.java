package com.GestionAsisDocente.service;

import com.GestionAsisDocente.dto.ChatRequest;
import com.GestionAsisDocente.entity.Chat;
import com.GestionAsisDocente.entity.Usuario;
import com.GestionAsisDocente.repository.ChatRepository;
import com.GestionAsisDocente.repository.UsuarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private UsuarioRepo usuarioRepo;

    public Chat crearChat(ChatRequest chatRequest) {
        Usuario usuario1 = usuarioRepo.findById(chatRequest.getUsuario1Id())
                .orElseThrow(() -> new RuntimeException("Usuario 1 no encontrado"));
        Usuario usuario2 = usuarioRepo.findById(chatRequest.getUsuario2Id())
                .orElseThrow(() -> new RuntimeException("Usuario 2 no encontrado"));

        Chat chat = new Chat();
        chat.setUsuario1(usuario1);
        chat.setUsuario2(usuario2);

        return chatRepository.save(chat);
    }

    public void eliminarChat(Integer id) {
        Chat chat = chatRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Chat no encontrado con id " + id));
        chatRepository.delete(chat);
    }
}
