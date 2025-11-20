package com.GestionAsisDocente.service;

import com.GestionAsisDocente.dto.PublicacionesRequest;
import com.GestionAsisDocente.entity.Publicaciones;
import com.GestionAsisDocente.entity.Tipo;
import com.GestionAsisDocente.entity.Usuario;
import com.GestionAsisDocente.repository.PublicacionesRepository;
import com.GestionAsisDocente.repository.TipoRepository;
import com.GestionAsisDocente.repository.UsuarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublicacionesService {

    @Autowired
    private PublicacionesRepository publicacionesRepository;

    @Autowired
    private TipoRepository tipoRepository;

    @Autowired
    private UsuarioRepo usuarioRepo;

    public List<Publicaciones> getAllPublicaciones() {
        return publicacionesRepository.findAll();
    }

    public Publicaciones createPublicacion(PublicacionesRequest request) {
        Tipo tipo = tipoRepository.findById(request.getTipoId())
                .orElseThrow(() -> new RuntimeException("Tipo not found with id " + request.getTipoId()));
        Usuario usuario = null;
        if (request.getUsuarioId() != null) {
            usuario = usuarioRepo.findById(request.getUsuarioId())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        }



        Publicaciones pub = new Publicaciones();
        pub.setTitulo(request.getTitulo());
        pub.setDescripcion(request.getDescripcion());
        pub.setFecha(request.getFecha());
        pub.setHora(request.getHora());
        pub.setArchivo(request.getArchivo());
        pub.setUbicacion(request.getUbicacion());
        pub.setTipo(tipo);
        pub.setUsuario(usuario);
        pub.setEstado(usuario != null);

        return publicacionesRepository.save(pub);
    }

    public Publicaciones updatePublicacion(Integer id, PublicacionesRequest request) {
        Publicaciones pub = publicacionesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Publicación not found with id " + id));

        Tipo tipo = tipoRepository.findById(request.getTipoId())
                .orElseThrow(() -> new RuntimeException("Tipo not found with id " + request.getTipoId()));
        Usuario usuario = null;
        if (request.getUsuarioId() != null) {
            usuario = usuarioRepo.findById(request.getUsuarioId())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        }


        pub.setTitulo(request.getTitulo());
        pub.setDescripcion(request.getDescripcion());
        pub.setFecha(request.getFecha());
        pub.setHora(request.getHora());
        pub.setArchivo(request.getArchivo());
        pub.setUbicacion(request.getUbicacion());
        pub.setTipo(tipo);
        pub.setUsuario(usuario);
        pub.setEstado(request.isEstado());

        return publicacionesRepository.save(pub);
    }

    public void deletePublicacion(Integer id) {
        Publicaciones pub = publicacionesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Publicación not found with id " + id));
        publicacionesRepository.delete(pub);
    }

    public Publicaciones getPublicacionById(Integer id) {
        return publicacionesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Publicación not found with id " + id));
    }

    public List<Publicaciones> getPublicacionesOcultas() {
        return publicacionesRepository.findByEstadoFalse();
    }


    public List<Publicaciones> getPublicacionesByUser(Integer userId) {
        Usuario usuario = usuarioRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id " + userId));
        return publicacionesRepository.findByUsuario(usuario);
    }
    public List<Publicaciones> getReportesBusquedaMascotas() {
        Integer TIPO_BUSCANDO = 2; // o léelo de un enum/constante si prefieres
        return publicacionesRepository.findByTipoIdAndEstadoTrue(TIPO_BUSCANDO);
    }

}
