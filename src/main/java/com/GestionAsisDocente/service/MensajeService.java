package com.GestionAsisDocente.service;

import com.GestionAsisDocente.dto.MensajeRequest;
import com.GestionAsisDocente.entity.Chat;
import com.GestionAsisDocente.entity.Mensaje;
import com.GestionAsisDocente.entity.Usuario;
import com.GestionAsisDocente.repository.ChatRepository;
import com.GestionAsisDocente.repository.MensajeRepository;
import com.GestionAsisDocente.repository.UsuarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MensajeService {

    @Autowired
    private MensajeRepository mensajeRepository;

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private UsuarioRepo usuarioRepo;

    public Mensaje crearMensaje(MensajeRequest request) {
        Chat chat = chatRepository.findById(request.getChatId())
                .orElseThrow(() -> new RuntimeException("Chat no encontrado con id " + request.getChatId()));

        Usuario mandadoPor = usuarioRepo.findById(request.getMandadoPorId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id " + request.getMandadoPorId()));

        // Validación simple: al menos texto o archivo
        if ((request.getTexto() == null || request.getTexto().isBlank()) &&
                (request.getArchivo() == null || request.getArchivo().isBlank())) {
            throw new RuntimeException("El mensaje debe tener texto o archivo.");
        }

        Mensaje mensaje = new Mensaje();
        mensaje.setChat(chat);
        mensaje.setMandadoPor(mandadoPor);
        mensaje.setArchivo(request.getArchivo());
        mensaje.setTexto(request.getTexto());

        return mensajeRepository.save(mensaje);
    }

    public List<Mensaje> getMensajesPorChat(Integer chatId) {
        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(() -> new RuntimeException("Chat no encontrado con id " + chatId));

        return mensajeRepository.findByChatOrderByIdAsc(chat);
    }

    public Mensaje getMensajeById(Integer id) {
        return mensajeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mensaje no encontrado con id " + id));
    }

    public void eliminarMensaje(Integer id) {
        Mensaje mensaje = mensajeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mensaje no encontrado con id " + id));
        mensajeRepository.delete(mensaje);
    }
}
