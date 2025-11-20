package com.GestionAsisDocente.service;

import com.GestionAsisDocente.dto.ComentariosRequest;
import com.GestionAsisDocente.entity.Comentarios;
import com.GestionAsisDocente.entity.Publicaciones;
import com.GestionAsisDocente.entity.Usuario;
import com.GestionAsisDocente.repository.ComentariosRepository;
import com.GestionAsisDocente.repository.PublicacionesRepository;
import com.GestionAsisDocente.repository.UsuarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComentariosService {

    @Autowired
    private ComentariosRepository comentariosRepository;

    @Autowired
    private PublicacionesRepository publicacionesRepository;

    @Autowired
    private UsuarioRepo usuarioRepo;

    public List<Comentarios> getAllComentarios() {
        return comentariosRepository.findAll();
    }

    public Comentarios createComentario(ComentariosRequest request) {
        Comentarios comentario = new Comentarios();

        comentario.setArchivo(request.getArchivo());
        comentario.setIncognito(request.getIncognito());
        comentario.setTexto(request.getTexto());
        comentario.setLikes(request.getLikes() != null ? request.getLikes() : 0);

        Publicaciones publicacion = publicacionesRepository.findById(request.getPublicacionId())
                .orElseThrow(() -> new RuntimeException("Publicación no encontrada"));
        comentario.setPublicacion(publicacion);

        Usuario usuario = usuarioRepo.findById(request.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        comentario.setUsuario(usuario);

        return comentariosRepository.save(comentario);
    }

    public Comentarios updateComentario(Integer id, ComentariosRequest request) {
        Comentarios comentario = comentariosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comentario no encontrado"));

        comentario.setArchivo(request.getArchivo());
        comentario.setIncognito(request.getIncognito());
        comentario.setTexto(request.getTexto());
        comentario.setLikes(request.getLikes() != null ? request.getLikes() : comentario.getLikes());

        if (request.getPublicacionId() != null) {
            Publicaciones publicacion = publicacionesRepository.findById(request.getPublicacionId())
                    .orElseThrow(() -> new RuntimeException("Publicación no encontrada"));
            comentario.setPublicacion(publicacion);
        }

        if (request.getUsuarioId() != null) {
            Usuario usuario = usuarioRepo.findById(request.getUsuarioId())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            comentario.setUsuario(usuario);
        }

        return comentariosRepository.save(comentario);
    }

    public void deleteComentario(Integer id) {
        Comentarios comentario = comentariosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comentario no encontrado"));
        comentariosRepository.delete(comentario);
    }

    public Comentarios getComentarioById(Integer id) {
        return comentariosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comentario no encontrado"));
    }

    public List<Comentarios> getComentariosByPublicacion(Integer publicacionId) {
        return comentariosRepository.findByPublicacionIdOrderByIdAsc(publicacionId);
    }

}
